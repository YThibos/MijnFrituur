<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resourceBundles.index"/>

<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>

<!DOCTYPE HTML>
<html lang="nl">

<head>
<meta http-equiv="Content-Type" content="text/html"/>
<link rel="stylesheet" href="styles/default.css"/>
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name="title" value="Mijn frituur" />
</c:import>
</head>

<body>

	<vdab:menu/>

	<h1>Frituur Frida</h1>
	<h2><fmt:message key="vandaagOpen"/></h2>
	<img src="images/${openGesloten}<fmt:message key="suffix"/>.png" alt="${openGesloten}<fmt:message key="suffix"/>"/>
	
	<h3><fmt:message key="adres"/></h3>
	<dl>
		<dt><fmt:message key="straat"/></dt>
		<dd>${adres.straat} ${adres.huisNr}</dd>
		<dt><fmt:message key="gemeente"/></dt>
		<dd>${adres.gemeente}</dd>
	</dl>
	
	<div id="helpdesk">${helpdesknummer}</div>
</body>
</html>