package net.sepa.SepaTransModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TransactionResume")
public class DirectDebitTransactionInformationResumeClass {

	@XmlElement
	String Num;
	
	@XmlElement
	String Identifiant;
	
	@XmlElement
	double Montant;
	
	@XmlElement
	String Date;
	
	public DirectDebitTransactionInformationResumeClass(){
		
	}

	public DirectDebitTransactionInformationResumeClass(String num, String identifiant, double montant, String date) {
		super();
		this.Num = num;
		Identifiant = identifiant;
		this.Montant = montant;
		this.Date = date;
	}
	
}
