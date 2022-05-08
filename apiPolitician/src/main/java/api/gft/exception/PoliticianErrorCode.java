package api.gft.exception;

public enum PoliticianErrorCode {
	
	UNCAUGHTERROR(249,"Uncaught error"),
	DUPLICATEDLEADER(250, "This person can not be a leader because the party has already a leader"),
	INVALIDCPF(251,"Invalid CPF"),
	FILEPROBLEM(252,"There is a problem with the file"),
	OPERATIONNOTPERFORMED(253,"Not performed"),
	NOTFOUND(254, "Not found!"),
	FAILEDTODELETE(255, "Failed to delete.");
	
	private final String displayValue;
	private final int codeError;


	PoliticianErrorCode(int codeError, String displayValue) {
		this.codeError = codeError;
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	public int getCodeError() {
		return codeError;
	}

	public static PoliticianErrorCode getEnum(String displayValue) {
		for(PoliticianErrorCode p: PoliticianErrorCode.values()) {
			if(p.getDisplayValue().equalsIgnoreCase(displayValue)) {
				return p;
			}
		}
		return null;
	}

}
