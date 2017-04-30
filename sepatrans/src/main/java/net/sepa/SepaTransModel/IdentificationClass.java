package net.sepa.SepaTransModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Id")
public class IdentificationClass {

	@XmlElement
	String IBAN;

	public IdentificationClass() {
		
	}

	public IdentificationClass(String iBAN) {
		super();
		IBAN = iBAN;
	}

	public String getIBAN() {
		return IBAN;
	}
}
