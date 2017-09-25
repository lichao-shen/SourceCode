package uk.co.pshealth.pslink.util;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class UtilpsLink {
	
	private static final String UUID_PREFIX = "uuid:";
	
	private static final String DATE_TIME_FORMATTER = "yyyyMMddHHmmss";
	
	
	/*
	 * Method to generate unique message id for SOAP request message
	 * e.g. uuid:971084C8-5097-41B2-8B7F-7E5A3317B2B8
	 * Universal Unique Identifier (UUID) in the common UUID format 
	 * that consists of 5 hyphen-separated groups of hexadecimal digits
	 * having 8, 4, 4, 4, and 12 places respectively
	 */
	
	public static String UUIDGenerator() {
		
		String messageId = UUID_PREFIX + UUID.randomUUID().toString().toUpperCase();
		
		return messageId;
		
	}
	
	/*
	 * Method to get current date and time as message creation time.
	 */
	public static String getCurrentDateTime() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
		String currentDateTime = LocalDateTime.now().format(formatter); 
		
		return currentDateTime;
		
	}
	
	/*
	 * Method to create SOAP message. 
	 */
	public static SOAPMessage createSOAPRequest(String soapContent, String interactionId) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        soapPart.setContent(new StreamSource(new StringReader(soapContent)));
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", "urn:nhs:names:services:pdsquery/" + interactionId );
		headers.addHeader("Content-Type", "text/xml");			
							
        return soapMessage;
    }
	
	/*
	 * Print a SOAP message
	 */
	public static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    Source sourceContent = soapResponse.getSOAPPart().getContent();
	    System.out.print("\nResponse SOAP Message = ");
	    StreamResult result = new StreamResult(System.out);	 
	    transformer.transform(sourceContent, result);
	    String soapStr = new String(result.getOutputStream().toString());			
	    
	}
	
	/*
	 * Get root cause of an exception
	 */
	public static Throwable getRootCause(Throwable e) {
	    Throwable cause = null; 
	    Throwable result = e;

	    while(null != (cause = result.getCause())  && (result != cause) ) {
	        result = cause;
	    }
	    return result;
	}

	
}
