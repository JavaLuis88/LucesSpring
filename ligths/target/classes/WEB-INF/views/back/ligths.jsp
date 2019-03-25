<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--CABECERA--%>

<jsp:include page="headerback.jsp">
	<jsp:param name="pagetitle" value="Configuraci&oacute;n de luces" />
	<jsp:param name="currentpage" value="ligthsconfig" />
</jsp:include>
<%--CABECERA--%>

<section id="mainsection" data-username="${model.sessionusername}"
	data-currentpage="ligthsconfig">







	<div id="generalformusersuscess" class="text-center"></div>
	<div id="generalformusererrors" class="text-danger text-center"></div>


	<article id="formuserlayer"
		class="container-fluid d-flex justify-content-center">






		<form id="frmligths">
			<div class="form-group">
				<label for="secretkey">Clave Secreta</label> <input name="secretkey" id="secretkey"
					type="text" value="${model.formligths.secretkey}" maxlength="16"
					class="form-control"><label id="labelerrorsecretkey"
					class="text-danger"></label>

			</div>
		<div class="form-group">
				<label for="pin">Pin</label> <select name="pin"
					id="pin" class="form-control">



					<c:choose>

						<c:when test="${model.formligths.pin == 1 }">
							<option value="1" selected>1</option>
						</c:when>


						<c:otherwise>
							<option value="1">1</option>
						</c:otherwise>
					</c:choose>

					<c:choose>

						<c:when test="${model.formligths.pin == 2 }">
							<option value="2" selected>2</option>
						</c:when>


						<c:otherwise>
							<option value="2">2</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 3 }">
							<option value="3" selected>3</option>
						</c:when>


						<c:otherwise>
							<option value="3">3</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 4 }">
							<option value="4" selected>4</option>
						</c:when>


						<c:otherwise>
							<option value="4">4</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 5 }">
							<option value="5" selected>5</option>
						</c:when>


						<c:otherwise>
							<option value="5">5</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 6 }">
							<option value="6" selected>6</option>
						</c:when>


						<c:otherwise>
							<option value="6">6</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 7 }">
							<option value="7" selected>7</option>
						</c:when>


						<c:otherwise>
							<option value="7">7</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 8 }">
							<option value="8" selected>1</option>
						</c:when>


						<c:otherwise>
							<option value="8">8</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 9 }">
							<option value="9" selected>9</option>
						</c:when>


						<c:otherwise>
							<option value="9">9</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 10 }">
							<option value="10" selected>10</option>
						</c:when>


						<c:otherwise>
							<option value="10">10</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 11 }">
							<option value="11" selected>11</option>
						</c:when>


						<c:otherwise>
							<option value="11">11</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 12 }">
							<option value="12" selected>1</option>
						</c:when>


						<c:otherwise>
							<option value="12">12</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 13 }">
							<option value="13" selected>13</option>
						</c:when>


						<c:otherwise>
							<option value="13">13</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 15 }">
							<option value="15" selected>1</option>
						</c:when>


						<c:otherwise>
							<option value="15">15</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 16 }">
							<option value="16" selected>16</option>
						</c:when>


						<c:otherwise>
							<option value="16">16</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 17 }">
							<option value="17" selected>1</option>
						</c:when>


						<c:otherwise>
							<option value="17">17</option>
						</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${model.formligths.pin == 18 }">
							<option value="18" selected>18</option>
						</c:when>


						<c:otherwise>
							<option value="18">18</option>
						</c:otherwise>
					</c:choose>
		

				
				</select> 
				<label id="labelerrorpin" class="text-danger"></label>

			</div>

		
			<button id="btnsave" type="button" class="btn btn-primary">Grabar</button>
	
		</form>



	</article>


	

</section>

<%--PIE--%>

<jsp:include page="footerback.jsp" />

<%--PIE--%>
