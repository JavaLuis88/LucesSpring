<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%--CABECERA--%>

<jsp:include page="headerback.jsp">
	<jsp:param name="pagetitle" value="Identificaci&oacute;n de usuario" />
	<jsp:param name="currentpage" value="loginpage" />
</jsp:include>
<%--CABECERA--%>


<section id="mainsection" data-username="" data-currentpage="loginpage">
		<c:if test="${param.error != null}">

			<article class="container-fluid d-flex justify-content-center text-danger text-center">Error de
				identificaci&oacute;n</article>
		
		
		</c:if>



	<article class="container-fluid d-flex justify-content-center">
		<c:url value="/back/index.html" var="loginUrl" />

		<form action="${loginUrl}" method="post" id="formlogin">
			<div class="form-group">
				<label for="username"><fmt:message key="back.usernamelabel" /></label>
				<input name="username" id="username" type="text" value=""
					maxlength="12" class="form-control"> <label
					id="labelerrousername" class="text-danger"></label>
			</div>
			<div class="form-group">
				<label for="password"><fmt:message key="back.passwordlabel" /></label>
				<input name="password" id="password" type="password" value=""
					maxlength="12" class="form-control"> <label
					id="labelerrorpassword" class="text-danger"></label>

			</div>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
			<button type="submit" class="btn btn-primary">
				<fmt:message key="back.sendbutton" />
			</button>
		</form>




	</article>
</section>


<%--PIE--%>

<jsp:include page="footerback.jsp" />

<%--PIE--%>
