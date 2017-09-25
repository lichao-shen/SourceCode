package uk.co.pshealth.pslink.dto;

import javax.xml.bind.annotation.XmlElement;

public class AddressDTO {

	@XmlElement(namespace = "http://pshealth.co.uk")
	private String postalCode;
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private String addressKey;
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public void setAddressKey(String addressKey) {
		this.addressKey = addressKey;
	}
		
}
