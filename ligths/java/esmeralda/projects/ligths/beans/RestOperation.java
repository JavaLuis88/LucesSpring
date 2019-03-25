package esmeralda.projects.ligths.beans;
import java.io.Serializable;
public class RestOperation implements Serializable {

	
	private String operationname;
	private int operationcode;
	private String operationcodedescription;
	private Object info;
	
	
	public RestOperation() {
		
		this.operationname="";
		this.operationcode=0;
		this.operationcodedescription="";
		this.info=null;
		
		
	}

	
	
	
	
	public RestOperation(String operationname,int operationcode,String operationcodedescription,Object info) {
		
		this.operationname=operationname;
		this.operationcode=operationcode;
		this.operationcodedescription=operationcodedescription;
		this.info=info;
		
		
	}


	
	public String getOperationname() {
		return this.operationname;
	}
	public void setOperationname(String operationname) {
		this.operationname = operationname;
	}
	public int getOperationcode() {
		return this.operationcode;
	}
	public void setOperationcode(int operationcode) {
		this.operationcode = operationcode;
	}
	public String getOperationcodedescription() {
		return this.operationcodedescription;
	}
	public void setOperationcodedescription(String operationcodedescription) {
		this.operationcodedescription = operationcodedescription;
	}
	public Object getInfo() {
		return this.info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	


	
	
}



