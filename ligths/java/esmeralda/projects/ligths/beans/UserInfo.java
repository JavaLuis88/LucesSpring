package esmeralda.projects.ligths.beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class UserInfo implements Serializable,Comparable<UserInfo> {

	
	private String username;
	private String password;
	private List<String> roles;
	
	

	
	public UserInfo() {
		
		this.username="";
		this.password="";
		this.roles=new ArrayList<String>();
				
		
	}
	
	
	public UserInfo(String username,String password,List<String> roles) {
		
		this.username=username;
		this.password=password;
		this.roles=roles;
	}

	
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getRoles() {
		return this.roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}


	@Override
	public int compareTo(UserInfo arg0) {
		
		int retval;
		retval=0;
		
		if(this.username!=null && this.username.trim().equals("")==false && arg0.username!=null && arg0.username.trim().equals("")==false) {
			
			retval=this.username.toUpperCase().compareTo(arg0.username.toUpperCase());
			
		}
		
		return retval;
	
	}

	
}
