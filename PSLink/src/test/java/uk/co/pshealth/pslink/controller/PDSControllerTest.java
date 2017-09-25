package uk.co.pshealth.pslink.controller;

import java.io.IOException;
import javax.ws.rs.core.MediaType;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class PDSControllerTest extends CamelBlueprintTestSupport {
	
	private static final String BASE_URL = "http://localhost:9999/pds/v1/";
	private CloseableHttpClient httpClient;
	
	 
    @Override
	public boolean isUseAdviceWith() {
    	return true;
    }
    
    @Override
    protected String getBlueprintDescriptor() {
        
		return "/RESTful/blueprint/RESTful.xml";
    }
	
    @Before
    public void setUpTests() {
    	
        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }
    }

    @After
    public void closeHttpClient() throws IOException {
    	
    	httpClient.close();
        httpClient = null;
    	
    }    
    
    @Test
    public void testSimpleTraceQuery() throws Exception {
        
    	String url = BASE_URL + "SimpleTraceQuery"; 
        String xml = IOUtils.toString(this.getClass().getResourceAsStream("/input/SimpleTrace.xml"), "UTF-8");
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", MediaType.APPLICATION_XML);
        HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        /*String stringResponse = EntityUtils.toString(response.getEntity());        
        Post blogPost = new ObjectMapper().readValue(stringResponse, Post.class);
        assertEquals("My Title", blogPost.getTitle()); */
        
        assertEquals(200, response.getStatusLine().getStatusCode());
    }
    
    @Test
    public void testRetrievalQuery() throws Exception {
        
    	String url = BASE_URL + "RetrievalQuery";
        String xml = IOUtils.toString(this.getClass().getResourceAsStream("/input/Retrieval.xml"), "UTF-8");
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", MediaType.APPLICATION_XML);
        HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        
        assertEquals(200, response.getStatusLine().getStatusCode()); 
    }

}
