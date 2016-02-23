<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE HTML>
<html>

<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Zoek de Friet'></c:param>
</c:import>
</head>

<body>
	<c:import url='/WEB-INF/JSP/menu.jsp'></c:import>

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
		
		<!-- 
		<button type='submit' name='deurnr' value='1'>
			<img src='<c:url value="/images/deurtoe.png"/>' alt='deur toe' />
		</button>
		<button type='submit' name='deurnr' value='2'>
			<img src='<c:url value="/images/deurtoe.png"/>' alt='deur toe' />
		</button>
		<button type='submit' name='deurnr' value='3'>
			<img src='<c:url value="/images/deurtoe.png"/>' alt='deur toe' />
		</button>
		<button type='submit' name='deurnr' value='4'>
			<img src='<c:url value="/images/deurtoe.png"/>' alt='deur toe' />
		</button>
		<button type='submit' name='deurnr' value='5'>
			<img src='<c:url value="/images/deurtoe.png"/>' alt='deur toe' />
		</button>
		<button type='submit' name='deurnr' value='6'>
			<img src='<c:url value="/images/deurtoe.png"/>' alt='deur toe' />
		</button>
		<button type='submit' name='deurnr' value='7'>
			<img src='<c:url value="/images/deurtoe.png"/>' alt='deur toe' />
		</button>
		 -->
	</div>

</body>

</html>