package esmeralda.projects.ligthscriptdaemon;

public class ThreadContext {
	
	private int signalcode;
	private String signalthreadname;

	
	public ThreadContext() {
		
		this.signalcode=0;
		this.signalthreadname="";
		
	}
	
		
	public int getSignalcode() {
		return this.signalcode;
	}
	public void setSignalcode(int signalcode) {
		this.signalcode = signalcode;
	}
	public String getSignalthreadname() {
		return this.signalthreadname;
	}
	public void setSignalthreadname(String signalthreadname) {
		this.signalthreadname = signalthreadname;
	}


}
