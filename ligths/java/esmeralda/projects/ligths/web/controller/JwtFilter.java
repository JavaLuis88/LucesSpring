
package esmeralda.projects.ligths.web.controller;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.GenericFilterBean;

import esmeralda.projects.ligths.business.ILigthsConfig;
import esmeralda.projects.ligths.business.IUserManager;
import esmeralda.projects.ligths.dao.entities.Roles;
import esmeralda.projects.ligths.web.JwtUtil;
import io.jsonwebtoken.Jwts;

@Controller
public class JwtFilter extends GenericFilterBean {

	
	//@Autowired
	//IUserManager usermanager;

	//@Autowired
	//ILigthsConfig ligthsconfig;


	private IUserManager usermanager;
	private ILigthsConfig ligthsconfig;
	//private String secretkey;
	
	
	public JwtFilter(IUserManager usemanager,ILigthsConfig ligthsconfig) {
		// TODO Auto-generated constructor stub
		this.usermanager=usemanager;
		this.ligthsconfig=ligthsconfig;
		//this.secretkey=secretkey;
	}
	
	
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

    	// Obtenemos el token que viene en el encabezado de la peticion
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        String[] list_roles = null;
        List<Roles> roles;
     
        if (token != null) {
            String username = Jwts.parser()
                    .setSigningKey(this.ligthsconfig.getSecretKey()) //la clave secreta esta declara en JwtUtil
                    .parseClaimsJws(token) //este metodo es el que valida
                    .getBody()
                    .getSubject();        //extraigo el nombre de usuario del token

	        /*De manera "sucia" vamos a obtener los roles del usuario*/
	        //DataBaseConfiguration db = new DataBaseConfiguration();       
	        //JdbcTemplate jdbcTemplate = new JdbcTemplate(db.dataSource());
/*
	        Collection roles = jdbcTemplate.query(
	                "select user_role_id,username,role from user_roles where username='" + username + "'", new RowMapper() {
						@Override
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							// TODO Auto-generated method stub
							Rol rol = new Rol();
	                        rol.setId(rs.getLong("user_role_id"));
	                        rol.setUsername(rs.getString("username"));
	                        rol.setRol(rs.getString("role"));
	                        return rol;
						}
	                });*/

            
            roles=this.usermanager.getUserRoles(this.usermanager.getUserByUserName(username).getId());


            
	        list_roles = new String[roles.size()];
	
	        
	        
	        
	        //int i=0;       
	        //for (Object rol : roles) {
	        //	list_roles[i] = rol.toString();
	        //	i++;
	        //}
        //}
	        
	        for (int i=0;i<roles.size();i++) {
	        	
	        	list_roles[i] = roles.get(i).getRole();
	        	
	        }
	        
	        
        //Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest)request, list_roles,ligthsconfig.getSecretKey());
        Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest)request, (HttpServletResponse)response,list_roles,ligthsconfig.getSecretKey());

        //ServletResponse response
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);

    }
}


