package dev.moralestorres.behaviours;

import jade.core.behaviours.Behaviour;
import dev.moralestorres.algorithms.LinearRegressionGD;
import dev.moralestorres.util.SingleDataset;

@SuppressWarnings("serial")
public class LRGDBehaviour extends Behaviour {
	private LinearRegressionGD lr;

	public LRGDBehaviour(LinearRegressionGD lr){
		this.lr = lr;
	}
	public void action(){
		double[] x = {1,2,3,4,5,6,7,8,9};
		double[] y = {2,4,6,8,10,12,14,16,18};
		this.lr.train(new SingleDataset(x, y), 2, 100000000);
	}
	public int onEnd(){
		System.out.print("Linear Regression GD: ");
		System.out.println(this.lr.toString());
		return 0;
	}
	public boolean done(){
		return this.lr.isDone();
	}
}