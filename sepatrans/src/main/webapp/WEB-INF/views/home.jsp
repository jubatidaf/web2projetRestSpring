<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projet WEB2</title>
    </head>
    <body>
    	<div>
	        <h3>Projet Web2 Sepa Transaction avec: Spring et Service Rest</h3>
	        <h3>Réalisé par : TIDAF Juba </h3>
	        <h3>Date : 30 Avril 2017</h3>
	        
	        <table border="1">
	        <li border="1">
	       
	        <ul><a href="transaction">/transaction</a>(GET)Renvoie un flux XML contenant la liste des transactions détaillées</ul>
	        <ul><a href="resume">/resume</a> (GET) renvoie un flux xml de resumé des transactions</ul>
	        <ul><a href="stats">/stats</a>(GET) retourne un document xml des stats des transactions</ul>
	        <ul><a href="">/trx/id</a>(GET) renvoie une transaction par son id en tapant sur lien /trx/{id}</ul>
	        <ul><a href="">/depot</a> (POST) faut utilisé un client pour tester cette fonctionnalité (insatller l'extension Client Rest sur chrome ou executer un projet client que on va voir dans la partie client)</ul>
	        </li>
	        </table>
    	</div>
    </body>
</html>
