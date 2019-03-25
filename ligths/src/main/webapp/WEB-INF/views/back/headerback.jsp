<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8" />
<meta name="robots" content="NOARCHIVE, NOODP, NOYDIR">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<link rel="stylesheet" href="../vendor/bootstrap/css/bootstrap.css">
<script src="../vendor/tether/dist/js/tether.js"></script>
<script src="../vendor/jquery/dist/jquery.js"></script>
<script src="../vendor/popper.js/dist/umd/popper.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.js"></script>
<script src="../js/AjaxObject.js"></script>
<script src="../js/back.js"></script>
<%-- 
<c:choose>
	<c:when test="${param.loginpage eq '1' }">
		<title><fmt:message key="back.loginpage.title" /></title>
	</c:when>
	<c:otherwise>
		<title>${domaindata.pagetitle}</title>

	</c:otherwise>
</c:choose>
--%>
<title>${param.pagetitle}</title>



</head>
<body>
	<header>

		<%-- 
		<c:choose>
			<c:when test="${param.loginpage eq '1' }">
				<h1 class="text-center">
					<fmt:message key="back.loginpage.title" />
				</h1>
			</c:when>
			<c:otherwise>
				<h1 class="text-center">${domaindata.pagetitle}</h1>

			</c:otherwise>
		</c:choose>
--%>



		<h1 class="text-center">${param.pagetitle}</h1>
	</header>
	<c:if test="${param.currentpage ne 'loginpage' }">

		<nav class="text-center">

			<c:choose>
				<c:when test="${param.currentpage eq 'userspage' }">
					|Usuarios|
				</c:when>
				<c:otherwise>
					<a href="users.html">|Usuarios|</a>
				</c:otherwise>
			</c:choose>


			<c:choose>
				<c:when test="${param.currentpage eq 'networkpage' }">
					|Red|
				</c:when>
				<c:otherwise>
					<a href="network.html">|Red|</a>
				</c:otherwise>
			</c:choose>
<%-- 
			<c:choose>
				<c:when test="${param.currentpage eq 'taskpage' }">
					|Tareas programadas|
				</c:when>
				<c:otherwise>
					<a href="task.html">|Tareas programadas|</a> 
				</c:otherwise>
			</c:choose>

--%>					



			<c:choose>
				<c:when test="${param.currentpage eq 'ligthsconfig' }">
					|Configutraci&oacute;n De Luces|
				</c:when>
				<c:otherwise>
					<a href="ligths.html">|Configuraci&oacute;n De Luces|</a> 
				</c:otherwise>
			</c:choose>

					



			<a	href="javascript:void(0);" id="closesession">|Cerrar Sesi&oacute;n|</a>
			<a	href="javascript:void(0);" id="reboot">|Reiniciar|</a>
			<a	href="javascript:void(0);" id="poweroff">|Apagar|</a>

			<c:url value="/back/logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="formlogout">

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}">


			</form>

		</nav>


	</c:if>