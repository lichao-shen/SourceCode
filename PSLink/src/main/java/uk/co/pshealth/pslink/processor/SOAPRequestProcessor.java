package uk.co.pshealth.pslink.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;
import uk.co.pshealth.pslink.exception.SpineSocketTimeoutException;
import uk.co.pshealth.pslink.exception.SpineSOAPException;
import uk.co.pshealth.pslink.exception.SpineSOAPFaultException;
import uk.co.pshealth.pslink.util.UtilpsLink;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import javax.xml.soap.*;

import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.security.SecureRandom;
import java.io.FileInputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SOAPRequestProcessor implements Processor {

	private static final Logger LOG = LoggerFactory.getLogger(SOAPRequestProcessor.class);
	
	private static int TIME_OUT = 5000;		
	
	@PropertyInject(value = "SPINE_SYNC_URL")
	private static String spineURL;		

	@PropertyInject(value = "SPINE_KEY_STORE_LOCATION")
	private static String keyStoreLocation;	

	@PropertyInject(value = "SPINE_TRUST_STORE_LOCATION")
	private static String trustStoreLocation;	
	
	private static SSLContext context = null;
    private static KeyStore keyStore = null;
    private static KeyStore trustStore = null;    
	    
	private static String CHARACTER_ENCODING = "utf-8";
	private static String DEFAULT_SSL_PASSWORD = "changeit";
	private static String EXCHANGE_INTERACTION_HEADER = "interactionId";

	
	public void process(Exchange exchange) throws Exception {
				
		LOG.debug("Entering SOAPRequestProcessor");
		
		if (context == null) {
			init();
		}
		
		String interactionId = (String)exchange.getIn().getHeader(EXCHANGE_INTERACTION_HEADER);		
		SOAPConnection soapConnection = null;
		SOAPMessage soapResponse = null;
		SOAPBody soapBody = null;
		String soapContent = null;
		URL endpoint = null;
		
		try {	
			soapContent = exchange.getIn().getBody(String.class);
			
			LOG.info("SOAP Request Message: {}", soapContent);
			
			//spineURL = "https://msg.dev.spine2.ncrs.nhs.uk/sync-service";
			//System.out.println("spineURL is " + spineURL );
						
			long startTime = System.currentTimeMillis();
			
	        soapConnection = SOAPConnectionFactory.newInstance().createConnection(); 
	        
	        endpoint = new URL(new URL(spineURL), "",
        		          new URLStreamHandler() {
        		            @Override
        		            protected URLConnection openConnection(URL url) throws IOException {
        		              URL target = new URL(url.toString());
        		              URLConnection connection = target.openConnection();        		             
        		              connection.setReadTimeout(TIME_OUT);	        		              
        		              return connection;
        		            } 
        		          });
        	        	
	        soapResponse = soapConnection.call(UtilpsLink.createSOAPRequest(soapContent, interactionId), endpoint);
	        if (soapResponse != null) {
	        	soapBody = soapResponse.getSOAPBody();	        
	        }	        	         
	        
	        ByteArrayOutputStream stream = new ByteArrayOutputStream();
 			soapResponse.writeTo(stream);
 			String soapResponseStr = new String(stream.toByteArray(), CHARACTER_ENCODING);
 			
 			LOG.info("Spine Response Message: {}", soapResponseStr);
 			
 			exchange.getOut().setBody(soapResponseStr);
 				  
 			long endTime = System.currentTimeMillis(); 			 			
 			LOG.debug("SOAP Request took {} milliseconds",(endTime - startTime)); 			
 			
 			// or should check http response status code != 200 ??
	        if (soapBody.hasFault()) {
	 			throw new SpineSOAPFaultException(soapResponseStr);
	 		}
	        
		} catch (javax.xml.soap.SOAPException e) {	
			LOG.error("Calling Spine PDS failed because {}", e.toString());
			Throwable root = UtilpsLink.getRootCause(e);
			if (root instanceof java.net.SocketTimeoutException) {
				throw new SpineSocketTimeoutException(e);
			} else {
				throw new SpineSOAPException(e);
			}			
		} finally {		
	        if (soapConnection != null) {
	        	try {
	        		soapConnection.close();
	        	} catch (javax.xml.soap.SOAPException e) {
	        		e.printStackTrace();
	        		LOG.error("Failed closing SOAP connection {}", e.toString());
	        	} 		        	
	        }			
		}  
		
		LOG.debug("Leaving SOAPRequestProcessor");
		
	}
	
	private static synchronized void init() throws Exception {
	
		setupKeyStore();		
		setupTrustStore();		
		createContext();		
		HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
		// SSLContext.setDefault() ??
	}
	
	private static void setupKeyStore() throws Exception {
        
		LOG.debug("Loading Key Store");
		
		keyStore = KeyStore.getInstance("jks");
		try (FileInputStream fis = new FileInputStream(keyStoreLocation)) {
                keyStore.load(fis, DEFAULT_SSL_PASSWORD.toCharArray());
        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
            //System.err.println(e.toString());
            LOG.error("Failed to load Key Store from {}, because {}", keyStoreLocation, e.toString());
            throw e;
        }
		
		LOG.debug("Key Store Loaded");
        
    }
	
	
	private static void setupTrustStore() throws Exception
    {		
		LOG.debug("Loading Trust Store");
		
		try {            
			trustStore = KeyStore.getInstance("jks");
            try (FileInputStream fis = new FileInputStream(trustStoreLocation)) {
                trustStore.load(fis, DEFAULT_SSL_PASSWORD.toCharArray());
            }            
        }
        catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
            LOG.error("Failed to load trust Store from {}, because {}", trustStoreLocation, e.toString());
            throw e;
        }
		
		LOG.debug("Trust Store Loaded");
    }
	
	
	private static void createContext() throws Exception {
		
		LOG.debug("creating SSL Context");
		
		KeyManagerFactory kmf = null;
        
        kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, DEFAULT_SSL_PASSWORD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()); 
        tmf.init(trustStore);
        
        SecureRandom sr = new SecureRandom();
        sr.nextInt();
        
        context = SSLContext.getInstance("TLSv1.2");
        context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), sr);
        
        LOG.debug("SSL Context Initialized");
	}	
	
		
}
