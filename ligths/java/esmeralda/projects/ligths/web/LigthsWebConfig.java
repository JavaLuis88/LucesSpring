package esmeralda.projects.ligths.web;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SuppressWarnings("deprecation")
@Configuration
@ComponentScan
@EnableWebMvc



public class LigthsWebConfig extends WebMvcConfigurerAdapter{


	
	@Override
	 public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/back/index.html").setViewName("back/indexback");
	        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/vendor/**").addResourceLocations("/bower_components/");
	    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	    registry.addResourceHandler("/images/**").addResourceLocations("/images/");
	}
	

	
	  @Override
	    public void configureViewResolvers(ViewResolverRegistry registry) {
	        registry.jsp("/WEB-INF/views/", ".jsp");
	    }

		@Bean
		public ViewResolver internalviewresolver() {
			
			InternalResourceViewResolver view = new InternalResourceViewResolver();
			view.setPrefix("/WEB-INF/views/");
			view.setSuffix(".jsp");
			return view;
			
		}


		 @Bean
		 public ResourceBundleMessageSource messageSource() {
		        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		        messageSource.setBasename("Messages");
		        return messageSource;
		    }
}
