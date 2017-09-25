package uk.co.pshealth.pslink.processor;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.commons.io.IOUtils;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.pshealth.pslink.exception.SpineSocketTimeoutException;
import uk.co.pshealth.pslink.service.SOAPWebServiceImpl;
//import uk.co.pshealth.pslink.service.SOAPWebServiceImpl;
import uk.co.pshealth.pslink.service.SOAPWebServiceTimeoutImpl;

import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;


public class SOAPRequestProcessorTest extends CamelBlueprintTestSupport {
	
	private static String WEB_SERVICE_URL = "http://127.0.0.1:9876/hw";
	private Endpoint endpoint = null;
	private Map<String,Object> headers = new HashMap<String,Object>();; 
	
	@Override
    protected String getBlueprintDescriptor() {
        
		return "/processor/OSGI-INF/ProcessorCamelContext.xml";
    }		
	
	@Before
	public void publishService() {
		endpoint = Endpoint.publish(WEB_SERVICE_URL, new SOAPWebServiceImpl());
		headers.put("interactionId", "test");
	}
	
	@After
	public void stopService() {
		endpoint.stop();
	}
	
	@Override
	public String useOverridePropertiesWithConfigAdmin(Dictionary props) {
    	
		props.put("SPINE_KEY_STORE_LOCATION", "");
		props.put("SPINE_TRUST_STORE_LOCATION", "");
		props.put("SPINE_SYNC_URL", "http://127.0.0.1:9876/hw");		
        
		return "pslink.persistent";
    }
	
	
	//@Test
	public void SOAPRequestConnectionTest() throws Exception {
				
		String xml = IOUtils.toString(this.getClass().getResourceAsStream("/processor/input/SOAPRequest.xml"), "UTF-8");		
		template.requestBodyAndHeaders("direct:SOAPRequest", xml, headers);

	}
	
	//@Test
	public void SOAPRequestTimeoutTest() throws SpineSocketTimeoutException, IOException {
		
		String xml = IOUtils.toString(this.getClass().getResourceAsStream("/processor/input/SOAPRequest.xml"), "UTF-8");
		template.requestBodyAndHeaders("direct:SOAPRequest", xml, headers);
		
	}
	
}
