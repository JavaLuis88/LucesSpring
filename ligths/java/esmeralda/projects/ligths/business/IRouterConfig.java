package esmeralda.projects.ligths.business;

import java.io.IOException;

public interface IRouterConfig {


	
	public void setSSID(String ssid) throws IllegalArgumentException; 
	public String getSSID();
	public void setPassword(String password) throws IllegalArgumentException; 
	public String getPassword();
	public void setChannel(int channel) throws IllegalArgumentException; 
	public int getChannel();
	public void setWPAType(int  wpatype) throws IllegalArgumentException; 
	public int getWPAtype();
	public void saveConfig() throws IOException;
	
	
}
