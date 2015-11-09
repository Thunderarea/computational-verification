package gr.iti.mklab.extractfeatures;

import weka.core.Instance;

public class ElementAnnotation {

	
	private Instance inst;
	private String id;
	private String predicted;
	private boolean agreed;
	private double confidenceValue;
	
	//setters
	public void setInstance(Instance inst) {
		this.inst = inst;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPredicted(String predicted) {
		this.predicted = predicted;
	}
	public void setAgreed(boolean agreed) {
		this.agreed = agreed;
	}
	public void setConfidenceValue(double confidenceValue) {
		this.confidenceValue = confidenceValue;
	}
	
	//getters
	public Instance getInstance() {
		return inst;
	}
	public String getId() {
		return id;
	}
	public String getPredicted() {
		return predicted;
	}
	public boolean getAgreed() {
		return agreed;
	}
	public double getConfidenceValue() {
		return confidenceValue;
	}
	

	
}
