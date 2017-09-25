package uk.co.pshealth.pslink.exception;

public class SpineSocketTimeoutException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SpineSocketTimeoutException() {		
		super();
	}
	
	public SpineSocketTimeoutException(String errorMsg) {		
		super(errorMsg);
	}	
	
	public SpineSocketTimeoutException(Throwable cause) {
        super(cause);
    }

	public SpineSocketTimeoutException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
