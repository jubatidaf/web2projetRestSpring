package net.sepa.SepaTransModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Dbtr")
public class DebtorClass {

	@XmlElement
	String Nm;

	public DebtorClass() {
		
	}

	public DebtorClass(String nm) {
		super();
		Nm = nm;
	}

	public String getNm() {
		return Nm;
	}
	
}