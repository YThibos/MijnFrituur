<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>

<!DOCTYPE HTML>
<html lang='nl'>

<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="styles/default.css" />
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Mijn frituur' />
</c:import>
</head>

<body>

	<vdab:menu/>

	<section class="content">
		<h1>Statistiek</h1>
		<dl>
			<c:forEach var="entry" items="${requestsMap}">
				<dt>${entry.key}</dt>
				<dd>${entry.value }</dd>
			</c:forEach>
			<dt>Totaal aantal requests sinds start</dt>
			<dd>${requestsCounter}</dd>
		</dl>
	</section>

</body>

</html>