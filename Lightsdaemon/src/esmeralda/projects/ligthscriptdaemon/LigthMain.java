package esmeralda.projects.ligthscriptdaemon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import esmeralda.libs.RPI.GPIO.DigitalOutput;
import esmeralda.libs.RPI.GPIO.IDigitalOuput;
import esmeralda.libs.RPI.GPIO.PinNumberNovalidException;
import esmeralda.libs.utils.Log;
import esmeralda.libs.utils.LogException;

public class LigthMain {

	public static void main(String[] args) throws Exception {

		LoadConfig mainconfig;
		ThreadContext threadcontext;
		ThreadScriptControl tsc;
		ThreadLightsStatus tls;
		ThreadLightsControl tlc;
		PowerOffReboot powerreboot;
		int pinnumber;

		Log log;

		if (args.length < 1) {

			System.err.println("Error faltan parametros");
			System.err.println("Sytaxis: java -jar ligtscript.jar rutaarchivoconfiguracion principal");

		} else {

			// System.out.println("Iniciando");
			// System.out.println("Cargando configuracion");

			mainconfig = new LoadConfig(new File(args[0]));
			log = mainconfig.getLogfile();
			// System.out.println("Instaciando threads");
			// log.write("instaciando threads");
			log.write("Iniciado");

			threadcontext = new ThreadContext();

			tsc = new ThreadScriptControl(threadcontext, mainconfig);
			try {

				pinnumber = Integer.parseInt(mainconfig.getLightsproperties().getProperty("pin"), 10);

			} catch (NumberFormatException e) {

				pinnumber = 0;
			}

			
			
			try (IDigitalOuput idigitaloutput = new DigitalOutput(pinnumber)) {

				tls = new ThreadLightsStatus(threadcontext, mainconfig, idigitaloutput);
				tlc = new ThreadLightsControl(threadcontext, mainconfig, idigitaloutput);

				// System.out.println("Arrancado threads");
				// log.write("Arrancado threads");
				tsc.start();
				tls.start();
				tlc.start();
				while (tsc.isAlive() == true || tls.isAlive()==true || tlc.isAlive()==true) {
					try {
					} catch (Exception e) {

					}
				}

				if (threadcontext.getSignalcode() == 0) {

					// System.out.println("Finalización normal");
					log.write("Finalización normal");

				}

				else if (threadcontext.getSignalcode() == 1) {

					// System.out.println("Finalización con errores de ejecucion");
					log.write("Finalización con errores de ejecucion");

				} else if (threadcontext.getSignalcode() == 2) {

					// System.out.println("Finalización por reinicio");
					log.write("Finalización por reinicio");

					powerreboot = new PowerOffReboot(mainconfig);
					powerreboot.reboot();

				} else if (threadcontext.getSignalcode() == 3) {

					// System.out.println("Finalización por apagado");
					log.write("Finalización por apagado");

					powerreboot = new PowerOffReboot(mainconfig);
					powerreboot.poweroff();

				}

				else {

					// System.out.println("Finalización desconocida");
					log.write("Finalización desconocida");

				}

			} catch (PinNumberNovalidException e) {
				// TODO: handle exception
				log.write("Numero de pin no valido");
				log.write(e);
				log.close();
			}

			catch (IOException e) {
				// TODO: handle exception
				log.write("Numero de pin no valido");
				log.write(e);
				log.close();

			}

		}

	}

}
