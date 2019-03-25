var imgligthon;
var imgligthoff;
var strpanel;
var continuar;
var estado;
var restapi;
imgligthon = new Image();
imgligthon.src = "images/ligthon.png";
imgligthoff = new Image();
imgligthoff.src = "images/ligthoff.png";

function inicio() {

	strpanel = "pnllogin";
	continuar = true;
	estado = false;
	document.getElementById("btnaccess").onclick = identificacion
	document.getElementById("btnconmutar").onclick = conmutar;
	document.getElementById("btncerrar").onclick = cerrar;

}

function identificacion() {

	var restoperation;
	var retval;
	document.getElementById("txtusernameerror").className = "form-text text-danger d-none";
	document.getElementById("txtpassworderror").className = "form-text text-danger d-none";
	document.getElementById("altalerta").className = "alert alert-danger alert-dismissible fade  fixed-top text-center"

	if (document.getElementById("txtusername").validity.valid == false) {

		document.getElementById("txtusernameerror").className = "form-text text-danger";
	} else if (document.getElementById("txtpassword").validity.valid == false) {

		document.getElementById("txtpassworderror").className = "form-text text-danger";
	} else {

		try {

			restobj = new Restapi(document.getElementById("txtusername").value,
					document.getElementById("txtpassword").value, funcionevento)
			restobj.login()

		} catch (e) {

			showerroralert("Error", "Error inesperado", cerrar);

		}

	}

}

function conmutar() {

	
	if (estado==true) {
		
		restobj.lightoff();
	}
	else {
		
		restobj.lighton();
		
	}
}

function cerrar() {
	restobj.lightclose();
}

function funcionevento(restapi, restoperation) {

	var alertbox;

	if (restoperation.operationcode == 0) {

		if (restoperation.operationname == "LOGIN") {

			document.getElementById(strpanel).style.display = "none";
			strpanel = "pnlligth";
			continuar = true;

		}

		else if (restoperation.operationname == "LIGHTSTATUS") {

			document.getElementById(strpanel).style.display = "block";
			
			estado=restoperation.info;
			if (estado==true) {

				document.getElementById("imgbombilla").src = imgligthon.src
				document.getElementById("btnconmutar").innerHTML = "Apagar"

			} else {

				document.getElementById("imgbombilla").src = imgligthoff.src
				document.getElementById("btnconmutar").innerHTML = "Encender"

			}

		
		}

		else if (restoperation.operationname == "CLOSE") {

			alertbox = "<div id=\"altalerta\" class=\"alert alert-danger alert-dismissible fade fixed-top text-center\" role=\"alert\">";
			alertbox = alertbox
					+ "	<h4 class=\"alert-heading\" id=\"hdralerta\"></h4>";
			alertbox = alertbox + "	<div id=\"bdyalerta\"></div>";
			alertbox = alertbox
					+ "	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">";
			alertbox = alertbox + "		<span aria-hidden=\"true\">&times;</span>";
			alertbox = alertbox + "	</button>";
			alertbox = alertbox + "</div>";
			document.getElementById("altbox").innerHTML = alertbox;
			continuar = false;
			status=false;
			document.getElementById(strpanel).style.display = "none";
			document.getElementById("pnllogin").style.display = "block";
			strpanel = "pnllogin";
			
		}

		if (continuar == true) {
			restapi.lightstatus();

		}
		
		
	} else  if (restoperation.operationname != "LIGHTSTATUS") {
		continuar=false;
		showerroralert("Error", restoperation.operationcodedescription, cerrar);
		
	}
}

function showerroralert(title, message, closefunction) {

	document.getElementById("hdralerta").innerHTML = title;
	document.getElementById("bdyalerta").innerHTML = message;
	if (closefunction != undefined && closefunction != null) {

		$('#altalerta').on('closed.bs.alert', closefunction);
	}
	document.getElementById("altalerta").className = "alert alert-danger alert-dismissible fade show fixed-top text-center"

}

window.onload = inicio;
