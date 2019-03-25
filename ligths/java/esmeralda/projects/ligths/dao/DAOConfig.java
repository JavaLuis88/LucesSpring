package esmeralda.projects.ligths.dao;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import esmeralda.projects.ligths.dao.entities.Users;

@Configuration
@ComponentScan
@PropertySource("classpath:appconfig.properties")
@EnableTransactionManagement
@EnableJpaRepositories("esmeralda.projects.ligths.dao.repositories")
public class DAOConfig {
	
	
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource datasource=new DriverManagerDataSource();
		datasource.setDriverClassName(env.getProperty("db.driver"));
		datasource.setUrl(env.getProperty("db.url"));		
		datasource.setUsername(env.getProperty("db.username"));
		datasource.setPassword(env.getProperty("db.password"));
		
		return datasource;
	}
	

	   @Bean
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

	        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
	        emf.setDataSource(dataSource());
	        emf.setPackagesToScan(Users.class.getPackage().getName());
	        
	        HibernateJpaVendorAdapter hibernateJpa = new HibernateJpaVendorAdapter();
	        hibernateJpa.setDatabase(Database.MYSQL);
	        hibernateJpa.setDatabasePlatform(env.getProperty("hibernate.dialect"));
	        hibernateJpa.setGenerateDdl(env.getProperty("hibernate.generateDdl", Boolean.class));
	        hibernateJpa.setShowSql(env.getProperty("hibernate.show_sql", Boolean.class));       
	        emf.setJpaVendorAdapter(hibernateJpa);
	        
	        Properties jpaProperties = new Properties();
	        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
	        jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
	        jpaProperties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
	        emf.setJpaProperties(jpaProperties);
	        
	        return emf;
	    }
	    
	    @Bean
	    public JpaTransactionManager transactionManager() {
	        JpaTransactionManager txnMgr = new JpaTransactionManager();
	        txnMgr.setEntityManagerFactory(entityManagerFactory().getObject());
	        return txnMgr;
	    }
	
	

	  
}

