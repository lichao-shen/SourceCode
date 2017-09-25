package uk.co.pshealth.pslink.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface SOAPWebService{

	@WebMethod(action="urn:nhs:names:services:pdsquery/test") 
	String sayHello(String name) throws Exception;

}