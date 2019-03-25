package esmeralda.projects.ligths.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import esmeralda.libs.RPI.GPIO.OperationNotWorkException;
import esmeralda.projects.ligths.beans.RestOperation;
import esmeralda.projects.ligths.business.ILightsHandler;
import esmeralda.projects.ligths.business.ILigthsConfig;

@RestController
@RequestMapping(value="/rest")
public class LightsRESTController {

	@Autowired
	private ILightsHandler ligthshandler;
	
	@RequestMapping(value="/lighton",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)	
	public RestOperation lighton() {
		
		RestOperation retval;
		retval=null;
		
		try {
			
		
			this.ligthshandler.ligthson();
			retval=new RestOperation("LIGHTON", 0, "Operación realizada con exito", null);
					
		}
		catch(OperationNotWorkException e) {
			
			retval=new RestOperation("LIGHTON", 6, "Error al realizar la operación", null);
			
		}
		
		return retval;
		
	}
	
	
	@RequestMapping(value="/lightoff",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)	
	public RestOperation lightoff() {
		
		RestOperation retval;
		retval=null;
		
		try {
			
		
			this.ligthshandler.ligthsoff();
			retval=new RestOperation("LIGHTOFF", 0, "Operación realizada con exito", null);
					
		}
		catch(OperationNotWorkException e) {
			
			retval=new RestOperation("LIGHTOFF", 6, "Error al realizar la operación", null);
			
		}
		
		return retval;
		
	}

	
	@RequestMapping(value="/lightstatus",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)	
	public RestOperation lightstatus() {
		boolean status;
		RestOperation retval;
		
		retval=null;
		
		try {
		  
		
			status=this.ligthshandler.getLightsStatus();
			retval=new RestOperation("LIGHTSTATUS", 0, "Operación realizada con exito", status);
					
		}
		catch(OperationNotWorkException e) {
			
			retval=new RestOperation("LIGHTSTATUS", 6, "Error al realizar la operación", null);
			
		}
		
		return retval;
		
	}

	
}
