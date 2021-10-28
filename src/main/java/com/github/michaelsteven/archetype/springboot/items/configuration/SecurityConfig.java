package com.github.michaelsteven.archetype.springboot.items.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	/**
	 * Sets the CSRF Token set-cookie header on GET requests.
	 * Enables XSS protection, and sets the contentSecurityPolicy
	 * to self and the bootstrap CDN used by the Swagger-UI.
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf()
        	.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        	.and()
        	.headers()
        	.xssProtection()
        	.and()
        	.contentSecurityPolicy("default-src 'self';img-src data: https:;object-src 'none'; script-src https://stackpath.bootstrapcdn.com/ 'self' 'unsafe-inline';style-src https://stackpath.bootstrapcdn.com/ 'self' 'unsafe-inline'; upgrade-insecure-requests;");
    }
}
