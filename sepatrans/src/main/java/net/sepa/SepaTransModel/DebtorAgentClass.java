package net.sepa.SepaTransModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DbtrAgt")
public class DebtorAgentClass {

	@XmlElement
	FinalInstitutionIdentifierClass FinInstnId;

	public DebtorAgentClass() {
		
	}
	
	public DebtorAgentClass(FinalInstitutionIdentifierClass finInstnId) {
		super();
		this.FinInstnId = finInstnId;
	}

	public FinalInstitutionIdentifierClass getFinInstnId() {
		return FinInstnId;
	}
}
