var createrecord;

function sendloginform() {

	var user;
	var pass;
	var retval

	retval = true;

	user = document.getElementById("username").value.trim();
	pass = document.getElementById("password").value.trim();

	if (user == null || user.trim() == "" || user.length < 6
			|| user.length > 12) {

		document.getElementById("labelerrousername").innerHTML = "Nombre de usuario no válido debe contender ente 8 y 12 caracteres";
		retval = false;
	}

	else if (pass == null || pass.trim() == "" || pass.length < 6
			|| pass.length > 12) {

		document.getElementById("labelerrorpassword").innerHTML = "Contraseña no válida debe contender ente 8 y 12 caracteres";
		retval = false;
	}

	return retval;

}

function sendlogoutform() {

	document.getElementById("formlogout").submit();

}

function drawsystemuserstable() {

	var ajaxobj;

	ajaxobj = getAjaxHandler();

	if (ajaxobj != null) {

		ajaxobj.onreadystatechange = function() {

			var jsonobj;
			var row;
			var colum;
			var element;
			var text;
			var tablebody
			jsonobj = null;

			if (ajaxobj.readyState == 4) {

				if (ajaxobj.status == 403
						|| converttexttojson(ajaxobj.responseText) == undefined) {
					sendlogoutform();
				}

				else if (ajaxobj.status == 200) {

					jsonobj = converttexttojson(ajaxobj.responseText);
					tablebody = document.getElementById("usertablebody")
					tablebody.innerHTML = "";
					if (jsonobj.length <= 0) {

						document.getElementById("tableusers").style.display = "none"
						document.getElementById("lnkdeleteuser").style.display = "none"

					} else {
						document.getElementById("tableusers").style.display = "inline-table"
						document.getElementById("lnkdeleteuser").style.display = "inline"

					}

					for (var i = 0; i < jsonobj.length; i++) {

						row = document.createElement("tr");

						colum = document.createElement("td");
						element = document.createElement("input")
						element.name = "chk" + i
						element.id = "chk" + i
						element.type = "checkbox"
						element.value = "1"
						element.setAttribute("data-userreg", JSON
								.stringify(jsonobj[i]));
						element.setAttribute("class", "checkboxusers");

						if (jsonobj[i].username == document.getElementById(
								"mainsection").getAttribute("data-username")) {

							element.disabled = true;

						}

						colum.appendChild(element);
						row.appendChild(colum);

						colum = document.createElement("td");
						text = document.createTextNode(jsonobj[i].username)
						colum.appendChild(text);
						row.appendChild(colum);

						colum = document.createElement("td");
						text = document.createTextNode(jsonobj[i].roles
								.join(","))
						colum.appendChild(text);
						row.appendChild(colum);

						colum = document.createElement("td");
						element = document.createElement("a");
						element.href = "javascript:void(0);"
						element.setAttribute("data-userreg", JSON
								.stringify(jsonobj[i]));
						element.onclick = edituser;
						text = document.createTextNode("Editar")
						element.appendChild(text)
						colum.appendChild(element);
						row.appendChild(colum);
						tablebody.appendChild(row)

					}

				}

			}

		}

		ajaxobj.open("GET", "getuserinfo", true)
		ajaxobj.send(null);

	} else {

		alert("Error al cargar la pagina")

	}
}

function converttexttojson(text) {

	var jsonobj;
	// jsonobj=null;

	try {

		jsonobj = JSON.parse(text);

	} catch (e) {

	}
	return jsonobj;
}

function edituser() {

	var userobj;
	clearformusererrorlabels();
	clearformusererrorfields();
	userobj = JSON.parse(this.getAttribute("data-userreg"))

	createrecord = false;
	document.getElementById("username").value = userobj.username;
	if (userobj.roles.indexOf("ROLE_ADMIN") != -1) {

		document.getElementById("chkadmin").checked = true;

	} else {

		document.getElementById("chkadmin").checked = false;

	}
	document.getElementById("username").disabled = true;

	if (userobj.username == document.getElementById("mainsection")
			.getAttribute("data-username")) {

		document.getElementById("chkadmin").disabled = true;

	}

	else {

		document.getElementById("chkadmin").disabled = false;

	}

	document.getElementById("formlogin").style.display = "block";

}

function createuser() {

	createrecord = true;
	clearformusererrorlabels();
	clearformusererrorfields();
	document.getElementById("username").value = "";
	document.getElementById("password").value = "";
	document.getElementById("repassword").value = "";
	document.getElementById("chkadmin").checked = false;
	document.getElementById("username").disabled = false;
	document.getElementById("chkadmin").disabled = false;
	document.getElementById("formlogin").style.display = "block";

}

