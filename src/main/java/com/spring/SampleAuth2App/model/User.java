package com.spring.SampleAuth2App.model;

import javax.persistence.*;

import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * @author a632972 The persistent class for the USERS database table.
 *
 */
@Entity
@Table(name = "USERS")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	/**
	 * serialVersionUID = 1L
	 */
	private static final long serialVersionUID = 1L;
	// Apache logger
	private static final Logger logger = Logger.getLogger(User.class);
	/**
	 * * {@link long} userId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private long userId;
	/**
	 * * {@link String} firstname
	 */
	private String firstname;
	/**
	 * * {@link String} lastname
	 */
	private String lastname;
	/**
	 * * {@link String} pass
	 */
	@Column(name = "password")
	private String pass;
	/**
	 * * {@link String} username
	 */
	@Column(name = "username")
	private String username;

	/**
	 * User default constructor
	 */
	public User() {
		logger.info("Inside User");
	}

	/**
	 * @return userId
	 */
	public long getUserId() {
		return this.userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return firstname
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return lastname
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return username;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String username) {
		this.username = username;
	}

}