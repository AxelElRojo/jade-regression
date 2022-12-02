package dev.moralestorres.algorithms;
import dev.moralestorres.util.MultipleDataset;

public class LogisticRegression {
	private double minCost;
	private double[] weights, prevSet;
	private boolean done;
	public LogisticRegression(){
		this.minCost = 1e100;
		this.done = false;
	}
	private void initWeights(int degrees){
		this.weights = new double[degrees];
        this.prevSet = new double[degrees];
        for(int i=0;i<degrees;++i){
        	this.weights[i] = 0;
        	this.prevSet[i] = 0;
        }
	}
	private double[][] prepend(double[][] x){
		int degrees = x[0].length, len = x.length;
		double[][] temp = new double[len][degrees+1];
        for(int i=0;i<len;++i)
        	temp[i][0] = 1;
        for(int i=0;i<len;++i)
        	for(int j=1;j<degrees+1;++j)
        		temp[i][j] = x[i][j-1];
        return temp;
	}
	public void train(MultipleDataset data, double rate, int iterations){
		double[][] x = data.x().clone();
		double[] y = data.y().clone(), tempWeights;
		if(x.length != y.length)
            throw new IllegalArgumentException("Los tamaños son diferentes!");
        int degrees = x[0].length, len = x.length;
        double sum;
        x = this.prepend(x);
        degrees++;
        this.initWeights(degrees);
        for(int i=0;i<iterations;++i){
        	tempWeights = this.weights.clone();
    		for(int j=0;j<degrees;++j){
    			sum = 0;
    			for(int k=0;k<len;++k)
    				sum += (this.predict(x[k]) - y[j]) * x[j][k];
    			tempWeights[j] -= rate * sum;
    		}
    		this.weights = tempWeights.clone();
    		if(this.cost(x, y) != 0){
    			if(Math.abs(this.cost(x, y)) < Math.abs(this.minCost)){
	    			this.minCost = this.cost(x, y);
	    			this.prevSet = this.weights.clone();
    			}
    		}else
    			break;
        }
        if(this.cost(x, y) != 0)
        	this.weights = this.prevSet.clone();
        this.done = true;
	}
	public double predict(double[] x){
		double dot = this.weights[0];
		for(int i=1;i<this.weights.length;++i)
			dot += x[i-1]*this.weights[i];
		return 1 / ( 1 + Math.exp(-dot));
	}
	private double cost(double[][] x, double[] y){
		double total = 0;
		for(int i=0; i<x.length; ++i)
			total += y[i]*Math.log(this.predict(x[i])) + (1 - y[i]) * Math.log(1 - this.predict(x[i]));
		return -total / (double)x.length;
	}
	public double[] getWeights(){
		return this.weights.clone();
	}
	public boolean isDone(){
		return this.done;
	}
	public String toString(){
		int len = this.weights.length;
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("y = 1/(1+e^-(%.4f + ", this.weights[0]));
		for(int i=1;i<len-1;++i)
			builder.append(String.format("x[%d]*%.4f + ", i-1, this.weights[i]));
		builder.append(String.format("x[%d]*%.4f))", len-2, this.weights[len-1]));
		return builder.toString();
	}
}