package dev.moralestorres.behaviours;

import jade.core.behaviours.Behaviour;
import dev.moralestorres.algorithms.LogisticRegression;
import dev.moralestorres.util.MultipleDataset;
@SuppressWarnings("serial")
public class LogRBehaviour extends Behaviour {
	private LogisticRegression lr;
	public LogRBehaviour(LogisticRegression lr){
		this.lr = lr;
	}
	public void action(){
		double[][] x = {
			{1, 1},
			{4, 2},
			{2, 4}
		};
		double[] y = {1, 0, 0};
		this.lr.train(new MultipleDataset(x, y), .001, 10000);
	}
	public boolean done(){
		return this.lr.isDone();
	}
	public int onEnd(){
		System.out.print("Logistic Regression: ");
		System.out.println(this.lr.toString());
		return 0;
	}
}