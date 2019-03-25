package esmeralda.projects.ligths.business;

import java.io.File;

import org.springframework.stereotype.Service;

public class AppPaths implements IAppPaths {

	private File systemscriptpoolspath;
	private File ligthscontrolspoolpath;
	private File appfolder;
	private File currentconfigpath;
	private File logfolderpath;

	public AppPaths(String systemscriptspool, String ligthscontrolspool, String appfolder, String logfolder) {
		// TODO Auto-generated constructor stub
		if (systemscriptspool.charAt(systemscriptspool.length() - 1) == File.separatorChar) {

			this.systemscriptpoolspath = new File(systemscriptspool);

		} else {

			this.systemscriptpoolspath = new File(systemscriptspool + File.separatorChar);

		}

		if (ligthscontrolspool.charAt(ligthscontrolspool.length() - 1) == File.separatorChar) {

			this.ligthscontrolspoolpath = new File(ligthscontrolspool);

		} else {

			this.ligthscontrolspoolpath = new File(ligthscontrolspool + File.separatorChar);

		}

		if (appfolder.charAt(appfolder.length() - 1) == File.separatorChar) {

			this.appfolder = new File(appfolder);

		} else {

			this.appfolder = new File(appfolder + File.separatorChar);

		}

		this.currentconfigpath = new File(this.appfolder, "conf" + File.separatorChar);

		if (logfolder.charAt(logfolder.length() - 1) == File.separatorChar) {

			this.logfolderpath = new File(logfolder);

		} else {

			this.logfolderpath = new File(logfolder + File.separatorChar);

		}

		

		
		
		this.systemscriptpoolspath.mkdirs();
		this.ligthscontrolspoolpath.mkdirs();
		this.appfolder.mkdirs();
		this.currentconfigpath.mkdirs();
		this.logfolderpath.mkdirs();
	}

	@Override
	public File getSystemscriptspool() {
		// TODO Auto-generated method stub
		return this.systemscriptpoolspath;
	}

	@Override
	public File getLigthscontrolspool() {
		// TODO Auto-generated method stub
		return this.ligthscontrolspoolpath;
	}

	@Override
	public File getAppfolder() {
		// TODO Auto-generated method stub
		return this.appfolder;
	}

	@Override
	public File getCurrentconfigpath() {
		// TODO Auto-generated method stub
		return this.currentconfigpath;
	}

	@Override
	public File getLogFolder() {
		// TODO Auto-generated method stub
		return this.logfolderpath;
	}


}
