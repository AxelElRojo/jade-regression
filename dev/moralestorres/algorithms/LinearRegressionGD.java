package dev.moralestorres.algorithms;
import dev.moralestorres.util.SingleDataset;

public class LinearRegressionGD extends LinearRegression {
	private double minCost = 1e100, prevB0, prevB1;
	private boolean done = false;
    public void train(SingleDataset data, double rate, int iterations){
    	double[] x = data.x().clone();
    	double[] y = data.y().clone();
    	if(x.length != y.length)
            throw new IllegalArgumentException("Los tamaños son diferentes!");
    	int len = x.length;
    	double sum;
    	for(int i=0; i<iterations; ++i){
    		sum = 0;
    		for(int j=0; j<len; ++j)
    			sum += x[j]*(this.predict(x[j]) - y[j]);
    		this.b0 -= (rate/(double)(len)) * sum;
    		sum = 0;
    		for(int j=0; j<len; ++j)
    			sum += this.predict(x[j]) - y[j];
    		this.b1 -= (rate/(double)(len)) * sum;
    		if(this.cost(x, y) == 0)
    			break;
    		else if(Math.abs(this.cost(x, y)) <= Math.abs(this.minCost)){
    			this.minCost = this.cost(x, y);
    			this.prevB0 = this.b0;
    			this.prevB1 = this.b1;
    		}else{
    			this.b0 = this.prevB0;
    			this.b1 = this.prevB1;
    			break;
    		}
    	}
    	done = true;
    }
    private double cost(double[] x, double y[]){
    	double total = 0;
    	for(int i=0; i<y.length; ++i)
    		total += (y[i] - this.predict(x[i])) * (y[i] - this.predict(x[i]));
    	return total / (double)y.length;
    }
    public boolean isDone(){
    	return this.done;
    }
}