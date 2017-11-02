/**
 * 
 */
package com.spring.SampleAuth2App.bean;

import javax.validation.groups.Default;

import org.apache.log4j.Logger;

/**
 * @author A669825
 *
 */
public class UserLogin {
	// Apache logger
	private static final Logger logger = Logger.getLogger(UserLogin.class);

	/**
	 * {@link String} username
	 */
	private String username;
	/**
	 * {@link String} Password
	 */
	private String password;

	/**
	 * {@link Default Constructor}
	 */
	public UserLogin() {
		logger.info("inside UserLogin");
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
