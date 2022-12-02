package dev.moralestorres.behaviours;

import jade.core.behaviours.Behaviour;
import dev.moralestorres.algorithms.LinearRegression;

@SuppressWarnings("serial")
public class LRBehaviour extends Behaviour {
	private LinearRegression lr;
	public LRBehaviour(LinearRegression lr){
		this.lr = lr;
	}
	public void action(){
		double[] x = {
			1,2,3,4,5,6,7,8,9
		};
		double[] y = {
			4,8,12,16,20,24,28,32,36
		};
		this.lr.train(x, y);
	}
	public int onEnd(){
		System.out.print("Linear Regression: ");
		System.out.println(this.lr.toString());
		return 0;
	}
	public boolean done(){
		return this.lr.getB0() != 0 || this.lr.getB1() != 0;
	}
}
