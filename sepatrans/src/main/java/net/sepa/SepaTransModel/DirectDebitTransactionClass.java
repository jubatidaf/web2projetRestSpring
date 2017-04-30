package net.sepa.SepaTransModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DrctDbtTx")
public class DirectDebitTransactionClass{

	@XmlElement
	MandateRelatedInformationClass MndtRltdInf;
	
	public DirectDebitTransactionClass(){
		
	}
	
	public DirectDebitTransactionClass(MandateRelatedInformationClass mndtRltdInf) {
		super();
		this.MndtRltdInf = mndtRltdInf;
	}

	public MandateRelatedInformationClass getMndtRltdInf() {
		return MndtRltdInf;
	}
}