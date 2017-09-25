package uk.co.pshealth.pslink.exception;

public class SpineSOAPException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SpineSOAPException() {		
		super();
	}
	
	public SpineSOAPException(String errorMsg) {		
		super(errorMsg);
	}		
				
	public SpineSOAPException(Throwable cause) {
        super(cause);
    }

	public SpineSOAPException(String message, Throwable throwable) {
        super(message, throwable);
    }
}