<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"
	session="false"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<!DOCTYPE HTML>
<html lang='nl'>

<head>
<meta http-equiv="Content-Type" content="text/html"/>
<link rel="stylesheet" href="styles/default.css"/>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Mijn frituur' />
</c:import>
</head>

<body>

	<c:import url="/WEB-INF/JSP/menu.jsp" />

	<h1>Frituur Frida</h1>
	<h2>Wij zijn vandaag ${openGesloten}</h2>
	<img src="images/${openGesloten}.png" alt="${openGesloten}"/>
	
	<h3>Adres</h3>
	<dl>
		<dt>Straat</dt>
		<dd>${adres.straat} ${adres.huisNr}</dd>
		<dt>Gemeente</dt>
		<dd>${adres.gemeente}</dd>
	</dl>
	
	<div id="helpdesk">${helpdesknummer}</div>
</body>
</html>