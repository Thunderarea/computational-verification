package gr.iti.mklab.extractfeatures;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserFeaturesAnnotation {
	
	@Expose
    @SerializedName(value = "id")
	protected String id;
	@Expose
    @SerializedName(value = "username")
	protected String username;
	@Expose
    @SerializedName(value = "reliability")
	protected String reliability;
	
	public String getId() {
		return id;
	}
	
	public String getReliability() {
		return reliability;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setReliability(String reliability) {
		this.reliability = reliability;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
