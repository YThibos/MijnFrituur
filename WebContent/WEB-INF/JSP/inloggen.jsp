<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<vdab:menu />

	<h1>Admin login</h1>
	<form action="" method="post">
		<label>Wachtwoord: <span>${fout}</span><input type="password" name="password" required /></label>
		<input type="submit" value="Log in"/>
	</form>

</body>

</html>