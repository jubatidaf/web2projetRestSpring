package net.sepa.SepaTransModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FinInstnId")
public class FinalInstitutionIdentifierClass {

	@XmlElement
	String BIC;
	
	public FinalInstitutionIdentifierClass() {
		
	}

	public FinalInstitutionIdentifierClass(String bic) {
		super();
		this.BIC = bic;
	}

	public String getBIC() {
		return BIC;
	}
}
