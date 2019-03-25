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

public class PoweroffReboot implements IPoweroffReboot {

	private IAppPaths apppaths;

	public PoweroffReboot(IAppPaths apppaths) {
		// TODO Auto-generated constructor stub

		this.apppaths = apppaths;

	}

	@Override
	public int poweroff() {

		Date curDate;
		SimpleDateFormat format;
		String dateToStr;
		File scriptpath;
		int retval;

		retval = 1;

		curDate = new Date();
		format = new SimpleDateFormat("yyyyMMddhhmmss");
		dateToStr = format.format(curDate);

		scriptpath = new File(this.apppaths.getSystemscriptspool(), dateToStr + ".sh");

		try (FileOutputStream channel = new FileOutputStream(scriptpath)) {

			try (OutputStreamWriter filter = new OutputStreamWriter(channel)) {

				try (BufferedWriter buffer = new BufferedWriter(filter)) {

					 	buffer.write("#!/bin/bash" + System.lineSeparator());
						buffer.write("exit 3" + System.lineSeparator());

				
					
					
					retval = 0;

				} catch (IOException e3) {

				}

			} catch (IOException e2) {

			}

		} catch (IOException e) {

		}

		return retval;
	}

	@Override
	public int reboot() {

		Date curDate;
		SimpleDateFormat format;
		String dateToStr;
		File scriptpath;
		int retval;

		retval = 1;

		curDate = new Date();
		format = new SimpleDateFormat("yyyyMMddhhmmss");
		dateToStr = format.format(curDate);

		scriptpath = new File(this.apppaths.getSystemscriptspool(), dateToStr + ".sh");

		try (FileOutputStream channel = new FileOutputStream(scriptpath)) {

			try (OutputStreamWriter filter = new OutputStreamWriter(channel)) {

				try (BufferedWriter buffer = new BufferedWriter(filter)) {

			
				 	buffer.write("#!/bin/bash" + System.lineSeparator());
					buffer.write("exit 2" + System.lineSeparator());

					retval = 0;

				} catch (IOException e3) {

				}

			} catch (IOException e2) {

			}

		} catch (IOException e) {

		}

		return retval;

	}

}
