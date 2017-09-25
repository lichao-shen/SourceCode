package uk.co.pshealth.pslink.dto;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "PatientInfo")
public class PatientInfo {

	private String name;
	private String NHSNumber;
	private String interactionId;
	
	
	public void setInteractionId(String interactionId) {
		this.interactionId = interactionId;
	}
	
	public String getInteractionId() {
		return this.interactionId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setNHSNumber(String NHSNumber) {
		this.NHSNumber = NHSNumber;
	}
	
	public String getNHSNumber() {
		return this.NHSNumber;
	}	
	
}