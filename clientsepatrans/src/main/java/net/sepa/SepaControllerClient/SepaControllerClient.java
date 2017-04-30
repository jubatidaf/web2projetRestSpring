package net.sepa.SepaControllerClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


@Controller
public class SepaControllerClient {

	
	
	//une fonction qui permet de retourné un document à partir de flux xml retourné par le serveur
	public Document parseDocument(String url) throws IOException, SAXException, ParserConfigurationException{
		String readLine;
		StringBuffer stringBuffer = new StringBuffer();

		URL objetUrl = new URL(url);
		HttpURLConnection httpURLConnection = (HttpURLConnection) objetUrl.openConnection();

		httpURLConnection.setRequestMethod("GET");
		BufferedReader bufferedReader = new BufferedReader(
		        new InputStreamReader(httpURLConnection.getInputStream()));
		
		while ((readLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(readLine);
		}
		bufferedReader.close();

		if(stringBuffer.toString().contains("<?xml version=\"1.0\"")){
	        return (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder()
		            .parse(new InputSource(new StringReader(stringBuffer.toString())));
		}
		return null;
	}
	//*************************************************************
	//page d'accueil
	@RequestMapping(value={"/","/home"})
	public ModelAndView homePage(ModelAndView model) throws IOException{
		
		model.setViewName("home");
		
		return model;
	}
	
	//***********************************************************************
	//retourne une page jsp contenant les detail transactions
	@RequestMapping(value="/transaction", method = RequestMethod.GET)
	public ModelAndView transactionDetails(ModelAndView model) throws IOException, SAXException, ParserConfigurationException{
        
		model.addObject("SepaTransactionDetail", parseDocument("http://localhost:8080/sepatrans/transaction"));
		model.setViewName("sepaDetailTransaction");
		
		return model;
	}
	
	//***********************************************************************
	//retourne une page jsp contenant le resumé des transactions
	@RequestMapping(value="/resume", method = RequestMethod.GET)
	public ModelAndView transactionResume(ModelAndView model) throws IOException, SAXException, ParserConfigurationException{
        
		model.addObject("listTransactions", parseDocument("http://localhost:8080/sepatrans/resume"));
		model.setViewName("sepaResumeTransaction");
		
		return model;
	}
	
	//***********************************************************************
	//retourne une page jsp contenant les statistiques des transactions
	@RequestMapping(value="/stats", method = RequestMethod.GET)
	public ModelAndView transactionStats(ModelAndView model) throws IOException, SAXException, ParserConfigurationException{
        
		model.addObject("statistiques", parseDocument("http://localhost:8080/sepatrans/stats"));
		model.setViewName("sepaTransactionStats");
		
		return model;
	}
	
	//***********************************************************************
	//retourne une transaction recherché par son id
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public ModelAndView transactionById( ModelAndView model){
		
		model.setViewName("sepaTransactionById");
		
		return model;
	}
	
	
	//***********************************************************************
	@RequestMapping(value="/sepaTransById", method = RequestMethod.GET)
	public ModelAndView transactionById(HttpServletRequest request, ModelAndView model) throws IOException, SAXException, ParserConfigurationException{
		String id = new String(request.getParameter("id"));
		
		Document d = parseDocument("http://localhost:8080/sepatrans/trx/"+id.replace(" ", "%20"));
		
		if(d!=null){
			model.addObject("sepatransactionById",d);
		}else{
			model.addObject("error","l'identifiant saisie "+id+" n'existe pas");
		}
		
		
		model.setViewName("sepaTransactionById");
		
		return model;
	}
	
	
	//***********************************************************************
	//un mapping vers l'accueil de saisi d'information d'une nouvelle transaction
	@RequestMapping(value="/depot", method = RequestMethod.GET)
	public ModelAndView transactionDepot(ModelAndView model) throws IOException{
		
		model.setViewName("sepaTransAjout");
		
		return model;
	}
	
	
	//***********************************************************************
	//un mapping 
	@RequestMapping(value="/ajouttransaction", method = RequestMethod.POST)
	public ModelAndView ajoutTransaction(HttpServletRequest request, ModelAndView model) throws IOException, SAXException, ParserConfigurationException{
		//construire le xml à envoyé vers le serveur
		String trx = new String("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
								+"<DrctDbtTxInf>"+
								"<PmtId>"+request.getParameter("PmtId")+"</PmtId>"+
								"<InstdAmt>"+request.getParameter("InstdAmt")+"</InstdAmt>"+
								"<DrctDbtTx>"+
									"<MndtRltdInf>"+
										"<MndtId>"+request.getParameter("MndtId")+"</MndtId>"+
										"<DtOfSgntr>"+request.getParameter("DtOfSgntr")+"</DtOfSgntr>"+
									"</MndtRltdInf>"+
								"</DrctDbtTx>"+
								"<DbtrAgt>"+
									"<FinInstnId>"+
										"<BIC>"+request.getParameter("BIC")+"</BIC>"+
									"</FinInstnId>"+
								"</DbtrAgt>"+
								"<Dbtr>"+
									"<Nm>"+request.getParameter("Nm")+"</Nm>"+
								"</Dbtr>"+
								"<DbtrAcct>"+
									"<Id>"+
										"<IBAN>"+request.getParameter("IBAN")+"</IBAN>"+
									"</Id>"+
								"</DbtrAcct>"+
								"<RmtInf>"+request.getParameter("RmtInf")+"</RmtInf>"+
							"</DrctDbtTxInf>");
		
		//on recois un document de la part de serveur soit il va validé la saisie et le mis
		//dans la base de données
		//soit il va nous retourné une erreur de syntaxe où une le ID saisie existe déja
		Document d = postSepaTransParse("http://localhost:8080/sepatrans/depot/", trx);
		if(d!=null)
			model.addObject("response", d);
		else
			model.addObject("error","LES VALEUR SIASIE SONT INCORRECT");
		
		model.setViewName("sepaTransAjout");
		return model;
	}
	
	//xette methode nous permet de parser le document xml
	public Document postSepaTransParse(String url, String xml) throws SAXException, ParserConfigurationException{
		try{
			String inputLine;
	        StringBuffer response = new StringBuffer();
	        
	        
			  URL objectUrl = new URL(url);
		        HttpURLConnection httpURLConnection = (HttpURLConnection) objectUrl.openConnection();
		        httpURLConnection.setRequestMethod("POST");
		        httpURLConnection.setRequestProperty("Content-Type",
		                "application/xml;charset=utf-8");
		        String urlParameters = xml;
		        httpURLConnection.setDoOutput(true);
		        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
		        dataOutputStream.writeBytes(urlParameters);
		        dataOutputStream.flush();
		        dataOutputStream.close();
		        String responseStatus = httpURLConnection.getResponseMessage();
		
		        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
		                httpURLConnection.getInputStream()));
		        
		        while ((inputLine = bufferedReader.readLine()) != null) {
		            response.append(inputLine);
		        }
		        bufferedReader.close();
		        return (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder()
			            .parse(new InputSource(new StringReader(response.toString())));
			}catch(IOException e){
				return null;
			}
	}
}
