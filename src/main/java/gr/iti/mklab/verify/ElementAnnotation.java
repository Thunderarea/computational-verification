package gr.iti.mklab.verify;

public class ElementAnnotation {
	private String id;
	private String actual;
	private String predicted;
	private boolean agreed;

	public ElementAnnotation(String id, String actual, String predicted, boolean agreed) {
		this.id = id;
		this.actual = actual;
		this.predicted = predicted;
		this.agreed = agreed;
	}

	public String getId() {
		return id;
	}

	public String getActual() {
		return actual;
	}

	public String getPredicted() {
		return predicted;
	}

	public boolean getAgreed() {
		return agreed;
	}
	
}
