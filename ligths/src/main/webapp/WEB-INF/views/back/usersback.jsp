<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--CABECERA--%>

<jsp:include page="headerback.jsp">
	<jsp:param name="pagetitle" value="Usuarios del sistema" />
	<jsp:param name="currentpage" value="userspage" />
</jsp:include>
<%--CABECERA--%>

<section id="mainsection" data-username="${model.sessionusername}"
	data-currentpage="userspage">



	<article class="container-fluid d-flex justify-content-center">

		<table id="tableusers" class="table">
			<thead>
				<tr>
					<th><input name="chkselectall" id="chkselectall"
						type="checkbox" value="1"></th>
					<th>Usuario</th>
					<th>Roles</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="usertablebody">
			</tbody>
		</table>

	</article>



		<div id="generalformusersuscess" class="text-center"></div>
		<div id="generalformusererrors" class="text-danger text-center"></div>


	<article id="formuserlayer"
		class="container-fluid d-flex justify-content-center">






		<form method="post" id="formlogin" action="">
			<div class="form-group">
				<label for="username">Nombre de usuario</label> <input
					name="username" id="username" type="text" value="" maxlength="12"
					class="form-control"> <label id="labelerrousername"
					class="text-danger"></label>
			</div>
			<div class="form-group">
				<label for="password">Contraseña</label> <input name="password"
					id="password" type="password" value="" maxlength="12"
					class="form-control"> <label id="labelerrorpassword"
					class="text-danger"></label>

			</div>
			<div class="form-group">
				<label for="repassword">Repetir Contraseña</label> <input
					name="repassword" id="repassword" type="password" value=""
					maxlength="12" class="form-control"> <label
					id="labelerrorrepassword" class="text-danger"></label>

			</div>
			<div class="form-check">
				<input type="checkbox" class="form-check-input" id="chkadmin">
				<label class="form-check-label" for="chkadminin">Administrador</label>
			</div>
			<button id="btnsave" type="button" class="btn btn-primary">Grabar</button>
			<button id="btnclose" type="button" class="btn btn-primary">Cerrar</button>

		</form>


	</article>



	<article class="container-fluid d-flex justify-content-center">
		<a href="javascript:void(0);" id="lnkadduser">|Nuevo|</a> <a
			href="javascript:void(0);" id="lnkdeleteuser">|Borrar|</a> <a
			href="javascript:void(0);" id="lnkdreload">|Recargar Usuarios|</a>

	</article>

</section>

<%--PIE--%>

<jsp:include page="footerback.jsp" />

<%--PIE--%>
