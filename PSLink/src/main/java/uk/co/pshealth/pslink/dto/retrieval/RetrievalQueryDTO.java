package uk.co.pshealth.pslink.dto.retrieval;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.pshealth.pslink.dto.BaseDTO;
import uk.co.pshealth.pslink.dto.retrieval.RetrievalQueryDTO;

@XmlRootElement(name = "RetrievalQuery", namespace = "http://pshealth.co.uk")
public class RetrievalQueryDTO extends BaseDTO {
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private String historicDataIndicator;
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private NHSNumberDTO NHSNumber;
	
	@XmlElement(namespace = "http://pshealth.co.uk")
	private String retrievalItem;		
	
	private static final String RETRIEVAL_QUERY_INTERACTION_ID = "QUPA_IN000008UK02";
		
	public void setHistoricDataIndicator(String historicDataIndicator) {
		this.historicDataIndicator = historicDataIndicator;
	}
	
	public void setNHSNumber(NHSNumberDTO nhsNumber) {
		this.NHSNumber = nhsNumber;
	}
		
	public void setRetrievalItem(String retrievalItem) {
		this.retrievalItem = retrievalItem;
	}
	
	public String getInteractionId() {
		return RETRIEVAL_QUERY_INTERACTION_ID; 
	}
	
}
