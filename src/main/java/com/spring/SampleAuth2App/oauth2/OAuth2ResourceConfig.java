/**
 */
package com.spring.SampleAuth2App.oauth2;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author A669825
 *
 */
@Configuration
@EnableResourceServer
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {
	// Apache logger
	private static final Logger logger = Logger.getLogger(OAuth2ResourceConfig.class);
	private final TokenExtractor tokenExtractor = new BearerTokenExtractor();

	/**
	 * OAuth2ResourceConfig default constructor
	 */
	public OAuth2ResourceConfig() {
		logger.info("In Oauth2 Resource Configure");
	}

	/**
	 * @return AccessTokenConverter
	 */
	@Bean
	public AccessTokenConverter accessTokenConverter() {
		return new DefaultAccessTokenConverter();
	}

	/**
	 * @return AuthenticationManager
	 */
	@Bean
	public AuthenticationManager authenticationManagerBean() {
		final OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
		authenticationManager.setTokenServices(remoteTokenServices());
		return authenticationManager;

	}

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http.addFilterAfter(new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
					final FilterChain filterChain) throws ServletException, IOException {
				if (tokenExtractor.extract(request) == null) {
					SecurityContextHolder.clearContext();
				}
				filterChain.doFilter(request, response);
			}
		}, AbstractPreAuthenticatedProcessingFilter.class);
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/v2/api-docs", "/swagger-resources/**", "/login/**", "/webjars/**", "/swagger-ui.html")
				.permitAll();
		http.authorizeRequests().anyRequest().authenticated();
	}

	@Override
	public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("mobile-app");
		resources.tokenServices(remoteTokenServices());
	}

	/**
	 * @return RemoteTokenServices
	 */
	@Bean
	public RemoteTokenServices remoteTokenServices() {
		final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
		// remoteTokenServices.setCheckTokenEndpointUrl("https://mi-uaa.apps.eu01.cf.canopy-cloud.com/check_token");

		remoteTokenServices
				.setCheckTokenEndpointUrl("http://localhost:8080/cloudfoundry-identity-uaa-3.9.1/check_token");

		remoteTokenServices.setClientId("mobile-app");
		remoteTokenServices.setClientSecret("secret");
		remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
		return remoteTokenServices;
	}

}