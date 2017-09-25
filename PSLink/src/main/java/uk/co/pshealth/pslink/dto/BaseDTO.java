package uk.co.pshealth.pslink.dto;

import javax.xml.bind.annotation.XmlElement;

public class BaseDTO {

	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private MessageInfoDTO messageInfo;
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private UserInfoDTO userInfo;
		
	public void setUserInfo(UserInfoDTO userInfo) {
		this.userInfo = userInfo;
	}
		
	public void setMessageInfo(MessageInfoDTO messageInfo) {
		this.messageInfo = messageInfo;
	}
				  
	public String getInteractionId() throws Exception {
		throw new Exception("Message Type Unsupported!");
	}
}
