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
        <title>sepaTransaction Resume</title>
    </head>
    <body>
<div><a href="home"> retour à l'accueil</a></div>
    	<div align="center">
    	<table border="1">
    	<th>Numero Transaction</th>
    	<th>Payment Identification : </th>
    	<th>Instructed Amount </th>
    	<th>Date Of Signature</th>
    	
	    <% Document docXML = (Document) request.getAttribute("listTransactions"); 
	    	NodeList nList = docXML.getElementsByTagName("Transaction");
	    	for (int i = 0; i < nList.getLength(); i++) {

	    		Node listNode = nList.item(i);

	    		%>
	    		<tr>
	    		
	    		<td><br> <div style="font-weight: bold;"> <%out.print("Transaction "+(i+1));%> </div>
	    		</td>
	    		<%if (listNode.getNodeType() == Node.ELEMENT_NODE) {

	    			Element eElement = (Element) listNode;

	    			%> <td><br> <%out.print(eElement.getElementsByTagName("Num").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(eElement.getElementsByTagName("Identifiant").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(eElement.getElementsByTagName("Montant").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(eElement.getElementsByTagName("Date").item(0).getTextContent());
	    			%> </td> <tr><%
	    			}
	    	}
	    	%>
	    	
	    	</table>
	    </div>
    </body>
</html>
