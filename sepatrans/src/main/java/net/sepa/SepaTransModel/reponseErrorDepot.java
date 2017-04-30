package net.sepa.SepaTransModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
public class reponseErrorDepot {

	@XmlElement
	String Error;

	@XmlElement
	String success;

	@XmlElement
	String Num;
	
	public reponseErrorDepot(){
		
	}
	public reponseErrorDepot(String error, String msgSuccess, String num) {
		super();
		Error = error;
		success = msgSuccess;
		Num = num;
	}
}
