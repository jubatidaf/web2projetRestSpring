package net.sepa.SepaTransController;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import net.sepa.SepaTransImplementation.InterfaceTransaction;
import net.sepa.SepaTransModel.*;
import net.sepa.sepaTransValide.*;


@Controller
public class SepaController {
	
	@Autowired
	protected InterfaceTransaction interfaceTransaction;
	private SEPA sepa;
	private resumeSepaTrans resumeSepa;
	
	public SepaController(){
		sepa = new SEPA();
		resumeSepa = new resumeSepaTrans();	
	}
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView homePage(ModelAndView model) throws IOException{
		model.setViewName("home");
		return model;
	}
	/*
	@RequestMapping(value="/doc", method = RequestMethod.GET)
	public ModelAndView homePage1(ModelAndView model) throws IOException{
		model.setViewName("doc");
		return model;
	}*/
	
	// lister toutes les transaction avec leurs informations détaillée en XML
	@RequestMapping(value="/transaction", method = RequestMethod.GET)
	public @ResponseBody SEPA SepaListTransXML() {
		this.sepa.setTransactions(interfaceTransaction.list_tous_transactions());
		return this.sepa;
	}
	
	//Liste de toutes les transactions résumées en XML
	@RequestMapping(value="/resume", method = RequestMethod.GET)
	public @ResponseBody resumeSepaTrans ResumeSepaTransXML() {
		this.resumeSepa.setTransactions(interfaceTransaction.list_Resume_All_transaction());
		return this.resumeSepa;
	}
	
	//Retourne le nombre de transactions et le montant total des 
	//transactions en XML
	@RequestMapping(value="/stats", method = RequestMethod.GET)
	public @ResponseBody TransactionStatistiqueClass SepaTransStatsXML() {
		return interfaceTransaction.get_Stats_transaction();
	}
	
	// trouvé une transaction par son "Payment Identification" = id
	@RequestMapping(value="/trx/{id}", method = RequestMethod.GET)
	public @ResponseBody DirectDebitTransactionInformationClass SepaTransByIdXML(@PathVariable("id") String id) {
		return interfaceTransaction.get_transaction_by_id(id);
	}
	
	/*Reçoit un flux XML d'une transaction, 
	 *et retourne la valeur PmtId*/
	
	@RequestMapping(value="/depot", method = RequestMethod.POST)
	public @ResponseBody reponseErrorDepot AjoutSepaTransation(@RequestBody String body) throws SAXException, ParserConfigurationException, IOException {
		
		
		
		InputSource inputSource = new InputSource(new StringReader(body));

		//tester si les informations saisie sont correcte
		sepaValidationXml validator = new sepaValidationXml();
		/*
		if(validator.validate_XML("/sepa.xsd", inputSource)==false){
			return new reponseErrorDepot("Fichier XML non valide.", null, null);
		}
		*/
		Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder()
	            .parse(inputSource);
		int id = interfaceTransaction.get_Max_Id()+1;
		DirectDebitTransactionClass directDebitTransactionClass=new DirectDebitTransactionClass((new MandateRelatedInformationClass(doc.getElementsByTagName("MndtId").item(0).getTextContent(),
				doc.getElementsByTagName("DtOfSgntr").item(0).getTextContent())));
		FinalInstitutionIdentifierClass finalInstitutionIdentifierClass=new FinalInstitutionIdentifierClass(
				doc.getElementsByTagName("BIC").item(0).getTextContent());
		DebtorAgentClass debtorAgentClass=new DebtorAgentClass(finalInstitutionIdentifierClass);
		DebtorClass debtorClass=new DebtorClass(
				doc.getElementsByTagName("Nm").item(0).getTextContent());
		IdentificationClass identificationClass=new IdentificationClass(
				doc.getElementsByTagName("IBAN").item(0).getTextContent());
		DebtorAccountClass debtorAccountClass=new DebtorAccountClass(identificationClass);
		try{
			
			//créer l'objet d'une transaction a partir des information réçus
			
		
		
				DirectDebitTransactionInformationClass drctDbtTxInf = 
				new DirectDebitTransactionInformationClass(id,Integer.toString(id),
				doc.getElementsByTagName("PmtId").item(0).getTextContent(),
				Double.parseDouble
				(doc.getElementsByTagName("InstdAmt").item(0).getTextContent()), 
				directDebitTransactionClass,
				debtorAgentClass,
				debtorClass, 
				debtorAccountClass,
				doc.getElementsByTagName("RmtInf").
				item(0).getTextContent());
				
				//avant d'enregistrer faut verifier si l'identifiant existe deja
				//sic'est le cas en renvois un message d'erreur
				if(interfaceTransaction.get_transaction_by_id(drctDbtTxInf.getPmtId())!=null){
					return new reponseErrorDepot("identifiant "+drctDbtTxInf.getPmtId()+"existe déjà.", null, null);
				}
				
				interfaceTransaction.ajouter_transaction(drctDbtTxInf);
				
				return new reponseErrorDepot(null, "votre transaction a bien ete enregistree.", drctDbtTxInf.getNum());
		}
		catch(NullPointerException e){
			return new reponseErrorDepot("Fichier XML non valide !", null, null);
		}
	}
	/*
	@RequestMapping(value="/depot", method = RequestMethod.POST)
	public @ResponseBody Response addTransaction(@RequestBody String body) throws SAXException, ParserConfigurationException, IOException {
		
		InputSource inputSource = new InputSource(new StringReader(body));
		//InputSource source ;
		
		sepaValidationXml validator = new sepaValidationXml();
		
		if(validator.validate_XML("/sepa.xsd", inputSource)==0){
			return new Response("Fichier XML non valide.", null, null);
		}
		
		Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder()
	            .parse(inputSource);
		
		int id = interfaceTransaction.get_Max_Id()+1;
		
		try{
			DirectDebitTransactionInformationClass drctDbtTxInf = new DirectDebitTransactionInformationClass(id,Integer.toString(id),
				doc.getElementsByTagName("PmtId").item(0).getTextContent(),
				Double.parseDouble(doc.getElementsByTagName("InstdAmt").item(0).getTextContent()), 
				new DirectDebitTransactionClass((new MandateRelatedInformationClass(doc.getElementsByTagName("MndtId").item(0).getTextContent(),
						doc.getElementsByTagName("DtOfSgntr").item(0).getTextContent()))),
				new DebtorAgentClass(new FinalInstitutionIdentifierClass(doc.getElementsByTagName("BIC").item(0).getTextContent())),
				new DebtorClass(doc.getElementsByTagName("Nm").item(0).getTextContent()), 
				new DebtorAccountClass(new IdentificationClass(doc.getElementsByTagName("IBAN").item(0).getTextContent())),
				doc.getElementsByTagName("RmtInf").item(0).getTextContent());
		
				if(interfaceTransaction.get_transaction_by_id(drctDbtTxInf.getPmtId())!=null){
					return new Response("L'identifiant de votre transaction existe déjà.", null, null);
				}
				
				interfaceTransaction.ajouter_transaction(drctDbtTxInf);
				
				return new Response(null, "Transaction enregistrée.", drctDbtTxInf.getNum());
		}
		catch(NullPointerException e){
			return new Response("Fichier XML non valide !", null, null);
		}
	}*/
}