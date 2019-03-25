<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>acceso no autorizadoe</title>
</head>
<body>

<header>
<h1>Acesso no autorizado</h1>
</header>
No tienes permiso para acceder a esta zona.
<c:url value="/back/logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="formlogout">

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}">

				<button type="submit">Cerrar Sesi&oacute;n</button>
			</form>

</body>
</html>