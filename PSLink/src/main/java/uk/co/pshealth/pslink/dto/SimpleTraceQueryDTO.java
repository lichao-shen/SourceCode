package uk.co.pshealth.pslink.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SimpleTraceQuery", namespace = "http://pshealth.co.uk")
public class SimpleTraceQueryDTO extends BaseDTO {

	@XmlElement(namespace = "http://pshealth.co.uk")
	private AddressDTO address;
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private String gender;
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private String birthTime;
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private String deceasedTime;	
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private NameDTO name;
	
	private static final String SIMPLE_TRACE_QUERY_INTERACTION_ID = "QUPA_IN000005UK01";
	
	
	public void setName(NameDTO name) {
		this.name = name;
	}	
	
	public void setAddress(AddressDTO address) {
		this.address = address; 
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setBirthTime(String birthTime) {
		this.birthTime = birthTime;
	}
		
	public void setDeceasedTime(String deceasedTime) {
		this.deceasedTime = deceasedTime;
	}
	
	public String getInteractionId() {
		return SIMPLE_TRACE_QUERY_INTERACTION_ID; 
	}
	
}
