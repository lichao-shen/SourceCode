package uk.co.pshealth.pslink.service;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.apache.camel.OutHeaders;

import uk.co.pshealth.pslink.exception.SpineSocketTimeoutException;
import uk.co.pshealth.pslink.exception.XSDValidationException;

public class SOAPWebServiceTimeoutHandler {
	
	//private int counter = 0;
	
	public Object SOAPTimeoutHandler(@Headers Map<?, ?> in, @Body String payload, @OutHeaders Map<String, Object> out) throws XSDValidationException, SpineSocketTimeoutException {
        
		System.out.println(" inside SOAPTimeoutHandler ");
		throw new SpineSocketTimeoutException("Request Timeout");		 
	}
	

}
