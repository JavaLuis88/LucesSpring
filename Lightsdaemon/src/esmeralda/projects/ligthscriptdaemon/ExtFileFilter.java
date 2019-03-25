package esmeralda.projects.ligthscriptdaemon;

import java.io.File;
import java.io.FilenameFilter;

public class ExtFileFilter implements FilenameFilter{

	
	private String ext;
	
	
	public ExtFileFilter(String ext) {
		this.ext=ext;
	}
	
	
	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		return (name.endsWith(this.ext)==true && (new File(dir,name)).isFile()==true);
	}

}
