function Restapi(username, password, callbackfunction) {

	this.ajaxobj = new XMLHttpRequest();
	this.ajaxobj.restapi = this;
	this.username = username;
	this.password = password;
	this.callbackfunction = callbackfunction;
	this.currenttoken = "";
	this.login = Restapi_login;
	this.lighton = Restapi_lighton;
	this.lightoff = Restapi_lightoff;
	this.lightstatus = Restapi_lightstatus;
	this.lightclose = Restapi_lightclose;


	if (this.ajaxobj == null) {

		throw new Error("El navegador no sporta ajax");

	}

	else if (typeof (this.username) != "string" || this.username.trim() == ""
			|| this.username.trim() != this.username) {

		throw new Error("Nombre de usuario no valido");

	}

	else if (typeof (this.password) != "string" || this.password.trim() == ""
			|| this.password.trim() != this.password) {

		throw new Error("Contraseña no válida");

	}

	else if (typeof (this.callbackfunction) != "function") {

		throw new Error("Función callback no válida");

	}

}

function Restapi_login() {
	
	var userobj;
	var restoperation;

	this.ajaxobj.onreadystatechange = function() {
		if (this.readyState == 4) {

			if (this.status == 200) {

				this.restapi.currenttoken = this
						.getResponseHeader("Authorization");
				restoperation = new RestOperation("LOGIN", 0,
						"identificacción del usuario exitosa", "")

			} else if (this.status == 401) {

				this.restapi.currenttoken = "";
				restoperation = new RestOperation("LOGIN", 1,
						"Nombre de usuario o comtraseña no validos", "")

			}

			else if (this.status == 403) {

				this.restapi.currenttoken = "";
				restoperation = new RestOperation("LOGIN", 2,
						"No tienes permiso para acceder aqui", "")

			} else {

				this.restapi.currenttoken = "";
				restoperation = new RestOperation("LOGIN", 3,
						"Error desconocido", "")

			}

			this.restapi.callbackfunction(this.restapi, restoperation)

		}
	}

	userobj = new Object()
	userobj.username = this.username;
	userobj.password = this.password;

	this.ajaxobj.open("POST", "rest/login", true);
	this.ajaxobj.setRequestHeader("Content-Type",
			"application/json; charset=UTF-8");
	this.ajaxobj.send(JSON.stringify(userobj));

}

function Restapi_lighton() {



	var userobj;
	var restoperation;

	this.ajaxobj.onreadystatechange = function() {
		
		var jsonobj;
		
		if (this.readyState == 4) {

			if (this.status == 200) {

				this.restapi.currenttoken = this.getResponseHeader("authorization");
				
				jsonobj=JSON.parse(this.responseText);
				
				if (jsonobj!=undefined && jsonobj!=null) {
					
					restoperation = new RestOperation(jsonobj.operationname,jsonobj.operationcode,jsonobj.operationcodedescription,jsonobj.info)

					
				}
				else {
					
					this.restapi.currenttoken = "";
					restoperation = new RestOperation("LIGHTON", 5,
							"respuesta del servidor no válida", "");

				}
				
				

			} else if (this.status == 401) {

				this.restapi.currenttoken = "";
				restoperation = new RestOperation("LIGHTON", 1,
						"Nombre de usuario o comtraseña no validos", "")

			}

			else if (this.status == 403) {

				this.restapi.currenttoken = "";
				restoperation = new RestOperation("LIGHTON", 2,
						"No tienes permiso para acceder aqui", "");

			} else {

				this.restapi.currenttoken = "";
				restoperation = new RestOperation("LIGHTON", 3,
						"Error desconocido", "");

			}

			this.restapi.callbackfunction(this.restapi, restoperation);

		}
	}

	if (typeof(this.currenttoken)=="string"  && this.currenttoken.trim()!="") {

		this.ajaxobj.open("POST", "rest/lighton", true);
		this.ajaxobj.setRequestHeader("Authorization", this.currenttoken);
		this.ajaxobj.send(null);

	} else {

		restoperation = new RestOperation("LIGHTON", 4,
				"No estas autenticado para acceder a este area", "");
		
		this.callbackfunction(this, restoperation);
	}

}

