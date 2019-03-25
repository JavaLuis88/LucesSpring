package esmeralda.projects.ligths;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import esmeralda.projects.ligths.business.LigthsBusinessConfig;
import esmeralda.projects.ligths.dao.DAOConfig;
import esmeralda.projects.ligths.web.LigthsWebConfig;
import esmeralda.projects.ligths.web.LigthsWebSecurityConfig;
import esmeralda.projects.ligths.web.controller.JwtFilter;


public class LigthsWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {

	
		Class<?>[] retval=new Class<?>[3];
		retval[0]=LigthsBusinessConfig.class;
		retval[1]=DAOConfig.class;
		retval[2]=LigthsWebSecurityConfig.class;
		
		
		return retval;
			
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		   return new Class<?>[] { LigthsWebConfig.class };
	
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/"};
	}

	
	
	
	
}
