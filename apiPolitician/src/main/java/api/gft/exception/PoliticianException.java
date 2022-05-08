package api.gft.exception;

public class PoliticianException extends Exception {

	private static final long serialVersionUID = 1L;
	private PoliticianErrorCode politicianErrorCode;

	public PoliticianException(PoliticianErrorCode politicianErrorCode, String errorMessage, Exception e) {
		super(errorMessage, e);
		this.politicianErrorCode = politicianErrorCode ;
	}

	public PoliticianErrorCode getPoliticianErrorCode() {
		return politicianErrorCode;
	}
	
}
