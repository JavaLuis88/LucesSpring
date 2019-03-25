package esmeralda.projects.ligths.business;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RouterConfig implements IRouterConfig {

	Properties wifiproperties;
	private IAppPaths apppaths;

	public RouterConfig(IAppPaths apppaths) throws IOException {
		// TODO Auto-generated constructor stub

		File hostapdconfigpath;

		this.apppaths = apppaths;
		hostapdconfigpath = new File(this.apppaths.getCurrentconfigpath(), "hostapd.conf");

		this.wifiproperties = new Properties();
		try (FileInputStream channel = new FileInputStream(hostapdconfigpath)) {

			this.wifiproperties.load(channel);

		}

	}

	@Override
	public void setSSID(String ssid) throws IllegalArgumentException {
		// TODO Auto-generated method stub

		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9]{4,29}[a-zA-Z0-9]$");
		matcher = pattern.matcher(ssid);

		if (ssid == null || ssid.trim().equals("") == true || matcher.matches() == false) {

			throw new IllegalArgumentException("SSID is not valid");

		} else {

			this.wifiproperties.setProperty("ssid", ssid);

		}

	}

	@Override
	public String getSSID() {
		// TODO Auto-generated method stub
		return this.wifiproperties.getProperty("ssid");

	}

	@Override
	public void setPassword(String password) throws IllegalArgumentException {
		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9]{4,29}[a-zA-Z0-9]$");
		matcher = pattern.matcher(password);

		if (password == null || password.trim().equals("") == true || matcher.matches() == false) {

			throw new IllegalArgumentException("Password is not valid");

		} else {

			this.wifiproperties.setProperty("wpa_passphrase", password);

		}

	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.wifiproperties.getProperty("wpa_passphrase");
	}

	@Override
	public void setChannel(int channel) throws IllegalArgumentException {
		// TODO Auto-generated method stub

		if (channel != 1 && channel != 6 && channel != 11) {

			throw new IllegalArgumentException("Channel is not valid");

		} else {
			this.wifiproperties.setProperty("channel", Integer.toString(channel));

		}

	}

	@Override
	public int getChannel() {

		int retval;
		retval = 1;

		try {

			retval = Integer.parseInt(this.wifiproperties.getProperty("channel"));

		} catch (NumberFormatException e) {

		}
		return retval;
	}

	@Override
	public void setWPAType(int wpatype) throws IllegalArgumentException {
		// TODO Auto-generated method stub

		if (wpatype != 1 && wpatype != 2) {

			throw new IllegalArgumentException("WPA version is not valid");

		} else {
			this.wifiproperties.setProperty("wpa", Integer.toString(wpatype));

		}

	}

	@Override
	public int getWPAtype() {
		// TODO Auto-generated method stub
		int retval;
		retval = 1;

		try {

			retval = Integer.parseInt(this.wifiproperties.getProperty("wpa"));

		} catch (NumberFormatException e) {

		}
		return retval;
	}

	@Override
	public void saveConfig() throws IOException {
		// TODO Auto-generated method stub

		Date curDate;
		SimpleDateFormat format;
        String dateToStr;
    	File hostapdconfigpath;
       	File hostapdconfigpath2;
      	File scriptpath;
              	
    	
        curDate = new Date();
        format = new SimpleDateFormat("yyyyMMddhhmmss");
        dateToStr = format.format(curDate);
        
    	hostapdconfigpath = new File(this.apppaths.getCurrentconfigpath(), "hostapd.conf");
    	hostapdconfigpath2 = new File(this.apppaths.getSystemscriptspool(), dateToStr + ".conf");
    	scriptpath = new File(this.apppaths.getSystemscriptspool(), dateToStr + ".sh");
           	
		
		try (FileOutputStream channel = new FileOutputStream(hostapdconfigpath)) {

			this.wifiproperties.store(channel, "");

			
	
			
			try (FileOutputStream channel2 = new FileOutputStream(hostapdconfigpath2)) {

				this.wifiproperties.store(channel2, "");

				try (FileOutputStream channel3 = new FileOutputStream(scriptpath)) {


					try (OutputStreamWriter filter=new OutputStreamWriter(channel3)) {
						
						try (BufferedWriter buffer=new BufferedWriter(filter)) {
						

						
							
							
							buffer.write("#!/bin/bash" + System.lineSeparator());
							buffer.write("retval=0" + System.lineSeparator());
							buffer.write("/usr/sbin/service hostapd stop" + System.lineSeparator());
							buffer.write("/bin/mv " +hostapdconfigpath2.getCanonicalPath()+ " /etc/hostapd/hostapd.conf" +System.lineSeparator());		
							buffer.write("/usr/sbin/service hostapd start " + System.lineSeparator());
							buffer.write("exit 0" + System.lineSeparator());


							
							
							
			
							
							
						}
						
					}
				
				
				
				}
	        
				
				
				
			}
			
			
			
		}
        
        
	}
	
	
	
	
	
	

}
