<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c' %>
<%@ page import="org.w3c.dom.Document" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sepaTransaction Stats</title>
    </head>
    <body>
<div><a href="home"> retour à l'accueil</a></div>
    	<div align="center" style="font-weight: bold;">
    	<table border="1">
    	<th>Nombre de Transactions</th>
    	<th>Montant Total</th>
    	<tr>
	    <% Document doc = (Document) request.getAttribute("statistiques"); 
	    			%> <td><br> <%out.print(doc.getElementsByTagName("NombreTransactions").item(0).getTextContent());
	    			%> </td><td><br> <%out.print(doc.getElementsByTagName("MontantTotal").item(0).getTextContent());
	    	%></td><br> </tr>
	    	</table>
	    </div>
    </body>
</html>
