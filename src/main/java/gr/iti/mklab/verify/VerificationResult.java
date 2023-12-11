package gr.iti.mklab.verify;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import gr.iti.mklab.util.FileManager;

public class VerificationResult {
	private String prediction;
	private double prob;
	private String id;
	private String actual;

	public VerificationResult(String prediction, double prob, String id, String actual) {
		this.prediction = prediction;
		this.prob = prob;
		this.id = id;
		this.actual = actual;
	}

	public VerificationResult(String prediction, double prob) {
		this.prediction = prediction;
		this.prob = prob;
	}

	public VerificationResult(String id, String prediction, double prob) {
		this.id = id;
		this.prediction = prediction;
		this.prob = prob;
	}

	public boolean isPredictionEqualsActual() {
		return prediction.equals(actual);
	}

	public boolean isActualEquals(String value) {
		return actual.equals(value);
	}

	public void writeToFile(List<String> listPred, List<Double> listScores, String path) {
		// ID ACTUAL PREDICTED PREDICTED_PER_MODEL AVERAGE_SCORE SCORE_PER_MODEL
		FileManager.getInstance().writePlainDataToFile(id + "\t" +
				actual + "\t" +
				prediction + "\t" +
				StringUtils.join(listPred, " ") + "\t" +
				(getProb()) + "\t" +
				StringUtils.join(listScores, " "),
				path);
	}

	public void writeToFile(String path) {
		FileManager.getInstance().writePlainDataToFile(id + "\t" + prediction, path);
	}

	// Not sure if the prediction can take only the values "real" and "fake" so I
	// created this method
	public String realFakePrediction() {
		String pred = prediction;
		if (!prediction.equals("fake"))
			pred = "real";
		return pred;
	}

	public String getPrediction() {
		return prediction;
	}

	public double getProb() {
		double p = prob;
		if (prediction.equals("fake"))
			p = (1 - prob);
		return p;
	}

	public String getId() {
		return id;
	}
}
