package com.spring.SampleAuth2App.oauth2;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author A669825
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = Logger.getLogger(SecurityConfig.class); // Apache
																					// logger

	/**
	 * SecurityConfig default constructor
	 */
	public SecurityConfig() {
		logger.info("In Oauth2 Websecurity Configure");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("Inside configure");
	}
}