<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>

<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name="title" value="Saus Raden"/>
	</c:import>
</head>

<body>
	<c:import url="/WEB-INF/JSP/menu.jsp"/>
	
	<h1>Saus Raden</h1>
	
	<h3>Te raden woord: </h3>
	<div class="teRadenWoord"><p>${teRadenSaus }</p></div>
	
	<div class="hangman">
		<img alt="hangman ${aantalFouteGokken}" src="<c:url value="/images/${aantalFouteGokken}.png" />" />
	</div>
	
	<span>${status} </span>
	<form method="post" id="gokform">
		<label>Ik gok de letter ..
			<input name="gegokteLetter" type="text" autocomplete="off" maxlength="1" autofocus required>
		</label>
		<input type="submit" value="Gok" id="gokknop">
	</form>
	<form method="post">
	<input name="nieuwSpel" type="submit" value="Nieuw spel">
	</form>

<script>
document.getElementById("gokform").onsubmit = function() {
if ( ! navigator.cookieEnabled) { (1)
alert("Dit werkt enkel als cookies aanstaan"); (2)
return false; (3)
}
document.getElementById("toevoegknop").disabled=${disableKnop};
};
</script>
</body>

</html>