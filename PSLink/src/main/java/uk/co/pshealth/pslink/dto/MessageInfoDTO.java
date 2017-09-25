package uk.co.pshealth.pslink.dto;

import javax.xml.bind.annotation.XmlElement;

public class MessageInfoDTO {
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private String messageType;
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private String messageName;
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private String messageCreationTime;
	
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}
	
	public void setmessageCreationTime(String messageCreationTime) {
		this.messageCreationTime = messageCreationTime;
	}
		
}
