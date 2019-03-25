package esmeralda.projects.ligths.business;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan
@PropertySource("classpath:appconfig.properties")
public class LigthsBusinessConfig {

	
	@Autowired
	private Environment env;
	
	@Bean
	public IAppPaths appPaths()
	{
		
		
		return new AppPaths(env.getProperty("systemscriptspool"), env.getProperty("ligthscontrolspool"), env.getProperty("appfolder"), env.getProperty("logfolder"));
		
		
	}

	
	@Bean
	public IRouterConfig routerConfig()
	{
		
		RouterConfig retval;
		retval=null;
		
		
		try {
			retval=new RouterConfig(appPaths());
		} catch (IOException e) {
		}
		
		return retval;
		
		
	}
	
	
	
	@Bean
	public IPoweroffReboot poweroffrebootConfig()
	{
		
	
		return new PoweroffReboot(appPaths());
		
		
	}
	
	
	
	@Bean
	public ILigthsConfig ligthsConfig()
	{
		
		LigthsConfig retval;
		retval=null;
		
		
		try {
			retval=new LigthsConfig(appPaths());
		} catch (IOException e) {

		}
		
		return retval;
		
		
	}
	
	
	@Bean
	public ILightsHandler ligthsHandler()
	{
		
		return new LightsHandler(appPaths());
		
		
	}
	
	
}
