package esmeralda.projects.ligths.web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import esmeralda.projects.ligths.business.ILigthsConfig;
import esmeralda.projects.ligths.business.IUserManager;
import esmeralda.projects.ligths.business.SHA256Encoder;
import esmeralda.projects.ligths.web.controller.JwtFilter;

@Configuration
@EnableWebSecurity

public class LigthsWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	
	@Autowired
	IUserManager usermanager;
	
	@Autowired
	private ILigthsConfig ligthsconfig;

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT username,password, true as enabled FROM users WHERE username = ?")
				.authoritiesByUsernameQuery(
						"SELECT u.username as username ,r.role as authority from users u , roles r where u.id=r.iduser and u.username=?");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().ignoringAntMatchers("/rest/**").and().authorizeRequests().antMatchers("/rest/login").permitAll()
				.antMatchers("/back/**").hasAnyRole("ADMIN").antMatchers("/rest/**").hasAnyRole("USER").and()
				.formLogin().loginPage("/back/index.html").permitAll().defaultSuccessUrl("/back/users.html").and()
				.logout().logoutUrl("/back/logout").permitAll().and().exceptionHandling()
				.accessDeniedPage("/back/accessdenied").and()
				.addFilterBefore(
						new LoginFilter("/rest/login", authenticationManager(), this.ligthsconfig.getSecretKey()),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JwtFilter(usermanager,ligthsconfig), UsernamePasswordAuthenticationFilter.class);

		;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new SHA256Encoder();
	}

}
