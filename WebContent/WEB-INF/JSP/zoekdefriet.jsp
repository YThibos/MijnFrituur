<%@page contentType='text/html' pageEncoding='UTF-8' session='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>

<!DOCTYPE HTML>
<html>

<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Zoek de Friet'></c:param>
</c:import>
</head>

<body>
	<vdab:menu/>

	<div class='deuren'>
		<form method='post'>
		<span>${gewonnen}</span>
		<c:forEach var='deur' items='${deuren}' varStatus='status'>
			<button type='submit' name='deurnr' value='${status.count}'>
				<img src='<c:url value='/images/${deur.value}.png'/>' alt='${deur.value}'/>
			</button>
		</c:forEach>
		<input type='submit' name='nieuwSpel' value='Nieuw Spel'>
		</form>
		
	</div>
	
	

</body>

</html>