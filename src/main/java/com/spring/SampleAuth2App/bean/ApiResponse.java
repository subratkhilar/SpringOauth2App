package com.spring.SampleAuth2App.bean;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

/**
 * 
 * This class is for the Api Response
 */
/**
 * @author A669825
 *
 */
public class ApiResponse {
	private static final Logger logger = Logger.getLogger(ApiResponse.class);

	private Object data;
	private String message;
	private int errorCode;

	public ApiResponse() {
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param msg
	 * @param obj
	 * @return ResponseDto
	 */
	public static ApiResponse successResponse(String msg, Object obj) {
		ApiResponse sucess = new ApiResponse();
		sucess.setMessage(msg);
		sucess.setData(obj);
		sucess.setErrorCode(HttpStatus.OK.value());
		return sucess;
	}

	/**
	 * @param msg
	 * @param obj
	 * @return ResponseDto
	 */
	public static ApiResponse failureResponse(String msg, Object obj, int errorCode) {
		ApiResponse failure = new ApiResponse();
		failure.setMessage(msg);
		failure.setData(obj);
		failure.setErrorCode(errorCode);
		return failure;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
