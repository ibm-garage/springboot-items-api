package com.github.michaelsteven.archetype.springboot.items.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class SecurityConfig {

	/**
	 * Authorizes all requests for now
	 * TODO:  implement your desired security
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/**").permitAll()
                            .anyRequest().authenticated()
            )
  	      	.csrf(AbstractHttpConfigurer::disable);
		return http.build();
	}
}
