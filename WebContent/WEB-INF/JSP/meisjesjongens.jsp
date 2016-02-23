<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE HTML>
<html>

<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Meisjes Jongens'/>
</c:import>
</head>

<body class='${cookie.meisjesjongens.value}'>
<c:import url='/WEB-INF/JSP/menu.jsp'/>

<form method='post'>
	<input type='submit' name='meisjesjongens' value='meisjes'>
	<input type='submit' name='meisjesjongens' value='jongens'>
</form>

</body>

</html>