function closeuserform() {

	document.getElementById("formlogin").style.display = "none";

}

function createedituser() {
	var objsend;
	var ajaxobj;
	var token;
	var header;

	clearformusererrorlabels();
	if (document.getElementById("username").value == ""
			|| document.getElementById("username").value.trim() != document
					.getElementById("username").value
			|| document.getElementById("username").value.length < 6
			|| document.getElementById("username").value.length > 12) {

		document.getElementById("labelerrousername").innerHTML = "Nombre de usuario no válido debe de tener ente 6 y 12 caracteres";

	}

	else if (createrecord == true
			&& (document.getElementById("password").value == ""
					|| document.getElementById("password").value.trim() != document
							.getElementById("password").value
					|| document.getElementById("password").value.length < 6 || document
					.getElementById("password").value.length > 12)) {

		document.getElementById("labelerrorpassword").innerHTML = "Contraseña no válida debe de tener ente 6 y 12 caracteres";

	}

	else if (createrecord == false
			&& document.getElementById("password").value != ""
			&& (document.getElementById("password").value.trim() != document
					.getElementById("password").value
					|| document.getElementById("password").value.length < 6 || document
					.getElementById("password").value.length > 12)) {

		document.getElementById("labelerrorpassword").innerHTML = "Contraseña no válida debe de tener ente 6 y 12 caracteres";

	}

	else if (document.getElementById("password").value != document

	.getElementById("repassword").value) {

		document.getElementById("labelerrorrepassword").innerHTML = "Las contraseñas no coinciden";

	} else {

		objsend = new Object();
		objsend.username = document.getElementById("username").value;
		objsend.password = document.getElementById("password").value;
		objsend.roles = new Array();
		objsend.roles[objsend.roles.length] = "ROLE_USER";
		if (document.getElementById("chkadmin").checked == true) {

			objsend.roles[objsend.roles.length] = "ROLE_ADMIN";

		}
		ajaxobj = getAjaxHandler();

		if (ajaxobj != null) {

			ajaxobj.onreadystatechange = function() {

				var jsonobj;
				var row;
				var colum;
				var element;
				var text;
				var tablebody;
				jsonobj = null;

				if (ajaxobj.readyState == 4) {
					if (ajaxobj.status == 403
							|| converttexttojson(ajaxobj.responseText) == undefined) {
						sendlogoutform();
					}

					else if (ajaxobj.status == 200) {

						jsonobj = converttexttojson(ajaxobj.responseText);
						if (jsonobj == 1) {

							document.getElementById("generalformusererrors").innerHTML = "Nombre de ususario no valido debe de contener ente 6 y 12 caracteres";

						} else if (jsonobj == 2) {

							document.getElementById("generalformusererrors").innerHTML = "Contraseña no valiada debe de contener ente 6 y 12 caracteres";

						} else if (jsonobj == 3) {

							document.getElementById("generalformusererrors").innerHTML = "Roles de usuario no váidos";

						} else if (jsonobj == 4) {

							document.getElementById("generalformusererrors").innerHTML = "Ya existe el usuario; especificado"

						} else if (jsonobj == 5) {

							document.getElementById("generalformusererrors").innerHTML = "Error inersperado"

						} else if (jsonobj == 6) {

							document.getElementById("generalformusererrors").innerHTML = "El usuario especificado no existe"

						} else if (createrecord == true) {

							document.getElementById("generalformusersuscess").innerHTML = "Usuario añadido con exito"
							closeuserform();
						} else {

							document.getElementById("generalformusersuscess").innerHTML = "Usuario ediatdo con exito"
							closeuserform();

						}

						drawsystemuserstable();
					}
				}
			}
			if (createrecord == true) {

				ajaxobj.open("POST", "adduser", true)
			} else {

				ajaxobj.open("POST", "edituser", true)

			}
			token = document.querySelector("meta[name='_csrf']").getAttribute(
					"content");
			header = document.querySelector("meta[name='_csrf_header']")
					.getAttribute("content");
			ajaxobj.setRequestHeader(header, token);
			ajaxobj.setRequestHeader("Content-Type",
					"application/json; charset=UTF-8");
			ajaxobj.send(JSON.stringify(objsend));
		} else {

			alert("Error al realizar la operacion")

		}

	}

}

function clearformusererrorlabels() {

	document.getElementById("generalformusersuscess").innerHTML = "";
	document.getElementById("generalformusererrors").innerHTML = "";
	document.getElementById("labelerrousername").innerHTML = "";
	document.getElementById("labelerrorpassword").innerHTML = "";
	document.getElementById("labelerrorrepassword").innerHTML = "";

}

