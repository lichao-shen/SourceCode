package uk.co.pshealth.pslink.dto.retrieval;

import javax.xml.bind.annotation.XmlElement;

public class NHSNumberDTO {

	@XmlElement(namespace = "http://pshealth.co.uk")
	private String identifier;
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private String value;
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
		
}