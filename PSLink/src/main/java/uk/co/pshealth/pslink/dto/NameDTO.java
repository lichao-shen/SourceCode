package uk.co.pshealth.pslink.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;


public class NameDTO {
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private List<String> given;
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private String family;
	
	
	public void setFamily(String family) {
		this.family = family;
	}
	
	public void setGiven(List<String> given) {
		this.given = given;
	}
	
}
