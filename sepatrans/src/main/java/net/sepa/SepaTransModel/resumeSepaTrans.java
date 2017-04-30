package net.sepa.SepaTransModel;

import java.util.Collection;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "separesume")
public class resumeSepaTrans {

	@XmlElement
	Collection<DirectDebitTransactionInformationResumeClass> Transaction;
	
	public resumeSepaTrans() {
		this.Transaction = new LinkedList<>();
	}

	public resumeSepaTrans(Collection<DirectDebitTransactionInformationResumeClass> drctDbtTxInfResume) {
		super();
		Transaction = drctDbtTxInfResume;
	}

	public void addTransaction(DirectDebitTransactionInformationResumeClass drctDbtTxInfResume){
		this.Transaction.add(drctDbtTxInfResume);
	}

	public Collection<DirectDebitTransactionInformationResumeClass> getTransaction(){
		return this.Transaction;
	}
	
	public void setTransactions(Collection<DirectDebitTransactionInformationResumeClass> transaction){
		this.Transaction = transaction;
	}
}