function clearformusererrorfields() {

	document.getElementById("username").value = "";
	document.getElementById("password").value = "";
	document.getElementById("repassword").value = "";
	document.getElementById("chkadmin").checked = false;

}

function changeuserselections() {

	var userobj;
	var checkusers;

	checkusers = document.getElementsByClassName("checkboxusers");
	for (var i = 0; i < checkusers.length; i++) {

		userobj = JSON.parse(checkusers[i].getAttribute("data-userreg"))
		if (document.getElementById("mainsection")
				.getAttribute("data-username") != userobj.username) {
			checkusers[i].checked = this.checked;
		}
	}
}

function deleteuserselections() {

	var userobj;
	var checkusers;
	var hasgoterror
	var usersnames;
	var i;

	clearformusererrorlabels();
	clearformusererrorfields();

	usersnames = new Array();
	checkusers = document.getElementsByClassName("checkboxusers");
	hasgoterror = false;
	i = 0;
	while (i < checkusers.length && hasgoterror == false) {

		userobj = JSON.parse(checkusers[i].getAttribute("data-userreg"))

		if (checkusers[i].checked == true) {
			if (document.getElementById("mainsection").getAttribute(
					"data-username") == userobj.username) {

				hasgoterror = true;

			} else {

				usersnames[usersnames.length] = userobj.username;

			}
		}
		i++;
	}

	if (hasgoterror == true) {

		document.getElementById("generalformusererrors").innerHTML = "No puedes borrar tu propio usuario"

	} else {

		ajaxdeleteusers(usersnames, 0)

	}
}

function ajaxdeleteusers(usernames, count) {

	var ajaxobj;
	var jsonobj;

	ajaxobj = getAjaxHandler();

	if (count < usernames.length) {

		if (ajaxobj != null) {

			ajaxobj.onreadystatechange = function() {

				if (ajaxobj.readyState == 4) {

					if (ajaxobj.status == 403
							|| converttexttojson(ajaxobj.responseText) == undefined) {
						sendlogoutform();
					}

					else if (ajaxobj.status == 200) {
						jsonobj = converttexttojson(ajaxobj.responseText)
						if (jsonobj == 0) {

							ajaxdeleteusers(usernames, count + 1);

						} else if (jsonobj == 1) {

							document.getElementById("generalformusererrors").innerHTML = "No puedes borrar tu propio usuario"

						} else {

							document.getElementById("generalformusererrors").innerHTML = "Error inesperador"

						}
						drawsystemuserstable();

					}
				}

			}

			ajaxobj.open("POST", "deleteuser", true)

			token = document.querySelector("meta[name='_csrf']").getAttribute(
					"content");
			header = document.querySelector("meta[name='_csrf_header']")
					.getAttribute("content");
			ajaxobj.setRequestHeader(header, token);
			ajaxobj.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			ajaxobj.send("username="
					+ escape(usernames[count]).split("%20").join("+"));
		} else {

			alert("Error al realizar la operacion")

		}

	}

}

function showhidenpassword() {

	var passwordbox;
	passwordbox = document.getElementById("password")
	if (this.checked == true) {

		passwordbox.type = "text";

	} else {

		passwordbox.type = "password";

	}
}

function clearsendnetwokconfiglabels() {

	document.getElementById("generalformusersuscess").innerHTML = "";
	document.getElementById("generalformusererrors").innerHTML = "";
	document.getElementById("labelerrorssid").innerHTML = "";
	document.getElementById("labelerrorpassword").innerHTML = "";
	document.getElementById("labelerrorchannel").innerHTML = "";
	document.getElementById("labelerrorsecuritytype").innerHTML = "";

}

