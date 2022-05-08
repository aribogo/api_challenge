package api.gft.entity;

public enum StatusBill {
	
	VOTE("Forwarded to vote"),
	PRESENTED("Presented in Plenary"),
	APPROVED("Approved in Plenary"),
	VETOED("Vetoed in Plenary"),
	SANCTIONED("Sanctioned");
	
	private final String displayValue;

	private StatusBill(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	
	public static StatusBill getEnum(String displayValue) {
		for(StatusBill p: StatusBill.values()) {
			if(p.getDisplayValue().equalsIgnoreCase(displayValue)) {
				return p;
			}
		}
		return null;
	}

	
}
