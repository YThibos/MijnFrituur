<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>

<!DOCTYPE HTML>
<html>

<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Sauzen'/>
</c:import>
</head>

<body>
	<vdab:menu/>

	<h1>Sauzen</h1>
	<form method='post'>
	<ul class='zonderbolletjes'>
		<c:forEach items='${sauzen }' var='saus'>
			<li>
				<input class='verwijderCheck' type='checkbox' name='saus' value='${saus.naam}'>
				<h3>${saus.naam}</h3>
				<img alt="${saus.naam}" src="images/${saus.naam}.png">
				<p>
				<c:forEach items='${saus.ingredienten}' var='ingredient' varStatus='status'>
					${ingredient}<c:if test='${!status.last}'>,	</c:if>
				</c:forEach>
				</p>
			</li>
		</c:forEach>
	</ul>
		<input type='submit' value='Verwijder selectie'>
	</form>
	
	<h3>Selecteer sauzen op basis van ingrediënten</h3>
	<form>
		<ul class='zonderbolletjes'>
			<c:forEach var='ingredient' items='${ingredienten}'>
				<li><label>
					<input type='checkbox' name='ingredient' value='${ingredient}'> 
					<c:out value='${ingredient}' />
				</label></li>
			</c:forEach>
		</ul>
		<input type='submit' value='Toon mijn keuzes'>
	</form>
</body>

</html>