function sendnetwokconfig() {
	var ajaxobj;
	var objsend;
	var regexpression
	clearsendnetwokconfiglabels()

	regexpression = new RegExp('^[a-zA-Z0-9][a-zA-Z0-9]{4,29}[a-zA-Z0-9]$');

	if (regexpression.test(document.getElementById("ssid").value) == false) {

		document.getElementById("labelerrorssid").innerHTML = "SSID no valido debe de contener entre 6 y 30 caracteres alfanumericos";

	} else if (regexpression.test(document.getElementById("password").value) == false) {

		document.getElementById("labelerrorpasswod").innerHTML = "Contraseña no valida debe de contener entre 6 y 30 caracteres alfanumericos";

	} else {

		if (window
				.confirm("Al hacer esto perderas la conexion si no estas conectado por cable") == true) {

			// /////////

			objsend = new Object();
			objsend.ssid = document.getElementById("ssid").value;
			objsend.password = document.getElementById("password").value;
			objsend.channel = parseInt(
					document.getElementById("channel").value, 10);
			objsend.securitytype = parseInt(document
					.getElementById("securitytype").value, 10);

			ajaxobj = getAjaxHandler();

			if (ajaxobj != null) {

				ajaxobj.onreadystatechange = function() {

					var jsonobj;
					var row;
					var colum;
					var element;
					var text;
					var tablebody;
					jsonobj = null;

					if (ajaxobj.readyState == 4) {
						if (ajaxobj.status == 403
								|| converttexttojson(ajaxobj.responseText) == undefined) {
							sendlogoutform();
						}

						else if (ajaxobj.status == 200) {

							jsonobj = converttexttojson(ajaxobj.responseText);
							if (jsonobj == 1) {

								document
										.getElementById("generalformusererrors").innerHTML = "Datos no validos";

							} else if (jsonobj == 2) {

								document
										.getElementById("generalformusererrors").innerHTML = "Error al realizar la operación";

							} else {

								document
										.getElementById("generalformusersuscess").innerHTML = "Operación realizada con exito"

							}

						}
					}
				}

				ajaxobj.open("POST", "networksave", true)

				token = document.querySelector("meta[name='_csrf']")
						.getAttribute("content");
				header = document.querySelector("meta[name='_csrf_header']")
						.getAttribute("content");
				ajaxobj.setRequestHeader(header, token);
				ajaxobj.setRequestHeader("Content-Type",
						"application/json; charset=UTF-8");
				ajaxobj.send(JSON.stringify(objsend));
			} else {

				alert("Error al realizar la operacion")

			}
		}
		// //////
	}
}

function rebootlink() {

	if (window.confirm("¿Estas seguro de quere reiniciar el dispositivo?") == true) {

		rebootdevice();

	}

}

function powerofflink() {

	if (window.confirm("¿Estas seguro de quere apagar el dispositivo?") == true) {

		poweroffdevice();

	}

}

function rebootdevice() {

	var ajaxobj;

	document.getElementById("generalformusersuscess").innerHTML = "";
	document.getElementById("generalformusererrors").innerHTML = "";

	ajaxobj = getAjaxHandler();

	if (ajaxobj != null) {

		ajaxobj.onreadystatechange = function() {

			var jsonobj;
			var row;
			var colum;
			var element;
			var text;
			var tablebody;
			jsonobj = null;

			if (ajaxobj.readyState == 4) {
				if (ajaxobj.status == 403
						|| converttexttojson(ajaxobj.responseText) == undefined) {
					sendlogoutform();
				}

				else if (ajaxobj.status == 200) {

					jsonobj = converttexttojson(ajaxobj.responseText);
					if (jsonobj == 1) {

						document.getElementById("generalformusererrors").innerHTML = "Error al realizar la operacion";

					} else {

						sendlogoutform()

					}

				}
			}
		}

		ajaxobj.open("GET", "reboot", true)

		token = document.querySelector("meta[name='_csrf']").getAttribute(
				"content");
		header = document.querySelector("meta[name='_csrf_header']")
				.getAttribute("content");
		ajaxobj.setRequestHeader(header, token);
		ajaxobj.setRequestHeader("Content-Type",
				"application/json; charset=UTF-8");
		ajaxobj.send(null);
	} else {

		alert("Error al realizar la operacion")

	}

}

function poweroffdevice() {

	var ajaxobj;

	document.getElementById("generalformusersuscess").innerHTML = "";
	document.getElementById("generalformusererrors").innerHTML = "";

	ajaxobj = getAjaxHandler();

	if (ajaxobj != null) {

		ajaxobj.onreadystatechange = function() {

			var jsonobj;
			var row;
			var colum;
			var element;
			var text;
			var tablebody;
			jsonobj = null;

			if (ajaxobj.readyState == 4) {
				if (ajaxobj.status == 403
						|| converttexttojson(ajaxobj.responseText) == undefined) {
					sendlogoutform();
				}

				else if (ajaxobj.status == 200) {

					jsonobj = converttexttojson(ajaxobj.responseText);
					if (jsonobj == 1) {

						document.getElementById("generalformusererrors").innerHTML = "Error al realizar la operacion";

					} else {

						sendlogoutform()

					}

				}
			}
		}

		ajaxobj.open("GET", "poweroff", true)

		token = document.querySelector("meta[name='_csrf']").getAttribute(
				"content");
		header = document.querySelector("meta[name='_csrf_header']")
				.getAttribute("content");
		ajaxobj.setRequestHeader(header, token);
		ajaxobj.setRequestHeader("Content-Type",
				"application/json; charset=UTF-8");
		ajaxobj.send(null);
	} else {

		alert("Error al realizar la operacion")

	}

}

