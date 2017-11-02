package com.spring.SampleAuth2App.oauth2;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

/**
 * @author a666395
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
	// Apache logger
	private static final Logger logger = Logger.getLogger(MethodSecurityConfig.class);

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {

		logger.info("In Method Security Handler");
		return new OAuth2MethodSecurityExpressionHandler();
	}
}
