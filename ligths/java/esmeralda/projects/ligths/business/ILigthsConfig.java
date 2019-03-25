package esmeralda.projects.ligths.business;

import java.io.IOException;

public interface ILigthsConfig {


	
	public void setSecretKey(String secretkey) throws IllegalArgumentException; 
	public String getSecretKey();
	public void setPin(int pin) throws IllegalArgumentException; 
	public int getPin();
	public void saveConfig() throws IOException;
	
	
}
