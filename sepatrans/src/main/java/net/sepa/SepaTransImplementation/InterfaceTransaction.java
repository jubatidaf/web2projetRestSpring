package net.sepa.SepaTransImplementation;

import java.util.List;

import net.sepa.SepaTransModel.DirectDebitTransactionInformationClass;
import net.sepa.SepaTransModel.DirectDebitTransactionInformationResumeClass;
import net.sepa.SepaTransModel.DirectDebitTransactionInformationClass;
import net.sepa.SepaTransModel.DirectDebitTransactionInformationResumeClass;
import net.sepa.SepaTransModel.TransactionStatistiqueClass;

public interface InterfaceTransaction {

	public void ajouter_transaction(DirectDebitTransactionInformationClass DrctDbtTxInf);
	
	public DirectDebitTransactionInformationClass get_transaction_by_id(String pmtId);
	
	public int get_Max_Id();
	
	public List<DirectDebitTransactionInformationClass> list_tous_transactions();
	
	public List<DirectDebitTransactionInformationResumeClass> list_Resume_All_transaction();
	
	public TransactionStatistiqueClass get_Stats_transaction();
}
