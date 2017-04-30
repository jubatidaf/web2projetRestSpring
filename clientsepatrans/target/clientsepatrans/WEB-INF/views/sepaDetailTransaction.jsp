<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c' %>
<%@ page import="org.w3c.dom.NodeList" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="org.w3c.dom.Node" %>
<%@ page import="org.w3c.dom.Element" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SepaTransactionsAll</title>
    </head>
    <body>
    	<div><a href="home"> retour à l'accueil</a></div>
    	<div align="center">
    	<table border="1">
    	<th>Numero Transaction</th>
    	<th>Payment Identification : </th>
    	<th>Instructed Amount </th>
    	<th>Mandate Identifier</th>
    	<th>Date Of Signature</th>
    	<th>BIC</th>
    	<th>Name</th>
    	<th>IBAN</th>
    	<th>Remittance Information</th>
	<% Document docXML = (Document) request.getAttribute("SepaTransactionDetail"); 
	   NodeList listNode = docXML.getElementsByTagName("DrctDbtTxInf");
	 
	   for (int i = 0; i < listNode.getLength(); i++) {

	    		Node nodeElement = listNode.item(i);

	    		%>
	    		<tr>
	    		<td><br> <div style="font-weight: bold;"> <%out.print("Transaction "+(i+1));%> </div>
	    		</td>
	    		<%if (nodeElement.getNodeType() == Node.ELEMENT_NODE) {
	    			Element eElement = (Element) nodeElement;
	    			%> <td><br> <%out.print( eElement.getElementsByTagName("PmtId").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(eElement.getElementsByTagName("InstdAmt").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(eElement.getElementsByTagName("MndtId").item(0).getTextContent());
	    			%></td> <td><br> <%out.print( eElement.getElementsByTagName("DtOfSgntr").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(eElement.getElementsByTagName("BIC").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(eElement.getElementsByTagName("Nm").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(eElement.getElementsByTagName("IBAN").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(eElement.getElementsByTagName("RmtInf").item(0).getTextContent());
	    			%></td><br> </tr><%
	    			}
	    	}
	    	%>
	    	
	    	</table>
	    </div>
    </body>
</html>
