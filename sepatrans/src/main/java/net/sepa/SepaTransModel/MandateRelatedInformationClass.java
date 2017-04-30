package net.sepa.SepaTransModel;

import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MndtRltdInf")
public class MandateRelatedInformationClass {
	
	@XmlElement
	String MndtId;

	@XmlElement
	String DtOfSgntr;

	public MandateRelatedInformationClass () {
		
	}
	
	public MandateRelatedInformationClass (String mndtId, String dtOfSgntr) {
		super();
		MndtId = mndtId;
		DtOfSgntr = dtOfSgntr;
	}
	
	public String getDtOfSgntr() {
		return DtOfSgntr;
	}

	public String getMndtId() {
		return MndtId;
	}
	
}
