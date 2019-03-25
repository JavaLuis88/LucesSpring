package esmeralda.projects.ligths.beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Ligths implements Serializable {


	private String secretkey;
	private int pin;

	
	public Ligths() {
		
		this.secretkey ="Clave123";
		this.pin=18;

				
		
	}
	
	
	public Ligths(String secretkey,int pin) {
		
		this.secretkey=secretkey;
		this.pin=pin;
	}



	public String getSecretkey() {
		return this.secretkey;
	}


	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}


	public int getPin() {
		return this.pin;
	}


	public void setPin(int pin) {
		this.pin = pin;
	}

	
}
