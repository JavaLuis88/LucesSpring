<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--CABECERA--%>
<jsp:include page="headerpublic.jsp">

	<jsp:param name="pagetitle" value="Index Publico" />


</jsp:include>
<%--CABECERA--%>


<header>

	<h1 class="text-center">Gestion de Luces</h1>

</header>

<section>

	<article id="pnllogin" class="container">
		<div class="row d-flex justify-content-center">
			<div
				class="col-12 col-sm-10 col-md-8 col-lg-6  col-xl-5 d-flex justify-content-center">
				<form class="container">

					<div class="form-group">
						<label for="txtusername">Usuario</label> <input type="text"
							class="form-control" name="txtusername" id="txtusername"
							maxlength="12" value="" placeholder="Username" required
							pattern="[A-Za-z0-9 ]{6,12}"> <small
							id="txtusernameerror" class="form-text text-danger d-none">El
							nombre de usuario de debe de tener entre 6 y 12 caracteres
							formados por letras mayúsculas, minúsculas o números</small>
					</div>
					<div class="form-group">
						<label for="txtpassword">Password</label> <input type="password"
							class="form-control" name="txtpassword" id="txtpassword"
							maxlength="12" value="" placeholder="Password" required
							pattern="[A-Za-z0-9 ]{6,12}"> <small
							id="txtpassworderror" class="form-text text-danger d-none">El
							nombre de usuario de debe de tener entre 6 y 12 caracteres
							formados por letras mayúsculas, minúsculas o números</small>
					</div>
					<button name="btnaccess" id="btnaccess" type="button"
						class="btn btn-primary">Acceder</button>
				</form>
			</div>
		</div>




	</article>




	<article id="pnlligth" class="container">
		<div class="row d-flex justify-content-center">
			<div
				class="col-12 col-sm-10 col-md-8 col-lg-6  col-xl-5 d-flex justify-content-center">

				<figure>
					<img src="images/ligthon.png" alt="Bombilla" id="imgbombilla"
						class="img-fluid">
					<figcaption id="figbombilla"></figcaption>
				</figure>


			</div>

		</div>



		<div class="row d-flex justify-content-center">
			<button id="btnconmutar" type="button" class="btn btn-primary mr-1">Encender</button>
			<button id="btncerrar" type="button">Cerrar</button>
		</div>


	</article>
	<div id="altbox">
		<div id="altalerta"
			class="alert alert-danger alert-dismissible fade fixed-top text-center"
			role="alert">
			<h4 class="alert-heading " id="hdralerta"></h4>
			<div id="bdyalerta"></div>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</div>
</section>





<%--PIE--%>

<jsp:include page="footerpublic.jsp" />

<%--PIE--%>
