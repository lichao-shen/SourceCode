package uk.co.pshealth.pslink.exception;

import javax.ws.rs.ext.Provider;

@Provider
public class XSDValidationException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public XSDValidationException() {		
		super();
	}
	
	public XSDValidationException(String errorMsg) {		
		super(errorMsg);
	}		
	
	public XSDValidationException(Throwable cause) {
        super(cause);
    }

	public XSDValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }
					
}