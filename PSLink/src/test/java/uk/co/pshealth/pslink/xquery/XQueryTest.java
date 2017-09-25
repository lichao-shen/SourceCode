package uk.co.pshealth.pslink.xquery;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.SimpleNamespaceContext;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.XpathEngine;
import org.junit.Test;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import java.util.Map;
import org.custommonkey.xmlunit.NamespaceContext;


public class XQueryTest extends CamelBlueprintTestSupport {
	
	@Override
    protected String getBlueprintDescriptor() {
        
		return "/XQuery/OSGI-INF/XQueryCamelContext.xml";
    }
	
	@Test
	public void retrievalXQueryTest() throws Exception {
		
		String xml = IOUtils.toString(this.getClass().getResourceAsStream("/input/Retrieval.xml"), "UTF-8");
		Map<String,Object> headers = new HashMap<String,Object>();
		headers.put("msgId", "QUPA_IN000008UK02");
		headers.put("currentDateTime", "");		
		
		org.w3c.dom.Document result = (org.w3c.dom.Document) template.requestBodyAndHeaders("direct:Retrieval", xml, headers);
		
		int nodeNumber = this.getNodeNumber(result, "//hl7:QUPA_IN000008UK02/hl7:ControlActEvent/hl7:query");
		assertEquals(1, nodeNumber);
			
	}		
	
	@Test
	public void simpleTraceQueryTest() throws Exception {
			
		String xml = IOUtils.toString(this.getClass().getResourceAsStream("/input/SimpleTrace.xml"), "UTF-8");
		Map<String,Object> headers = new HashMap<String,Object>();
		headers.put("msgId", "QUPA_IN000005UK01");
		headers.put("currentDateTime", "");		
		
		org.w3c.dom.Document result = (org.w3c.dom.Document) template.requestBodyAndHeaders("direct:SimpleTrace", xml, headers);
		
		int nodeNumber = this.getNodeNumber(result, "//hl7:QUPA_IN000005UK01/hl7:ControlActEvent/hl7:query");
		assertEquals(1, nodeNumber);
			
	}
	
	
	private int getNodeNumber(org.w3c.dom.Document document, String xPath) throws Exception{
				
		Map map = new HashMap();
        map.put( "hl7", "urn:hl7-org:v3");
        NamespaceContext ctx = new SimpleNamespaceContext(map);
        //XMLUnit.setXpathNamespaceContext(ctx);
        XpathEngine xPathEngine = XMLUnit.newXpathEngine();
        xPathEngine.setNamespaceContext(ctx);        
        NodeList nodes = xPathEngine.getMatchingNodes(xPath, document);
    
        /*
		DOMSource source = new DOMSource(result); 
        StringWriter writer = new StringWriter(); 
        Transformer transformer = null; 
        try { 
            transformer = TransformerFactory.newInstance() 
                    .newTransformer(); 
            transformer.transform(source, new StreamResult(writer)); 
        } catch (TransformerException e) { 
             
            e.printStackTrace(); 
        } 
        String s = writer.getBuffer().toString();         
        System.out.println(" output is " + s); */
		
		return nodes.getLength();
	}
	
	
}	
