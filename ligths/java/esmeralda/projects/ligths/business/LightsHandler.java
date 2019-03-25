package esmeralda.projects.ligths.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import esmeralda.libs.RPI.GPIO.OperationNotWorkException;

public class LightsHandler implements ILightsHandler {

	private IAppPaths apppaths;

	public LightsHandler(IAppPaths apppaths) {

		this.apppaths = apppaths;

	}

	@Override
	public void ligthson() throws OperationNotWorkException {

		Date curDate;
		SimpleDateFormat format;
		String dateToStr;
		File controlfilepath;

		curDate = new Date();
		format = new SimpleDateFormat("yyyyMMddhhmmss");
		dateToStr = format.format(curDate);
		controlfilepath = new File(this.apppaths.getLigthscontrolspool(), dateToStr + ".in");

		try (PrintStream printstream = new PrintStream(controlfilepath)) {

			printstream.println("1");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new OperationNotWorkException();

		}

	}

	@Override
	public void ligthsoff() throws OperationNotWorkException {

		Date curDate;
		SimpleDateFormat format;
		String dateToStr;
		File controlfilepath;

		curDate = new Date();
		format = new SimpleDateFormat("yyyyMMddhhmmss");
		dateToStr = format.format(curDate);
		controlfilepath = new File(this.apppaths.getLigthscontrolspool(), dateToStr + ".in");

		try (PrintStream printstream = new PrintStream(controlfilepath)) {

			printstream.println("0");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new OperationNotWorkException();

		}

	}

	@Override
	public boolean getLightsStatus() throws OperationNotWorkException {
		File controlfilepath;
		String line;
		boolean retval;

		line="";
		retval = false;

		controlfilepath = new File(this.apppaths.getLigthscontrolspool(), "ligths.out");

		if (controlfilepath.exists() == true && controlfilepath.isFile() == true) {

			try (FileInputStream channel = new FileInputStream(controlfilepath)) {

				try (InputStreamReader filter = new InputStreamReader(channel)) {

					try (BufferedReader buffer = new BufferedReader(filter)) {
						line = buffer.readLine();

						retval=line.trim().equals("1");
						
					}

				}

			} catch (Exception e) {

				throw new OperationNotWorkException();

			}

		}

		return retval;
	}

}
