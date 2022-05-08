package api.gft.entity;

public enum Position {
	
	CITYCOUNCILOR("City Councilor"),
	GOVERNOR("Governor"),
	STATEDEPUTY("State deputy"),
	CONGRESSMAN("Congressman"),
	SENATOR("Senator"),
	MINISTER("Minister"),
	MAYOR("Mayor"),
	PRESIDENT("President");
	
	private final String displayValue;

	private Position(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	
	public static Position getEnum(String displayValue) {
		for(Position p: Position.values()) {
			if(p.getDisplayValue().equalsIgnoreCase(displayValue)) {
				return p;
			}
		}
		return null;
	}

}