function clearsendligthsconfiglabels() {

	document.getElementById("generalformusersuscess").innerHTML = "";
	document.getElementById("generalformusererrors").innerHTML = "";
	document.getElementById("labelerrorsecretkey").innerHTML = "";
	document.getElementById("labelerrorpin").innerHTML = "";

}

function sendligthconfig() {

	var ajaxobj;
	var objsend;
	clearsendligthsconfiglabels()

	if (document.getElementById("secretkey") == null
			|| document.getElementById("secretkey").value.trim() == ""
			|| document.getElementById("secretkey").value.trim() != document
					.getElementById("secretkey").value
			|| document.getElementById("secretkey").length < 4
			|| document.getElementById("secretkey").length > 16) {

		document.getElementById("labelerrorsecretkey").innerHTML = "La clave no es valida debe contener entre 4 y 16 caracteres";

	} else {

		if (window.confirm("Al hacer esto se reiniciara el dispositivo") == true) {

			// /////////

			objsend = new Object();
			objsend.secretkey = document.getElementById("secretkey").value;
			objsend.pin = parseInt(document.getElementById("pin").value, 10);

			ajaxobj = getAjaxHandler();

			if (ajaxobj != null) {

				ajaxobj.onreadystatechange = function() {

					var jsonobj;
					var row;
					var colum;
					var element;
					var text;
					var tablebody;
					jsonobj = null;

					if (ajaxobj.readyState == 4) {
						if (ajaxobj.status == 403
								|| converttexttojson(ajaxobj.responseText) == undefined) {
							sendlogoutform();
						}

						else if (ajaxobj.status == 200) {

							jsonobj = converttexttojson(ajaxobj.responseText);
							if (jsonobj == 1) {

								document
										.getElementById("generalformusererrors").innerHTML = "Datos no validos";

							} else if (jsonobj == 2) {

								document
										.getElementById("generalformusererrors").innerHTML = "Error al realizar la operación";

							}

							else if (jsonobj == 3) {

								document
										.getElementById("generalformusererrors").innerHTML = "Error al realizar la operación";

							}

							else {

								sendlogoutform()

							}

						}
					}
				}

				ajaxobj.open("POST", "ligthssave", true)

				token = document.querySelector("meta[name='_csrf']")
						.getAttribute("content");
				header = document.querySelector("meta[name='_csrf_header']")
						.getAttribute("content");
				ajaxobj.setRequestHeader(header, token);
				ajaxobj.setRequestHeader("Content-Type",
						"application/json; charset=UTF-8");
				ajaxobj.send(JSON.stringify(objsend));
			} else {

				alert("Error al realizar la operacion")

			}
		}
		// //////
	}

}

function initpage() {

	var mainsectiontag;

	mainsectiontag = document.getElementById("mainsection");
	if (mainsectiontag != null) {

		if (mainsectiontag.getAttribute("data-currentpage") == "loginpage") {

			document.getElementById("formlogin").onsubmit = sendloginform

		}

		else if (mainsectiontag.getAttribute("data-currentpage") == "userspage") {

			drawsystemuserstable()
			document.getElementById("formlogin").style.display = "none";
			createrecord = true;
			document.getElementById("btnclose").onclick = closeuserform;
			document.getElementById("btnsave").onclick = createedituser;
			document.getElementById("lnkadduser").onclick = createuser;
			document.getElementById("chkselectall").onclick = changeuserselections;
			document.getElementById("lnkdeleteuser").onclick = deleteuserselections;
			document.getElementById("lnkdreload").onclick = drawsystemuserstable;

		}

		else if (mainsectiontag.getAttribute("data-currentpage") == "networkpage") {

			document.getElementById("btnsave").onclick = sendnetwokconfig;
			document.getElementById("chkshowpassword").onclick = showhidenpassword;

		}

		else if (mainsectiontag.getAttribute("data-currentpage") == "ligthsconfig") {

			document.getElementById("btnsave").onclick = sendligthconfig;

		}

		if (mainsectiontag.getAttribute("data-currentpage") != "loginpage") {

			document.getElementById("closesession").onclick = sendlogoutform;
			document.getElementById("reboot").onclick = rebootlink;
			document.getElementById("poweroff").onclick = powerofflink;

		}

	}

}

window.addEventListener("load", initpage, true);
