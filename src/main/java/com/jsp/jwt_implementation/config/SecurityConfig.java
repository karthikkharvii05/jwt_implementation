package com.jsp.jwt_implementation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jsp.jwt_implementation.service.JwtFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	JwtFilter  filter;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http
			.csrf(csrf->csrf.disable())
			.authorizeHttpRequests(
					auth->auth
					.requestMatchers("/save","/verify")
					.permitAll()
					.requestMatchers("/admin").hasRole("ADMIN")
					.requestMatchers("/user").hasAnyRole("ADMIN","USER")
					.anyRequest()
					.authenticated());
		http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
		return configuration.getAuthenticationManager();
	}
	

}
