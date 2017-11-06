package gr.iti.mklab.verify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeaturesAnnotationItem {
	
	@Expose
    @SerializedName(value = "id")
	protected String id;
	@Expose
    @SerializedName(value = "label")
	protected String label;
	
	@Expose
    @SerializedName(value = "event")
	protected String event;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
	

}
