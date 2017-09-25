package uk.co.pshealth.pslink.service;

import javax.jws.WebService;
import java.lang.Thread;


@WebService(endpointInterface = "uk.co.pshealth.pslink.service.SOAPWebService")
public class SOAPWebServiceTimeoutImpl implements SOAPWebService {

	public String sayHello(String name) throws Exception {
		
		Thread.sleep(10000);
		
		return "Hello " + name;
	}

}
