package uk.co.pshealth.pslink.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import uk.co.pshealth.pslink.exception.XSDValidationException;

public class XSDValidationExceptionMapper implements ExceptionMapper<XSDValidationException>  {

	@Override
	public Response toResponse(XSDValidationException exception) {
		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).type("text/plain").build();
	}

}
