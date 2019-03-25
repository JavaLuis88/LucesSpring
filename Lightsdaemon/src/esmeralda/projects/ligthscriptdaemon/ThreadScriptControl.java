package esmeralda.projects.ligthscriptdaemon;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import esmeralda.libs.utils.Log;
import esmeralda.libs.utils.LogException;

public class ThreadScriptControl extends Thread {

	private ThreadContext threadcontext;
	private LoadConfig mainconfig;
	private Log log;
	private boolean execute;
	private int signalcode;
	private String siganlthreadname;
	private Runtime runtime;

	public ThreadScriptControl(ThreadContext threadcontext, LoadConfig mainconfig) {
		// TODO Auto-generated constructor stub
		super();
		//System.out.println("Inciando thread TheadScriptControl");
		this.threadcontext = threadcontext;
		this.mainconfig = mainconfig;

		//this.mainconfig.getLogfile().write("Inciando thread TheadScriptControl");

		this.log = mainconfig.getLogfile();

		//System.out.println("Thread TheadScriptControl iniciado");
		//this.log.write("Thread TheadScriptControl iniciado");
	}

	public void run() {

		//System.out.println("iniciando metodo run de TheadScriptControl");
		//this.log.write("iniciando metodo run de TheadScriptControl");


		this.execute = true;
		this.signalcode = 0;
		this.siganlthreadname = "";

		//System.out.println("Obteniendo Runtime en TheadScriptControl");
		//this.log.write("Obteniendo Runtime en TheadScriptControl");

		this.runtime = Runtime.getRuntime();

		//System.out.println("iniciando thread en TheadScriptControl");
		this.log.write("iniciando thread en TheadScriptControl");

		while (this.execute == true && this.signalcode == 0) {

			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
			this.executescripts();

		}

		if (this.signalcode == 0) {

			//System.out.println("Salida normal del  metodo run de TheadScriptControl");
			this.log.write("Salida normal del metodo run de TheadScriptControl");

		} else if (this.signalcode == 1) {

			//System.out.println(
			//		"Salida  del  metodo run de TheadScriptControl por error del thread " + this.siganlthreadname);
			this.log.write(
					"Salida  del  metodo run de TheadScriptControl por error del thread " + this.siganlthreadname);

		}

		else if (this.signalcode == 2) {

			//System.out.println("Salida  del  metodo run de TheadScriptControl reinicio solicitado del thread "
			//		+ this.siganlthreadname);
			this.log.write("Salida  del  metodo run de TheadScriptControl reinicio solicitado del thread "
					+ this.siganlthreadname);

		}

		else if (this.signalcode == 3) {

			//System.out.println("Salida  del  metodo run de TheadScriptControl apagado solicitado del thread "
			//		+ this.siganlthreadname);
			this.log.write("Salida  del  metodo run de TheadScriptControl apagado solicitado del thread "
					+ this.siganlthreadname);

		} else {

			//System.out.println(
			//		"Salida  del  metodo run de TheadScriptControl por causas desconiciidas solicitado del thread "
			//				+ this.siganlthreadname);
			this.log.write(
					"Salida  del  metodo run de TheadScriptControl por causas desconiciidas solicitado del thread "
							+ this.siganlthreadname);

		}
	}

