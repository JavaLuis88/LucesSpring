package esmeralda.projects.ligthscriptdaemon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import esmeralda.libs.RPI.GPIO.IDigitalOuput;
import esmeralda.libs.RPI.GPIO.OperationNotWorkException;
import esmeralda.libs.RPI.GPIO.PinNumberNovalidException;
import esmeralda.libs.utils.Log;
import esmeralda.libs.utils.LogException;

public class ThreadLightsStatus extends Thread {

	private ThreadContext threadcontext;
	private LoadConfig mainconfig;
	private IDigitalOuput idigitaloutput;
	private Log log;
	private boolean execute;
	private int signalcode;
	private String siganlthreadname;
	private Runtime runtime;

	public ThreadLightsStatus(ThreadContext threadcontext, LoadConfig mainconfig, IDigitalOuput idigitaloutput) {
		// TODO Auto-generated constructor stub
		super();
		// System.out.println("Inciando thread ThreadLightsStatus");
		this.threadcontext = threadcontext;
		this.mainconfig = mainconfig;
		this.idigitaloutput = idigitaloutput;
		// this.mainconfig.getLogfile().write("Inciando thread ThreadLightsStatus");

		this.log = mainconfig.getLogfile();

		// System.out.println("Thread ThreadLightsStatus iniciado");
		// this.log.write("Thread ThreadLightsStatus iniciado");
	}

	public void run() {

		// System.out.println("iniciando metodo run de ThreadLightsStatus");
		// this.log.write("iniciando metodo run de ThreadLightsStatus");

		this.execute = true;
		this.signalcode = 0;
		this.siganlthreadname = "";

		// System.out.println("Obteniendo Runtime en ThreadLightsStatus");
		// this.log.write("Obteniendo Runtime en ThreadLightsStatus");

		this.runtime = Runtime.getRuntime();

		// System.out.println("iniciando thread en ThreadLightsStatus");
		 this.log.write("iniciando thread en ThreadLightsStatus");

	
		 
		while (this.execute == true && this.signalcode == 0) {

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			this.verifylightsstatus();

		}

		if (this.signalcode == 0) {

			// System.out.println("Salida normal del metodo run de ThreadLightsStatus");
			this.log.write("Salida normal del metodo run de ThreadLightsStatus");
			this.signalcode = this.threadcontext.getSignalcode();
			this.siganlthreadname = this.threadcontext.getSignalthreadname();

			
			
		} else if (this.signalcode == 1) {

			System.out.println(
					"Salida  del  metodo run de ThreadLightsStatus por error del thread " + this.siganlthreadname);
			this.log.write(
					"Salida  del  metodo run de ThreadLightsStatus por error del thread " + this.siganlthreadname);

		}

		else {

			// System.out.println(
			// "Salida del metodo run de ThreadLightsStatus por causas desconicidas
			// solicitado del thread "
			// + this.siganlthreadname);
			this.log.write(
					"Salida  del  metodo run de ThreadLightsStatus por causas desconicidas solicitado del thread "
							+ this.siganlthreadname);

		}
	}

	private synchronized void verifylightsstatus() {

		File lightstatusfile;

		lightstatusfile = new File(this.mainconfig.getApppaths().getLigthscontrolspool(), "ligths.out");

		// System.out.println("Abriendo el archivo " +
		// lightstatusfile.getAbsolutePath());
		// this.log.write("Abriendo el archivo " + lightstatusfile.getAbsolutePath()");

		try (FileOutputStream channel = new FileOutputStream(lightstatusfile)) {

			try (PrintStream lightstatusstream = new PrintStream(channel)) {

				try {
					if (this.idigitaloutput.getPinStatus() == true) {
						lightstatusstream.println("1");
					} else {
						lightstatusstream.println("0");							
					}
					
					this.signalcode = this.threadcontext.getSignalcode();
					this.siganlthreadname = this.threadcontext.getSignalthreadname();

					
				} catch (PinNumberNovalidException e) {
	
					this.log.write("Error al leer el estado del pin");
					this.log.write(e);
					this.signalcode = 1;
					this.siganlthreadname = "ThreadLightsStatus";
					this.threadcontext.setSignalcode(1);
					this.threadcontext.setSignalthreadname("ThreadLightsStatus");

					
				}
				catch(OperationNotWorkException e) {
			
					this.log.write("Error al leer el estado del pin");
					this.log.write(e);
					this.signalcode = 1;
					this.siganlthreadname = "ThreadLightsStatus";
					this.threadcontext.setSignalcode(1);
					this.threadcontext.setSignalthreadname("ThreadLightsStatus");

					
				}
			}

		} catch (FileNotFoundException e) {

			// System.out.println("Error al abrir el archivo " +
			// lightstatusfile.getAbsolutePath());
			this.log.write("Error al abrir el archivo " + lightstatusfile.getAbsolutePath());
			this.log.write(e);
			this.signalcode = 1;
			this.siganlthreadname = "ThreadLightsStatus";
			this.threadcontext.setSignalcode(1);
			this.threadcontext.setSignalthreadname("ThreadLightsStatus");

		}

		catch (IOException e) {

			// System.out.println("Error al abrir el archivo " +
			// lightstatusfile.getAbsolutePath());
			this.log.write("Error al abrir el archivo " + lightstatusfile.getAbsolutePath());
			this.log.write(e);
			this.signalcode = 1;
			this.siganlthreadname = "ThreadLightsStatus";
			this.threadcontext.setSignalcode(1);
			this.threadcontext.setSignalthreadname("ThreadLightsStatus");

		}

	}

}
