package uk.co.pshealth.pslink.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.apache.camel.ValidationException;


public class CamelValidationExceptionMapper implements ExceptionMapper<org.apache.camel.ValidationException>  {

	@Override
	public Response toResponse(ValidationException exception) {
		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).type("text/plain").build();
	}

}