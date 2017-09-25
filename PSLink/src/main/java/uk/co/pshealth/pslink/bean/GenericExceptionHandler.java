package uk.co.pshealth.pslink.bean;

import java.util.Map;
import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.apache.camel.OutHeaders;

import uk.co.pshealth.pslink.exception.XSDValidationException;

public class GenericExceptionHandler {


	public Object ValidationExceptionHandler(@Headers Map<?, ?> in, @Body String payload, @OutHeaders Map<String, Object> out) throws XSDValidationException {
		            
		throw new XSDValidationException("Valication failed ");		 
	}
					
}
