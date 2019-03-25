package esmeralda.projects.ligths.business;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Encoder implements PasswordEncoder {

	public SHA256Encoder() {
		
		
		
	}

	
	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return this.convertirSHA256(rawPassword.toString().toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return encodedPassword.equalsIgnoreCase(this.encode(rawPassword));
	}
	
	
	public String convertirSHA256(String password) {
		MessageDigest md = null;
		byte[] hash;
		StringBuffer sb;
		String retval;
		
		retval=null;
		
		
		try {
			md = MessageDigest.getInstance("SHA-256");
			hash = md.digest(password.getBytes());
			sb = new StringBuffer();
			    
			for(byte b : hash) {        
				sb.append(String.format("%02x", b));
			}

			retval=sb.toString();

		} 
		catch (NoSuchAlgorithmException e) {		
		}
		
		return retval;
		    

	}

}
