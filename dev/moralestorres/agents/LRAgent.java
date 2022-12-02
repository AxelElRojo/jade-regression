// Hands on 4: Linear Regression
package dev.moralestorres.agents;

import jade.core.Agent;
import dev.moralestorres.algorithms.LinearRegression;
import dev.moralestorres.behaviours.LRBehaviour;
import dev.moralestorres.util.GUI;

@SuppressWarnings("serial")
public class LRAgent extends Agent implements RegressionAgent {
	private LinearRegression lr;
	private GUI gui;
	@Override
	public void setup(){
		this.lr = new LinearRegression();
		this.addBehaviour(new LRBehaviour(this.lr));
		this.gui = new GUI("Linear Regression", 1, this);
		this.gui.showGui();
	}
	public double predict(double[] x){
		return this.lr.predict(x[0]);
	}
}