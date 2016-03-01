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
	
	<section class="gastenboekToevoegen" hidden="${formHidden}">
		<h1>Gastenboek</h1>
		
		<form action="" method="post">
			<label>Naam:</label>
			<input name="naam" type="text" maxlength="50" required />
			<label>Bericht:</label>
			<textarea name="bericht" cols="50" rows="4" maxlength="300" required></textarea>
			<input type="submit" value="Toevoegen" />
		</form>
	</section>
	
	<section class="gastenboek">
		<c:forEach var="entry" items="${entries}">
		<div class="entry">
			<div class="entryinfo">${entry.datum} ${entry.naam}</div>
			<div class="entrymessage">${entry.bericht}</div>
		</div>
		</c:forEach>
	</section>

</body>

</html>