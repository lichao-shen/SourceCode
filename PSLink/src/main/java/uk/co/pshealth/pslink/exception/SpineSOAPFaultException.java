package uk.co.pshealth.pslink.exception;

public class SpineSOAPFaultException extends Exception {

	private static final long serialVersionUID = 1L;

	public SpineSOAPFaultException() {		
		super();
	}
	
	public SpineSOAPFaultException(String errorMsg) {		
		super(errorMsg);
	}		
				
	public SpineSOAPFaultException(Throwable cause) {
        super(cause);
    }

	public SpineSOAPFaultException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
