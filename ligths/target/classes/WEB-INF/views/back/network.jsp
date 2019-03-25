<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--CABECERA--%>

<jsp:include page="headerback.jsp">
	<jsp:param name="pagetitle" value="Configuraci&oacute;n de red" />
	<jsp:param name="currentpage" value="networkpage" />
</jsp:include>
<%--CABECERA--%>

<section id="mainsection" data-username="${model.sessionusername}"
	data-currentpage="networkpage">







	<div id="generalformusersuscess" class="text-center"></div>
	<div id="generalformusererrors" class="text-danger text-center"></div>


	<article id="formuserlayer"
		class="container-fluid d-flex justify-content-center">






		<form id="frmnetwork">
			<div class="form-group">
				<label for="ssid">SSID</label> <input name="ssid" id="ssid"
					type="text" value="${model.formnetwork.ssid}" maxlength="30"
					class="form-control"> <label id="labelerrorssid"
					class="text-danger"></label>

			</div>
			<div class="form-group">
				<label for="password">Contraseña</label> 
				<input name="password"
					id="password" type="password" value="${model.formnetwork.password}" maxlength="30"
					class="form-control"> <label
					id="labelerrorpassword" class="text-danger"></label>

			</div>
			
			
				<div class="form-check">
				<input type="checkbox" class="form-check-input" id="chkshowpassword">
				<label class="form-check-label" for="chkshowpassword">Mostrar contraseña</label>
			</div>
			
			<div class="form-group">
				<label for="channel">Canal</label> <select name="channel"
					id="channel" class="form-control">



					<c:choose>

						<c:when test="${model.formnetwork.channel == 1 }">
							<option value="1" selected>1</option>
						</c:when>


						<c:otherwise>
							<option value="1">1</option>
						</c:otherwise>
					</c:choose>


					<c:choose>

						<c:when test="${model.formnetwork.channel == 6 }">
							<option value="6" selected>6</option>
						</c:when>


						<c:otherwise>
							<option value="6">6</option>
						</c:otherwise>
					</c:choose>


					<c:choose>

						<c:when test="${model.formnetwork.channel == 11 }">
							<option value="11" selected>1</option>
						</c:when>


						<c:otherwise>
							<option value="11">11</option>
						</c:otherwise>
					</c:choose>
				
				</select> 
				<label id="labelerrorchannel" class="text-danger"></label>

			</div>

			<div class="form-group">
				<label for="securitytype">Seguridad</label> <select name="securitytype"
					id="securitytype" class="form-control">
		



					<c:choose>

						<c:when test="${model.formnetwork.securitytype == 1 }">
							<option value="1" selected>WPA/PSK</option>
						</c:when>


						<c:otherwise>
							<option value="1">WPA/PSK</option>
						</c:otherwise>
					</c:choose>

					<c:choose>

						<c:when test="${model.formnetwork.securitytype == 2 }">
							<option value="2" selected>WPA2/PSK</option>
						</c:when>


						<c:otherwise>
							<option value="2">WPA2/PSK</option>
						</c:otherwise>
					</c:choose>

				
				
				</select> 
				<label id="labelerrorsecuritytype" class="text-danger"></label>

			</div>




		
			<button id="btnsave" type="button" class="btn btn-primary">Grabar</button>
	
		</form>



	</article>


	

</section>

<%--PIE--%>

<jsp:include page="footerback.jsp" />

<%--PIE--%>
