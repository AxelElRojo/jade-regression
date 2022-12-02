// Hands on 6: Linear Regression by Gradient Descent
package dev.moralestorres.agents;

import jade.core.Agent;
import dev.moralestorres.algorithms.LinearRegressionGD;
import dev.moralestorres.behaviours.LRGDBehaviour;
import dev.moralestorres.util.GUI;

@SuppressWarnings("serial")
public class LRGDAgent extends Agent implements RegressionAgent {
	private LinearRegressionGD lr;
	private GUI gui;
	public void setup(){
		this.lr = new LinearRegressionGD();
		this.addBehaviour(new LRGDBehaviour(this.lr));
		this.gui = new GUI("Linear Regression GD", 1, this);
		this.gui.showGui();
	}
	public double predict(double[] x){
		return this.lr.predict(x[0]);
	}
}