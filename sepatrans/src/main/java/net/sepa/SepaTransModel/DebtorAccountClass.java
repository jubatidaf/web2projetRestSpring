package net.sepa.SepaTransModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DbtrAcct")
public class DebtorAccountClass {

	@XmlElement
	IdentificationClass Id;

	public DebtorAccountClass() {
		
	}

	public DebtorAccountClass(IdentificationClass id) {
		super();
		Id = id;
	}

	public IdentificationClass getId() {
		return Id;
	}
}
