package gr.iti.mklab.verify;

/**
 * Auxiliary class that holds information about the classification of the tweets with the BAGGING technique.
 * Note that it is used only by the Bagging class.
 * @author boididou
 *
 */
public class VerificationResult {

	
	protected String prediction;
	protected double prob;
	protected String id;
	
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
}
