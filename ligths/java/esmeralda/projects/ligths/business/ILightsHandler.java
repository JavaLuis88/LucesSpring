package esmeralda.projects.ligths.business;

import esmeralda.libs.RPI.GPIO.OperationNotWorkException;

public interface ILightsHandler {
	

	public void ligthson() throws OperationNotWorkException;
	public void ligthsoff() throws OperationNotWorkException;
	public boolean getLightsStatus() throws OperationNotWorkException;
	
	

}
