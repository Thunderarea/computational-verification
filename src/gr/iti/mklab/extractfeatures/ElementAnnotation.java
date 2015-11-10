package gr.iti.mklab.extractfeatures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElementAnnotation {

	
	//private Instance inst;
	@Expose(serialize=false)
	@SerializedName(value = "id")
	private String id;
	
	@Expose
	@SerializedName(value = "predicted_value")
	private String predicted;
	
	@Expose(serialize=false)
	@SerializedName(value = "classifiers_agreed")
	private boolean agreed;
	
	@Expose
	@SerializedName(value = "confidence_value")
	private double confidenceValue;
	
   
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
	/*public Instance getInstance() {
		return inst;
	}*/
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
	
	public String toJSONString() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(this);
	}
	
}
