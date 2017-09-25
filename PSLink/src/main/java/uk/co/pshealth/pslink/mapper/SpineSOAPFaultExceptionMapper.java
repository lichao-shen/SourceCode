package uk.co.pshealth.pslink.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import uk.co.pshealth.pslink.exception.SpineSOAPFaultException;

public class SpineSOAPFaultExceptionMapper implements ExceptionMapper<SpineSOAPFaultException>  {

	@Override
	public Response toResponse(SpineSOAPFaultException exception) {
		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).type("text/plain").build();
	}

}
