package uk.co.pshealth.pslink.processor;

import java.util.HashMap;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import uk.co.pshealth.pslink.dto.BaseDTO;
import uk.co.pshealth.pslink.util.UtilpsLink;


public class MessageProcessor implements Processor {

	
	public void process(Exchange exchange) throws Exception {		
		
		HashMap<String,Object> params = new HashMap<String,Object>();
		BaseDTO baseInfo = null;
		String msgId = null;
		String currentDateTime = null;
								
		baseInfo = exchange.getIn().getBody(BaseDTO.class);		
		msgId = UtilpsLink.UUIDGenerator();
		currentDateTime = UtilpsLink.getCurrentDateTime();
		
		params.put("msgId", msgId);
		params.put("currentDateTime", currentDateTime);						
		params.put("interactionId", baseInfo.getInteractionId());
		exchange.getIn().setHeaders(params);				
		
		//exchange.getOut().setBody("<out body message />");		
		//System.out.println(" exchange message Id is " + exchange.getExchangeId());
		//exchange.getIn().setBody("<PatientInfo/>");
		
	}
		
	
}
