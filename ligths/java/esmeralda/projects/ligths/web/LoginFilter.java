
package esmeralda.projects.ligths.web;
import com.fasterxml.jackson.databind.ObjectMapper;

import esmeralda.projects.ligths.beans.UserInfo;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	
	private String secretkey;
	
	
	public LoginFilter(String url, AuthenticationManager authManager,String secretkey) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.secretkey=secretkey;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {

		InputStream body;
		UserInfo user;

		body = req.getInputStream();

		user = new ObjectMapper().readValue(body, UserInfo.class);

		//ArrayList<String> rr=new ArrayList<String>();
		//rr.add("USER_ROLE");
		//rr.add("ADMIN_ROLE");
		
		//user=new UserInfo("mariano", "TCLTK8",rr);
		
		
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
				user.getPassword(), Collections.emptyList()));

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		
		HttpServletRequest request;
		HttpServletResponse response;
		String clientOrigin;
		
		request = (HttpServletRequest) req;
		response = (HttpServletResponse) res;
		clientOrigin = request.getHeader("origin");

	

		response.addHeader("Access-Control-Allow-Origin", clientOrigin);
		response.setHeader("Access-Control-Allow-Methods", "POST, GET,  DELETE, PUT");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type, Origin, Authorization, X-Auth-Token");

		JwtUtil.addAuthentication(response, auth.getName(),this.secretkey);
	}
}

