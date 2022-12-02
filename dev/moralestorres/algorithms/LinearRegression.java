package dev.moralestorres.algorithms;

public class LinearRegression {
    protected double b0, b1;

    public LinearRegression(){
        this.b0 = 0;
        this.b1 = 0;
    }
    public void train(double[] x, double[] y){
    	if(x.length != y.length)
            throw new IllegalArgumentException("Los tamaños son diferentes!");
        int len = x.length;
        double sumX = 0, sumY = 0, errX, errY, errXX = 0, errXY = 0;
        for(int i = 0; i < len; i++){
        	sumX += x[i];
        	sumY += y[i];
        }
        errX = sumX / (double)len;
        errY = sumY / (double)len;
        for(int i = 0; i < len; i++){
        	errXX += (x[i] - errX) * (x[i] - errX);
        	errXY += (x[i] - errX) * (y[i] - errY);
        }
        this.b0 = errXY / errXX;
        this.b1 = errY - this.b0 * errX;
    }
    public double getB0(){
        return this.b0;
    }
    public double getB1(){
        return this.b1;
    }
    public double predict(double x){
        return this.getB0()*x + this.getB1();
    }
    public String toString(){
    	if(this.getB1() >= 0)
    		return String.format("y = %.8f%c + %.8f", this.getB0(), 'x', this.getB1());
    	else
    		return String.format("y = %.8f%c - %.8f", this.getB0(), 'x', this.getB1()*-1);
    }
}