package com.favorite.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
//
public class FavoriteJWTFilter extends GenericFilterBean {


	
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//    	HttpServletRequest httprequest=(HttpServletRequest) request;
//		HttpServletResponse httpresponse=(HttpServletResponse) response;
//		httpresponse.setHeader("Access-Control-Allow-Headers", "Authorization,Content-Type, Content-Range, Content-Disposition, Content-Description,Origin, X-Requested-With, sessionId");
////		httpresponse.setHeader("Access-Control-Allow-Origin", "*");
//
//
//		
//		httpresponse.setHeader("Access-Control-Allow-Origin", httprequest.getHeader("origin"));
//		httpresponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");;
//		httpresponse.setHeader("Access-Control-Allow-Credential", "true");
//		
//		
//		
//		
//		String authHeader=httprequest.getHeader("Authorization");
//		
//		if(authHeader==null || (!authHeader.startsWith("Bearer")))
//			throw  new ServletException("JWT token is missing in authorization");
//		
//		else{
//		String token=authHeader.substring(7);
//			try{
//				JwtParser myparser=Jwts.parser().setSigningKey("ibmkey");
//				Jwt jwtobj=myparser.parse(token);
//				Claims claims=(Claims)jwtobj.getBody();
//				String user=claims.getSubject();
//				HttpSession session=httprequest.getSession();
//				session.setAttribute("username", user);
//			}
//			catch(SignatureException excep){
//				throw new ServletException("signature mismatch/ token expired");
//			}
//			catch(MalformedJwtException excep){
//				throw new ServletException("modified token. illegal operation");
//			}
//		}
//		chain.doFilter(httprequest, httpresponse);
//		}
    
	
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	   	HttpServletRequest httprequest=(HttpServletRequest) request;
			HttpServletResponse httpresponse=(HttpServletResponse) response;
			
			
			httpresponse.setHeader("Access-Control-Allow-Origin", httprequest.getHeader("origin"));
			httpresponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");;
			httpresponse.setHeader("Access-Control-Allow-Credential", "true");
			httpresponse.setHeader("Access-Control-Allow-Headers","*");
			
			
			if (httprequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name()))
			{
				chain.doFilter(httprequest, httpresponse);
			}
			else {
			
			String authheader=httprequest.getHeader("Authorization");
			
			System.out.println(authheader);
			
			if(authheader==null || (!authheader.startsWith("Bearer")))
				throw  new ServletException("JWT token is missing in authorization");
		
			String token=authheader.substring(7);
			
			try {
				JwtParser myparser = Jwts.parser().setSigningKey("ibmkey");
				Jwt jwtobj = myparser.parse(token);
				
				//Claims claims = (Claims) jwtobj.getBody();
				
				//String user = claims.getSubject();
						
			}
			
			catch(Exception excep) {
				throw new ServletException("Altered token");
			}
			
		
			chain.doFilter(httprequest, httpresponse);
			}
	    }
	
	
}