package esmeralda.projects.ligthscriptdaemon;

import java.io.File;

import esmeralda.libs.utils.Log;

public class PowerOffReboot {
	 LoadConfig mainconfig;
	 Log log;
	public PowerOffReboot ( LoadConfig mainconfig) {
		
		this.mainconfig=mainconfig;
		this.log=mainconfig.getLogfile();
	}
	

	public void reboot() {
		System.out.println("Solicitado reinicio");
		log.write("Solicitado reinicio");
		if (this.cleafiles()==true) {
			
			System.out.println("Realizando reinicio");
			log.write("Realizando reinicio");	
			log.close();
			
			
			try {
				
				Runtime.getRuntime().exec("/sbin/reboot");

				
			}
			catch(Exception e) {
				System.out.println("Error al realizar el reinicio");

				
			}
			
			
		}
		else{
			
			System.out.println("Reinicio cancelado");
			log.write("Reinicio cancelado");	
			log.close();
			
		}
	
		
	}
	public void poweroff() {
		
		System.out.println("Solicitado apagado");
		log.write("Solicitado apagado");
		if (this.cleafiles()==true) {
			
			System.out.println("Realizando apagado");
			log.write("Realizando apagado");			
			log.close();
			
			try {
				
				Runtime.getRuntime().exec("/sbin/poweroff");

				
			}
			catch(Exception e) {
				System.out.println("Error al realizar el apagado");

				
			}
			
			
		}
		else{
			
			System.out.println("Apagado cancelado");
			log.write("Apagado cancelado");	
			log.close();
			
		}
		
	}
	
	
	private boolean cleafiles() {
		
		File[] filelist;;
		File currentfile;
		boolean retval;
		int i;
		
		currentfile=null;
		retval=true;
		
		System.out.println("Realizando limpieza de archivos");
		log.write("Realizando limpieza de archivos");
		filelist=this.mainconfig.getApppaths().getSystemscriptspool().listFiles();
		i=0;
		while(i<filelist.length && retval==true) {
			
			currentfile=filelist[i];
			
			
			System.out.println("Borrando el archivo " + currentfile.getAbsolutePath());
			log.write("Borrando el archivo " + currentfile.getAbsolutePath());
			
			retval=currentfile.delete();
			i++;
			
			
		}
		i=0;
		filelist=this.mainconfig.getApppaths().getLigthscontrolspool().listFiles();
		i=0;
		while(i<filelist.length && retval==true) {
			
			currentfile=filelist[i];
			
			
			System.out.println("Borrando el archivo " + currentfile.getAbsolutePath());
			log.write("Borrando el archivo " + currentfile.getAbsolutePath());
			
			retval=currentfile.delete();
			i++;
			
			
		}
		if (retval==false) {
			
			System.out.println("Error al borrar el archivo " + currentfile.getAbsolutePath());
			log.write("Error al borrar el archivo " + currentfile.getAbsolutePath());
			

			
			
		}
		
		
		return retval;
	
	}
	
	
}
