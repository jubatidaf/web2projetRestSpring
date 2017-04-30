<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c' %>
<%@ page import="org.w3c.dom.Document" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sepaTrnsaction By Id</title>
    </head>
    <body>

    	<br><br>
    	<div><a href="home"> retour à l'accueil</a></div>
    	<div align="center">
	    	<form action="sepaTransById" method="get">
			<table>
				<tr>
					<td>Payment Identification :</td>
					<td>
						<input name="id" required="required" value=""/>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><br><input type="submit" value="Rechercher"></td>
				</tr>
			</table>
			</form>
	    </div>
	    
	    <div align="center">
	    
	    	<% Document docXML = (Document) request.getAttribute("sepatransactionById");
	    		String error = (String) request.getAttribute("error");
	    	if(docXML!=null){
	    			%> <table border="1">

    	<th>Payment Identification : </th>
    	<th>Instructed Amount </th>
    	<th>Mandate Identifier</th>
    	<th>Date Of Signature</th>
    	<th>BIC</th>
    	<th>Name</th>
    	<th>IBAN</th>
    	<th>Remittance Information</th>
    	<tr>
    				<td><br> <%out.print(docXML.getElementsByTagName("PmtId").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(docXML.getElementsByTagName("InstdAmt").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(docXML.getElementsByTagName("MndtId").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(docXML.getElementsByTagName("DtOfSgntr").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(docXML.getElementsByTagName("BIC").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(docXML.getElementsByTagName("Nm").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(docXML.getElementsByTagName("IBAN").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(docXML.getElementsByTagName("RmtInf").item(0).getTextContent());
			}
			if(error!=null){
    			%></td></tr></table> <br> <%out.print(error);
			}%>
			
	    </div>
    </body>
</html>
