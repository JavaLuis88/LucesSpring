package esmeralda.libs.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Log implements AutoCloseable {

	private PrintWriter pw;

	public Log(String logfile)  {

		try {
		File logfilepath;
		FileOutputStream channel;
		OutputStreamWriter filter;
		BufferedWriter buffer;
		logfilepath = new File(logfile);
		channel = new FileOutputStream(logfilepath,true);
		filter = new OutputStreamWriter(channel);
		buffer = new BufferedWriter(filter);
		this.pw = new PrintWriter(buffer);
		}
		catch(Exception e) {
			
			throw new LogException();
			
		}
	}

	
	
	public void write(String message) {
		// TODO Auto-generated method stub
	
		try {
		

			Date curDate;
			SimpleDateFormat format;
			String dateToStr;
    	
			curDate = new Date();
			format = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
			dateToStr = format.format(curDate);
			this.pw.println(dateToStr + " " + message);
			
			
			
		}
		catch(Exception e) {
			
			throw new LogException();
			
		}
	}
	
	
	public void write(Throwable exception)  {
		// TODO Auto-generated method stub
		
		
		try {
			

			Date curDate;
			SimpleDateFormat format;
			String dateToStr;
    	
			curDate = new Date();
			format = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
			dateToStr = format.format(curDate);
			this.pw.println(dateToStr);
			exception.printStackTrace(this.pw);
			
			
		}
		catch(Exception e) {
			
			throw new LogException();
			
		}


	}

	
	public void write(String message,Throwable exception)  {
		// TODO Auto-generated method stub

		try {
			

			Date curDate;
			SimpleDateFormat format;
			String dateToStr;
    	
			curDate = new Date();
			format = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
			dateToStr = format.format(curDate);
			this.pw.println(dateToStr + " " + message);
			exception.printStackTrace(this.pw);
			
			
		}
		catch(Exception e) {
			
			throw new LogException();
			
		}


	
	}
	
	
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
		
		try {
			
			if (this.pw!=null) {
			this.pw.close();
			}
		}
		catch(Throwable e) {
			
			
		}
		

	}
}
