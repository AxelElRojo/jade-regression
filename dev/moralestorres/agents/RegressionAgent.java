package dev.moralestorres.agents;

public interface RegressionAgent {
	void setup();
	double predict(double[] x);
}