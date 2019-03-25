package esmeralda.libs.utils;

public class LogException extends RuntimeException {

	
	public LogException() {
		
		super("Exception work in log file");
		
	}
	

	public LogException(String msg) {
		
		super(msg);
		
	}
	

	
}
