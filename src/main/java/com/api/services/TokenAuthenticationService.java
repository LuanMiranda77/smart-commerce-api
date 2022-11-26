package com.api.services;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
public class TokenAuthenticationService {
	
	// EXPIRATION_TIME = 1 hora de 
	    static final long HORA=24;
		static final long EXPIRATION_TIME = HORA*3600*1000;
		static final String SECRET = "8c5b8dd9-ef6f-4e67-8480-6d4423ff52f7";
		static final String TOKEN_PREFIX = "Bearer";
		static final String HEADER_STRING = "Authorization";
		
		static void addAuthentication(HttpServletResponse response, String username) {
			String JWT = Jwts.builder()
					.setSubject(username)
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, SECRET)
					.compact();
			
			response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
			try {
				response.getWriter().write(TOKEN_PREFIX + " " + JWT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				response.setStatus(403);
				e.printStackTrace();
			}
		}
		
		static Authentication 	getAuthentication(HttpServletRequest request, HttpServletResponse response) {
			String token = request.getHeader(HEADER_STRING);
			
			if (token != null) {
				// faz parse do token
				String user = null;
				try {
					user = Jwts.parser()
						.setSigningKey(SECRET)
						.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
						.getBody()
						.getSubject();
				}catch (ExpiredJwtException e) {
					System.err.println("Token expirado "+e.getMessage());
    				response.setStatus(403);
    			}
				
				if (user != null) {
					return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
				}
			}
			return null;
		}

}
