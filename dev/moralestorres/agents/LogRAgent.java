// Hands-on 7: Logistic Regression
package dev.moralestorres.agents;

import jade.core.Agent;
import dev.moralestorres.behaviours.LogRBehaviour;
import dev.moralestorres.algorithms.LogisticRegression;
import dev.moralestorres.util.GUI;

@SuppressWarnings("serial")
public class LogRAgent extends Agent implements RegressionAgent {
	private LogisticRegression lr;
	private GUI gui;
	public void setup(){
		this.lr = new LogisticRegression();
		this.addBehaviour(new LogRBehaviour(lr));
		this.gui = new GUI("Logistic Regression", 2, this);
		this.gui.showGui();
	}
	public double predict(double[] x){
		return this.lr.predict(x);
	}
}