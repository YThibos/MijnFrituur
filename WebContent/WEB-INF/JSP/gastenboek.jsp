<%@ page contentType='text/html' pageEncoding='UTF-8' session='true'%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="styles/default.css" />
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name="title" value="Mijn frituur" />
</c:import>
</head>

<body>
	<vdab:menu/>
	
	<section class="gastenboekToevoegen">
		<h1>Gastenboek</h1>
		<c:choose>
			<c:when test="${sessionScope.admin}">
			<h3>Welcome mister Admin.</h3>
			</c:when>
		</c:choose>
		
		<form action="" method="post">
			<label>Naam:</label>
			<input name="naam" type="text" maxlength="50" required />
			<label>Bericht:</label>
			<textarea name="bericht" cols="50" rows="4" maxlength="300" required></textarea>
			<input type="submit" value="Toevoegen" />
		</form>
	</section>
	
	<section class="gastenboek">
		<c:if test="${sessionScope.admin}">
			<form method="post">
		</c:if>
		<c:forEach var="entry" items="${entries}">
		<div class="entry">
		<c:if test="${sessionScope.admin}">
			<input type="checkbox" name="id" value="${entry.id}"/>Delete
		</c:if>
			<div class="entryinfo">${entry.datum} ${entry.naam}</div>
			<div class="entrymessage">${entry.bericht}</div>
		</div>
		</c:forEach>
		<c:if test="${sessionScope.admin}">
			
			<input type="submit" name="uitloggen" value="Uitloggen"/>
			<input type="submit" name="verwijderen" value="Verwijder geselecteerde items"/>
			</form>
		</c:if>
	</section>

</body>

</html>