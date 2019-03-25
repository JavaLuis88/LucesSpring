
package esmeralda.projects.ligths.web;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

public class JwtUtil {

	 public static void addAuthentication(HttpServletResponse res, String username,String secretkey) {

    	String token;
    	
        token = Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + 300000))
            .signWith(SignatureAlgorithm.HS512, secretkey)
            .compact();
        res.addHeader("Authorization", token);
        
        //.setExpiration(new Date(System.currentTimeMillis() + 43200000))
        
    }

    public static Authentication getAuthentication(HttpServletRequest request,HttpServletResponse response, String[] roles,String secretkey) throws IOException {

    	 String token;
    	 String user;
    	 UsernamePasswordAuthenticationToken retval;
    	 
    	 
    	 retval=null;
    	 
        token = request.getHeader("Authorization");

        if (token != null) {
            user = Jwts.parser()
                    .setSigningKey(secretkey)
                    .parseClaimsJws(token) 
                    .getBody()
                    .getSubject();

       
            if(user != null) {
            	
            	retval= new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList(roles));
            	JwtUtil.addAuthentication(response, user, secretkey);
            	
            }
        
          
        }

        return retval;
    }

    /*
    public static Authentication getAuthentication(HttpServletRequest request, String[] roles,String secretkey) throws IOException {

   	 String token;
   	 String user;
   	 
       token = request.getHeader("Authorization");

       if (token != null) {
           user = Jwts.parser()
                   .setSigningKey(secretkey)
                   .parseClaimsJws(token) 
                   .getBody()
                   .getSubject();

      
           return user != null ?
                   new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList(roles)) :
                   null;

       }

       return null;
   }
    */
    
    
    
    
    
}



