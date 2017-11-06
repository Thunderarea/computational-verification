package gr.iti.mklab.verify;

public class VerificationResult {

	
	private String prediction;
	private double prob;
	private String id;
	private boolean decided;
	
	public String getPrediction() {
		return prediction;
	}
	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}
	public double getProb() {
		return prob;
	}
	public void setProb(double prob) {
		this.prob = prob;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isDecided() {
		return decided;
	}
	public void setDecided(boolean decided) {
		this.decided = decided;
	}
}