function Restapi_lightoff() {

	


	var userobj;
	var restoperation;

	this.ajaxobj.onreadystatechange = function() {
		
		var jsonobj;
		
		if (this.readyState == 4) {

			if (this.status == 200) {

				this.restapi.currenttoken = this.getResponseHeader("authorization");
			
				
				jsonobj=JSON.parse(this.responseText);
				
				if (jsonobj!=undefined && jsonobj!=null) {
					
					restoperation = new RestOperation(jsonobj.operationname,jsonobj.operationcode,jsonobj.operationcodedescription,jsonobj.info)

					
				}
				else {
					this.restapi.currenttoken = "";
					restoperation = new RestOperation("LIGHTOFF", 5,
							"respuesta del servidor no válida", "");

				}
				


			} else if (this.status == 401) {

				this.restapi.currenttoken="";
				this.restapi.islogin = false;
				
				restoperation = new RestOperation("LIGHTOFF", 1,
						"Nombre de usuario o comtraseña no validos", "");

			}

			else if (this.status == 403) {
				
				this.restapi.currenttoken="";
				restoperation = new RestOperation("LIGHTOFF", 2,
						"No tienes permiso para acceder aqui", "");

			} else {
				
				this.restapi.currenttoken="";
				restoperation = new RestOperation("LIGHTOFF", 3,
						"Error desconocido", "")

			}

			this.restapi.callbackfunction(this.restapi, restoperation);

		}
	}

	if (typeof(this.currenttoken)=="string"  && this.currenttoken.trim()!="") {

		this.ajaxobj.open("POST", "rest/lightoff", true);
		this.ajaxobj.setRequestHeader("Authorization", this.currenttoken);
		this.ajaxobj.send(null);

	} else {

		restoperation = new RestOperation("LIGHTOFF", 4,
				"No estas autenticado para acceder a este area", "");
		this.callbackfunction(this, restoperation);
	}
	
}

function Restapi_lightstatus() {

	


	var userobj;
	var restoperation;

	this.ajaxobj.onreadystatechange = function() {
		
		var jsonobj;
		
		if (this.readyState == 4) {

			if (this.status == 200) {

				this.restapi.currenttoken = this.getResponseHeader("authorization");
				jsonobj=JSON.parse(this.responseText);
				
				if (jsonobj!=undefined && jsonobj!=null) {
					
					restoperation = new RestOperation(jsonobj.operationname,jsonobj.operationcode,jsonobj.operationcodedescription,jsonobj.info)

					
				}
				else {
					this.restapi.currenttoken = "";
					restoperation = new RestOperation("LIGHTSTATUS", 5,
							"respuesta del servidor no válida", "");

				}
				


			} else if (this.status == 401) {

				this.restapi.currenttoken="";
				restoperation = new RestOperation("LIGHTSTATUS", 1,
						"Nombre de usuario o comtraseña no validos", "");

			}

			else if (this.status == 403) {

				this.restapi.currenttoken = "";
							restoperation = new RestOperation("LIGHTSTATUS", 2,
						"No tienes permiso para acceder aqui", "");

			} else {

				this.restapi.currenttoken = "";
				restoperation = new RestOperation("LIGHTSTATUS", 3,
						"Error desconocido", "");

			}

			this.restapi.callbackfunction(this.restapi, restoperation)

		}
	}


	if (typeof(this.currenttoken)=="string"  && this.currenttoken.trim()!="") {

		this.ajaxobj.open("POST", "rest/lightstatus", true);
		this.ajaxobj.setRequestHeader("Authorization", this.currenttoken);
		this.ajaxobj.send(null);

	} else {

		restoperation = new RestOperation("LIGHTSTATUS", 4,
				"No estas autenticado para acceder a este area", "")
		this.callbackfunction(this, restoperation);
	}
	
	
}

function Restapi_lightclose() {
	

	this.currenttoken="";
	restoperation = new RestOperation("CLOSE", 0,
			"cierre de sessón exitoso", "");
	this.callbackfunction(this, restoperation)
	
}
