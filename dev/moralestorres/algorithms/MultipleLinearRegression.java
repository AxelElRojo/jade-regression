package dev.moralestorres.algorithms;
import dev.moralestorres.util.Matrix;
public class MultipleLinearRegression {
	private double[] weights;
	private boolean done;
	public void train(double _x[][], double _y[]){
		_x = this.prepend(_x);
		// En el behaviour x viene en 2x200, pero se necesita de 200x2
		Matrix x = new Matrix(_x).transpose();
		Matrix transposed = x.transpose();
		Matrix y = new Matrix(_y);
		Matrix res;
		double[][] temp;
		res = transposed.multiply(x).invert();
		res = res.multiply(transposed.multiply(y));
		temp = res.toArray();
		this.weights = new double[temp.length];
		for(int i=0;i<temp.length;++i)
			this.weights[i] = temp[i][0];
		this.done = true;
	}
	public double predict(double[] x){
		double total = 0;
		for(int i=1;i<x.length+1;++i)
			total += x[i-1]*this.weights[i];
		total += this.weights[0];
		return total;
	}
	private double[][] prepend(double[][] x){
		int len = x[0].length, degrees = x.length;
		double[][] temp = new double[degrees+1][len];
        for(int i=0;i<len;++i)
        	temp[0][i] = 1;
        for(int i=0;i<degrees;++i)
        	for(int j=0;j<len;++j)
        		temp[i+1][j] = x[i][j];
        return temp;
	}
	public String toString(){
		int len = this.weights.length;
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("y = %.4f + ", this.weights[0]));
		for(int i=1;i<len-1;++i)
			builder.append(String.format("x[%d]*%.4f + ", i-1, this.weights[i]));
		builder.append(String.format("x[%d]*%.4f", len-2, this.weights[len-1]));
		return builder.toString();
	}
	public boolean isDone(){
		return this.done;
	}
}