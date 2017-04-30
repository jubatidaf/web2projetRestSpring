package net.sepa.SepaTransModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stats")
public class TransactionStatistiqueClass {

	@XmlElement
	int NombreTransactions;
	
	@XmlElement
	double MontantTotal;
	
	public TransactionStatistiqueClass(){
		
	}

	public TransactionStatistiqueClass(int nombreTransactions, double montantTotal) {
		super();
		NombreTransactions = nombreTransactions;
		MontantTotal = montantTotal;
	}
	
}