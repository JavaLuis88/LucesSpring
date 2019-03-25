package esmeralda.projects.ligthscriptdaemon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class ThreadLightsControl extends Thread {

	private ThreadContext threadcontext;
	private LoadConfig mainconfig;
	private IDigitalOuput idigitaloutput;
	private Log log;
	private boolean execute;
	private int signalcode;
	private String siganlthreadname;
	private Runtime runtime;

	public ThreadLightsControl(ThreadContext threadcontext, LoadConfig mainconfig, IDigitalOuput idigitaloutput) {
		// TODO Auto-generated constructor stub
		super();
		//System.out.println("Inciando thread ThreadLightsControl");
		this.threadcontext = threadcontext;
		this.mainconfig = mainconfig;
		this.idigitaloutput = idigitaloutput;
		// this.mainconfig.getLogfile().write("Inciando thread ThreadLightsControl");

		this.log = mainconfig.getLogfile();

		//System.out.println("Thread ThreadLightsControl iniciado");
		// this.log.write("Thread ThreadLightsControl iniciado");
	}

	public void run() {

		//System.out.println("iniciando metodo run de ThreadLightsControl");
		// this.log.write("iniciando metodo run de ThreadLightsControl");

		this.execute = true;
		this.signalcode = 0;
		this.siganlthreadname = "";

		// System.out.println("Obteniendo Runtime en ThreadLightsControl");
		// this.log.write("Obteniendo Runtime en ThreadLightsControl");

		this.runtime = Runtime.getRuntime();

		//System.out.println("iniciando thread en ThreadLightsControl");
		this.log.write("iniciando thread en ThreadLightsControl");

		while (this.execute == true && this.signalcode == 0) {

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			this.executeligthsfiles();

		}

		if (this.signalcode == 0) {

			// System.out.println("Salida normal del metodo run de ThreadLightsControl");
			this.log.write("Salida normal del metodo run de ThreadLightsControl");
			this.signalcode = this.threadcontext.getSignalcode();
			this.siganlthreadname = this.threadcontext.getSignalthreadname();

		} else if (this.signalcode == 1) {

			// System.out.println(
			// "Salida del metodo run de ThreadLightsControl por error del thread " +
			// this.siganlthreadname);
			this.log.write(
					"Salida  del  metodo run de ThreadLightsControl por error del thread " + this.siganlthreadname);

		}

		else {

			// System.out.println(
			// "Salida del metodo run de ThreadLightsStatus por causas desconicidas
			// solicitado del thread "
			// + this.siganlthreadname);
			this.log.write(
					"Salida  del  metodo run de ThreadLightsControl por causas desconicidas solicitado del thread "
							+ this.siganlthreadname);

		}
	}

	private synchronized void executeligthsfiles() {

		File[] lightsfilespath;
		File currentfile;
		Process proccess;
		String line;
		int count;
		int retval;
		int i;

		//System.out
		//		.println("Listando archivos en el directorio " + this.mainconfig.getApppaths().getLigthscontrolspool());
		// log.write(
		// "Listando archivos script en el directotio " +
		// this.mainconfig.getApppaths().getLigthscontrolspool());

		lightsfilespath = this.mainconfig.getApppaths().getLigthscontrolspool().listFiles(new ExtFileFilter(".in"));

		count = lightsfilespath.length;

		this.signalcode = this.threadcontext.getSignalcode();
		this.siganlthreadname = this.threadcontext.getSignalthreadname();
		retval = 0;
		i = 0;

		while (i < count && this.signalcode == 0) {

			currentfile = lightsfilespath[i];
			if (currentfile.exists() == true && currentfile.isFile() == true) {

				//////////////////////////////

				// System.out.println("Leyendo el archivo " + currentfile.getAbsolutePath());
				// log.write("Leyendo el archivo " + currentfile.getName());

				try (FileInputStream channel = new FileInputStream(currentfile)) {

					try (InputStreamReader filter = new InputStreamReader(channel)) {

						try (BufferedReader buffer = new BufferedReader(filter)) {

							try {
								line = buffer.readLine();
								try {
						
									if (line != null && line.trim().equals("1") == true) {

										this.idigitaloutput.pinOn();

									} else {
										this.idigitaloutput.pinOff();

									}
								} catch (PinNumberNovalidException e) {

									// System.out.println("Error al enceder o apagar el pin");
									log.write("Error al enceder o apagar el pin");
									log.write(e);

									this.signalcode = 1;
									this.siganlthreadname = "ThreadLightsControl";
									this.threadcontext.setSignalcode(1);
									this.threadcontext.setSignalthreadname("ThreadLightsControl");

								} catch (IOException e) {

									// System.out.println("Error al enceder o apagar el pin");
									log.write("Error al enceder o apagar el pin");
									log.write(e);

									this.signalcode = 1;
									this.siganlthreadname = "ThreadLightsControl";
									this.threadcontext.setSignalcode(1);
									this.threadcontext.setSignalthreadname("ThreadLightsControl");

								}

								catch (OperationNotWorkException e) {

									// System.out.println("Error al enceder o apagar el pin");
									log.write("Error al enceder o apagar el pin");
									log.write(e);

									this.signalcode = 1;
									this.siganlthreadname = "ThreadLightsControl";
									this.threadcontext.setSignalcode(1);
									this.threadcontext.setSignalthreadname("ThreadLightsControl");

								}
							} catch (IOException e) {

								// System.out.println("Error al leer el archivo " +
								// currentfile.getAbsolutePath());
								log.write("Error al leer el archivo " + currentfile.getAbsolutePath());
								log.write(e);

								this.signalcode = 1;
								this.siganlthreadname = "ThreadLightsControl";
								this.threadcontext.setSignalcode(1);
								this.threadcontext.setSignalthreadname("ThreadLightsControl");

							}

						}

					}

				} catch (FileNotFoundException e) {
					// System.out.println("Error al abrir el archivo " +
					// currentfile.getAbsolutePath());
					log.write("Error al abrir el archivo " + currentfile.getName());
					log.write(e);

					this.signalcode = 1;
					this.siganlthreadname = "ThreadLightsControl";
					this.threadcontext.setSignalcode(1);
					this.threadcontext.setSignalthreadname("ThreadLightsControl");

				} catch (IOException e) {
					// System.out.println("Error al abrir el archivo " +
					// currentfile.getAbsolutePath());
					log.write("Error al abrir el archivo " + currentfile.getAbsolutePath());
					log.write(e);

					this.signalcode = 1;
					this.siganlthreadname = "ThreadLightsControl";
					this.threadcontext.setSignalcode(1);
					this.threadcontext.setSignalthreadname("ThreadLightsControl");

				}

				///////////////////////////////

				if (this.signalcode == 0) {
					if (currentfile.delete() ==true) {
						this.signalcode = this.threadcontext.getSignalcode();
						this.siganlthreadname = this.threadcontext.getSignalthreadname();

					
					}
					else {

						log.write("Error al borrar el archivo " + currentfile.getAbsolutePath());

						this.signalcode = 1;
						this.siganlthreadname = "ThreadLightsControl";
						this.threadcontext.setSignalcode(1);
						this.threadcontext.setSignalthreadname("ThreadLightsControl");

					}

				}

			} else {

				// System.out.println("No se ha encontrado el archivo " +
				// currentfile.getAbsolutePath());
				log.write("No se ha encontrado el archivo " + currentfile.getAbsolutePath());
				this.signalcode = this.threadcontext.getSignalcode();
				this.siganlthreadname = this.threadcontext.getSignalthreadname();

			}

			i++;
		}

		if (this.signalcode == 1) {

			 System.out.println("Salida del recorrido de archivos por erorres");
			log.write("Salida del recorrido de archivos por erorres");

		} else if (this.signalcode == 2) {

			 System.out.println("Salida del recorrido de archivos por reincio");
			log.write("Salida del recorrido de archivos por reinicio");

		} else if (this.signalcode == 3) {

			 System.out.println("Finalización por apagado");
			log.write("Finalización por apagado");

		}

		else if (this.signalcode != 0) {

			System.out.println("Finalización desconocida");
			log.write("Finalización desconocida");

		}

	}
}
