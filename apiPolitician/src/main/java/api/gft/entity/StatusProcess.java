package api.gft.entity;

public enum StatusProcess {

	ANALYSIS("In Analysis"),
	DEFERRED("Deferred"),
	REJECTED("Rejected");
	
	private final String displayValue;

	private StatusProcess(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	
	public static StatusProcess getEnum(String displayValue) {
		for(StatusProcess p: StatusProcess.values()) {
			if(p.getDisplayValue().equalsIgnoreCase(displayValue)) {
				return p;
			}
		}
		return null;
	}
	
}