	private synchronized void executescripts() {
		File[] scriptfilespath;
		File currentfile;
		Process proccess;
		int count;
		int retval;
		int i;

		//System.out.println(
		//		"Listando archivos de script en el directorio " + this.mainconfig.getApppaths().getSystemscriptspool());
		//log.write(
		//		"Listando archivos de script en el directotio " + this.mainconfig.getApppaths().getSystemscriptspool());

		scriptfilespath = this.mainconfig.getApppaths().getSystemscriptspool().listFiles(new ExtFileFilter(".sh"));

		
		count=scriptfilespath.length;
		
		this.signalcode = this.threadcontext.getSignalcode();
		this.siganlthreadname = this.threadcontext.getSignalthreadname();
		retval = 0;
		i = 0;
		while (i < count && this.signalcode == 0) {

			currentfile = scriptfilespath[i];
			if (currentfile.exists() == true && currentfile.isFile() == true) {

				System.out.println("Ejecutando el archivo " + currentfile.getAbsolutePath());
				log.write("Ejecutando el archivo " + currentfile.getName());

				try {

					proccess = runtime.exec("/bin/bash " + currentfile.getCanonicalPath());
					proccess.waitFor();
					retval = proccess.exitValue();

					if (retval == 0) {

						//System.out.println("Ejecutando con exito el archivo " + currentfile.getAbsolutePath());
						//log.write("Ejecutando con exito el archivo " + currentfile.getAbsolutePath());

						//System.out.println("Borrando el archivo " + currentfile.getAbsolutePath());
						//log.write("Borrando el archivo " + currentfile.getAbsolutePath());

						if (currentfile.delete() == true) {

							//System.out.println("Borrado con exito el archivo " + currentfile.getAbsolutePath());
							log.write("Borrado con exito el archivo " + currentfile.getAbsolutePath());
							this.signalcode = this.threadcontext.getSignalcode();
							this.siganlthreadname = this.threadcontext.getSignalthreadname();

						} else {

							//System.out.println("Borrado sin exito el archivo " + currentfile.getAbsolutePath());
							log.write("Borrado sin exito el archivo " + currentfile.getAbsolutePath());

							this.signalcode = 1;
							this.siganlthreadname = "TheadScriptControl";
							this.threadcontext.setSignalcode(1);
							this.threadcontext.setSignalthreadname("TheadScriptControl");

						}

					} else if (retval == 1) {

						//System.out.println(
						//		"El archivo " + currentfile.getAbsolutePath() + " ha devuelto un codigo de error 1");
						log.write("El archivo " + currentfile.getAbsolutePath() + " ha devuelto un codigo de error 1");

						this.signalcode = retval;
						this.siganlthreadname = "TheadScriptControl";
						this.threadcontext.setSignalcode(retval);
						this.threadcontext.setSignalthreadname("TheadScriptControl");

					} else if (retval == 2) {

						//System.out.println("El archivo " + currentfile.getAbsolutePath()
						//		+ " ha devuelto un codigo de error 2 para solicitar reinicio");
						log.write("El archivo " + currentfile.getAbsolutePath()
								+ " ha devuelto un codigo de error 2 para solicitar reinicio");

						if (currentfile.delete() == true) {

							//System.out.println("Borrado con exito el archivo " + currentfile.getAbsolutePath());
							//log.write("Borrado con exito el archivo " + currentfile.getAbsolutePath());

							this.signalcode = retval;
							this.siganlthreadname = "TheadScriptControl";
							this.threadcontext.setSignalcode(retval);
							this.threadcontext.setSignalthreadname("TheadScriptControl");

						} else {

							//System.out.println("Borrado sin exito el archivo " + currentfile.getAbsolutePath());
							log.write("Borrado sin exito el archivo " + currentfile.getAbsolutePath());
							this.signalcode = 1;
							this.siganlthreadname = "TheadScriptControl";
							this.threadcontext.setSignalcode(1);
							this.threadcontext.setSignalthreadname("TheadScriptControl");

						}

					}

					else if (retval == 3) {

						//System.out.println("El archivo " + currentfile.getAbsolutePath()
						//		+ " ha devuelto un codigo de error 3 para solicitar apagdo");
						log.write("El archivo " + currentfile.getAbsolutePath()
								+ " ha devuelto un codigo de error 3 para solicitar apagado");

						if (currentfile.delete() == true) {

							//System.out.println("Borrado con exito el archivo " + currentfile.getAbsolutePath());
							//log.write("Borrado con exito el archivo " + currentfile.getAbsolutePath());

							this.signalcode = retval;
							this.siganlthreadname = "TheadScriptControl";
							this.threadcontext.setSignalcode(retval);
							this.threadcontext.setSignalthreadname("TheadScriptControl");

						} else {

							//System.out.println("Borrado sin exito el archivo " + currentfile.getAbsolutePath());
							log.write("Borrado sin exito el archivo " + currentfile.getAbsolutePath());

							this.signalcode = 1;
							this.siganlthreadname = "TheadScriptControl";
							this.threadcontext.setSignalcode(retval);
							this.threadcontext.setSignalthreadname("TheadScriptControl");

						}

					} else {

						//System.out.println("El archivo " + currentfile.getAbsolutePath()
						//		+ " ha devuelto un codigo de error " + retval);
						log.write("El archivo " + currentfile.getAbsolutePath() + " ha devuelto un codigo de error "
								+ retval);
						this.signalcode = this.threadcontext.getSignalcode();
						this.siganlthreadname = this.threadcontext.getSignalthreadname();

					}

				} catch (Exception e3) {

					//System.out.println("Error al ejecutar el archivo " + currentfile.getAbsolutePath());
					log.write("Error al ejecutar el  archivo " + currentfile.getAbsolutePath());
					log.write(e3);
					this.signalcode = 1;
					//this.siganlthreadname = this.threadcontext.getSignalthreadname();
					this.siganlthreadname="TheadScriptControl";

					
					
					this.threadcontext.setSignalcode(retval);
					this.threadcontext.setSignalthreadname("TheadScriptControl");

					
				}

			} else {
				//System.out.println("No se ha encontrado el archivo " + currentfile.getAbsolutePath());
				log.write("No se ha encontrado el archivo " + currentfile.getAbsolutePath());
				this.signalcode = this.threadcontext.getSignalcode();
				this.siganlthreadname = this.threadcontext.getSignalthreadname();

			}

			i++;

		}

		//if (this.signalcode == 0) {

			//System.out.println("Salida del recorrido de archivos normal");
			//log.write("Salida del recorrido de archivos");

		//}

		//else 
		if (this.signalcode == 1) {

			//System.out.println("Salida del recorrido de archivos por erorres");
			log.write("Salida del recorrido de archivos por erorres");

		} else if (this.signalcode == 2) {

			//System.out.println("Salida del recorrido de archivos por reincio");
			log.write("Salida del recorrido de archivos por reinicio");

		} else if (this.signalcode == 3) {

			//System.out.println("Finalización por apagado");
			log.write("Finalización por apagado");

		}

		else if (this.signalcode != 0) {

			//System.out.println("Finalización desconocida");
			log.write("Finalización desconocida");

		}

	}

}
