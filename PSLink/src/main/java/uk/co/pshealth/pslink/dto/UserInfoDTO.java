package uk.co.pshealth.pslink.dto;

import javax.xml.bind.annotation.XmlElement;

public class UserInfoDTO {

	@XmlElement(namespace = "http://pshealth.co.uk")
	private String userName;
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private String userRole;
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
}
