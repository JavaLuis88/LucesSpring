package esmeralda.projects.ligthscriptdaemon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import esmeralda.libs.utils.Log;
import esmeralda.libs.utils.LogException;

public class LoadConfig {



	private Properties mainproperties;
	private Properties lightsproperties;
	
	private AppPaths apppaths;
	private Log logfile;

	public LoadConfig(File mainconfigfile) throws Exception {

		File logfilepath;

		this.mainproperties = new Properties();
		this.lightsproperties=new Properties();
		
		try {
			//System.out.println("Abriendo el archivo principal de configuración");
			
			FileInputStream channel = new FileInputStream(mainconfigfile);

			try {
				this.mainproperties.load(channel);

				this.apppaths = new AppPaths(mainproperties.getProperty("systemscriptspool"),
						mainproperties.getProperty("ligthscontrolspool"), mainproperties.getProperty("appfolder"),
						mainproperties.getProperty("logfolder"));

				logfilepath = new File(apppaths.getLogFolder(), "scriptcontroldaemom.log");
				//System.out.println("Abriendo el archivo de logs " + logfilepath.getCanonicalPath());
				try {
					this.logfile = new Log(logfilepath.getAbsolutePath());
				} catch (LogException e) {
					// TODO Auto-generated catch block
					System.err.println("Error al abrir el archivo de logs " + logfilepath.getCanonicalPath());
					e.printStackTrace();
					throw e;
				}
				
						
			} catch (IOException e) {
				// TODO Auto-generated catch block

				System.err.println("Error al leer el archivo principal de configuración");
				e.printStackTrace();
				throw e;

			} finally {

				try {
					//System.out.println("Cerrando el archivo principal de configuración");
					channel.close();
				} catch (IOException e) {
					//System.out.println("Error al cerrar el archivo principal de configuración");
					e.printStackTrace();
					throw e;
					
				}
			}

		} catch (FileNotFoundException e) {

			System.err.println("Error al abrir el archivo principal de configuración");
			e.printStackTrace();
			throw e;
			
		}

		

		try {
			//System.out.println("Abriendo el archivo de cofiguarcion de luces");
			
			FileInputStream channel = new FileInputStream(new File(this.getApppaths().getCurrentconfigpath(),"lights.properties"));

			try {
				this.lightsproperties.load(channel);

				
				try {
					this.logfile = new Log(logfilepath.getAbsolutePath());
				} catch (LogException e) {
					// TODO Auto-generated catch block
					System.err.println("Error al abrir el archivo de logs " + logfilepath.getCanonicalPath());
					e.printStackTrace();
					throw e;
				}
				
						
			} catch (IOException e) {
				// TODO Auto-generated catch block

				System.err.println("Error al leer el archivo principal de configuración");
				e.printStackTrace();
				throw e;

			} finally {

				try {
					//System.out.println("Cerrando el archivo de configuración de las luces");
					channel.close();
				} catch (IOException e) {
					//System.out.println("Error al cerrar el archivo de configuracion de las luces");
					
					e.printStackTrace();
					throw e;
					
				}
			}

		} catch (FileNotFoundException e) {

			System.err.println("Error al abrir el archivo principal de configuración");
			e.printStackTrace();
			throw e;
			
		}

		
	}

	
	public Properties getMainproperties() {
		return this.mainproperties;
	}

	public Properties getLightsproperties() {
		return this.lightsproperties;
	}

	
	public AppPaths getApppaths() {
		return this.apppaths;
	}


	public Log getLogfile() {
		return this.logfile;
	}

	
	
}
