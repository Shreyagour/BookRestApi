package com.exercise.book_rest_service.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class MySpringSecurityConfiguration {
	@Bean
	public SecurityFilterChain mySecurityFilterChain(HttpSecurity myhttp) throws Exception {
		//all reqs authenticated
				myhttp.authorizeHttpRequests(
						auth->auth.anyRequest().authenticated());
				//applying basic authentication popup
				myhttp.httpBasic(withDefaults());
				myhttp.csrf().disable();
				return myhttp.build();
	}
}
