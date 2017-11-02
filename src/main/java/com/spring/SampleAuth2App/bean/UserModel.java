package com.spring.SampleAuth2App.bean;

import org.apache.log4j.Logger;



/**
 * @author A669825
 *
 */
public class UserModel  {
	//Apache logger
	private static final Logger logger = Logger.getLogger(UserModel.class); 
	/**
	 * {@link Long} userid
	 */
	private long userid;
	/**
	 * {@link String} firstName
	 */
	private String firstName;
	/**
	 * {@link String} lastName
	 */
	private String lastName;
	/**
	 * {@link String} userName
	 */
	private String userName;
	
	/**
	 * {@link AuthTokenInfo} authTokenInfo
	 */
	private AuthTokenInfo authTokenInfo;

	/**
	 * {@link DefaultConstructor} UserModel
	 */
	public UserModel() {
		logger.info("Inside UserModel");
	}

	/**
	 * @return {@link Long} userid
	 */
	public long getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 */
	public void setUserid(long userid) {
		this.userid = userid;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return AuthTokenInfo
	 */
	public AuthTokenInfo getAuthTokenInfo() {
		return authTokenInfo;
	}

	/**
	 * @param authTokenInfo
	 */
	public void setAuthTokenInfo(AuthTokenInfo authTokenInfo) {
		this.authTokenInfo = authTokenInfo;
	}



	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserModel [userid=" + userid + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", userName=" + userName
				+ ", devicetoken=" + ", authTokenInfo=" + authTokenInfo + "]";
	}
		
}