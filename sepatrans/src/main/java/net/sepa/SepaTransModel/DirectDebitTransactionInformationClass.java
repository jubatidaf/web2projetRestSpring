package net.sepa.SepaTransModel;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "DrctDbtTxInf")
@XmlAccessorType(XmlAccessType.FIELD)
public class DirectDebitTransactionInformationClass {

	@XmlTransient
	int id;
	
	@XmlTransient
	String Num;
	
	@XmlElement
	String PmtId;

	@XmlElement
	double InstdAmt;
	
	@XmlElement
	DirectDebitTransactionClass DrctDbtTx;
	
	@XmlElement
	DebtorAgentClass DbtrAgt;
	
	@XmlElement
	DebtorClass Dbtr;
	
	@XmlElement
	DebtorAccountClass DbtrAcct;
	
	@XmlElement
	String RmtInf;

	public DirectDebitTransactionInformationClass() {
		
	}

	public DirectDebitTransactionInformationClass(int id, String num,String pmtId, double instdAmt, DirectDebitTransactionClass drctDbtTx,
			DebtorAgentClass dbtrAgt, DebtorClass dbtr, DebtorAccountClass dbtrAcct,
			String rmtInf) {
		super();
		this.id = id;
		Num = verifNum(num);
		PmtId = pmtId;
		InstdAmt = instdAmt;
		DrctDbtTx = drctDbtTx;
		DbtrAgt = dbtrAgt;
		Dbtr = dbtr;
		DbtrAcct = dbtrAcct;
		RmtInf = rmtInf;
	}

	public int getId() {
		return id;
	}

	public String getNum() {
		return Num;
	}

	public String verifNum(String n) {
		while(n.length()<4){
			n = "0"+n;
		}
		n = "TJ"+n;
		return n;
	}

	public String getPmtId() {
		return PmtId;
	}

	public double getInstdAmt() {
		return InstdAmt;
	}

	public DirectDebitTransactionClass getDrctDbtTx() {
		return DrctDbtTx;
	}

	public DebtorAgentClass getDbtrAgt() {
		return DbtrAgt;
	}

	public DebtorClass getDbtr() {
		return Dbtr;
	}

	public DebtorAccountClass getDbtrAcct() {
		return DbtrAcct;
	}

	public String getRmtInf() {
		return RmtInf;
	}
	
}
