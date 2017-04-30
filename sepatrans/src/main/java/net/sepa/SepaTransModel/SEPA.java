package net.sepa.SepaTransModel;

import java.util.Collection;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SEPA")
public class SEPA {

	@XmlElement
	Collection<DirectDebitTransactionInformationClass> DrctDbtTxInf;
	
	public SEPA() {
		this.DrctDbtTxInf = new LinkedList<>();
	}

	public SEPA(Collection<DirectDebitTransactionInformationClass> drctDbtTxInf) {
		super();
		DrctDbtTxInf = drctDbtTxInf;
	}

	public void addTransaction(DirectDebitTransactionInformationClass drctDbtTxInf){
		this.DrctDbtTxInf.add(drctDbtTxInf);
	}

	public Collection<DirectDebitTransactionInformationClass> getTransaction(){
		return this.DrctDbtTxInf;
	}
	
	public void setTransactions(Collection<DirectDebitTransactionInformationClass> transaction){
		this.DrctDbtTxInf = transaction;
	}
}

