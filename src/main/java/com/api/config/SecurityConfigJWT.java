package com.api.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.services.JWTAuthenticationFilter;
import com.api.services.JWTLoginFilter;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class SecurityConfigJWT extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
//			.antMatchers(HttpMethod.GET,"api/home").permitAll()
//			.antMatchers(HttpMethod.POST,"api/cliente").permitAll()
			.antMatchers(HttpMethod.POST, "/token").permitAll()
			.anyRequest().authenticated()
			.and().cors()
			.and().csrf().disable()
			
			// filtra requisições de login
			.addFilterBefore(new JWTLoginFilter("/token", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
			// filtra requisições de login
//			.addFilterBefore(new JWTLoginFilter("api/cliente", authenticationManager()),
//				                UsernamePasswordAuthenticationFilter.class)
//			
			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// cria uma conta default
		
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("{noop}7sir5Q@BVUXVU@qo")
			.roles("ROEL_ADMIN");
		}

}
