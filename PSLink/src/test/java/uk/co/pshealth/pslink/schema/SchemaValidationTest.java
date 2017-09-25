package uk.co.pshealth.pslink.schema;

import org.apache.commons.io.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class SchemaValidationTest  extends CamelBlueprintTestSupport {

	@Override
    protected String getBlueprintDescriptor() {
        
		return "/OSGI-INF/blueprint/CamelContext.xml";
    }
	
	@Test
	public void RetrievalValidation() throws Exception {
		    
	    String xml = IOUtils.toString(this.getClass().getResourceAsStream("/schema/input/Retrieval.xml"), "UTF-8");
		template.sendBody("direct:Retrieval", xml);
		
		/*
		java.net.URL url = MyClass.class.getResource("test/resources/abc.xml");
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        String xml = new String(java.nio.file.Files.readAllBytes(resPath), "UTF8"); */        
	}
	
	@Test
	public void SimpleTraceValidation() throws Exception {
		
		String xml = IOUtils.toString(this.getClass().getResourceAsStream("/schema/input/SimpleTrace.xml"), "UTF-8");
		template.sendBody("direct:SimpleTrace", xml);		
	}
		
}
