package dev.moralestorres.agents;

import jade.core.Agent;
import dev.moralestorres.behaviours.MLRBehaviour;
import dev.moralestorres.algorithms.MultipleLinearRegression;
import dev.moralestorres.util.GUI;

@SuppressWarnings("serial")
public class MLRAgent extends Agent implements RegressionAgent {
	private MultipleLinearRegression mlr;
	private GUI gui;
	public void setup(){
		this.mlr = new MultipleLinearRegression();
		this.gui = new GUI("Multiple Linear Regression", 2, this);
		this.addBehaviour(new MLRBehaviour(mlr));
		this.gui.showGui();
	}
	public double predict(double[] x){
		return this.mlr.predict(x);
	}
}