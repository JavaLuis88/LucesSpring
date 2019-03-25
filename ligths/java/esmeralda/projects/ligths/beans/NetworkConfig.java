package esmeralda.projects.ligths.beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class NetworkConfig implements Serializable {

	

	private String ssid;
	private String password;
	private int channel;
	private int securitytype;
	
	public NetworkConfig() {
		
		this.ssid="Luces";
		this.password="Luces123";
		this.channel=6;
		this.securitytype=2;
				
		
	}
	
	
	public NetworkConfig(String ssid,String password,int channel,int securitytype) {
		
		this.ssid=ssid;
		this.password=password;
		this.channel=channel;
		this.securitytype=securitytype;	
	}

	
	public String getSsid() {
		return this.ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getChannel() {
		return this.channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public int getSecuritytype() {
		return this.securitytype;
	}
	public void setSecuritytype(int securitytype) {
		this.securitytype = securitytype;
	}

	
}
