package com.spring.SampleAuth2App.controllers;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.spring.SampleAuth2App.bean.AuthTokenInfo;
import com.spring.SampleAuth2App.bean.UserLogin;
import com.spring.SampleAuth2App.bean.UserModel;
import com.spring.SampleAuth2App.model.MessageResponse;
import com.spring.SampleAuth2App.model.User;
import com.spring.SampleAuth2App.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/login", method = RequestMethod.POST)
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private Environment env;

	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Validate User Credentials", notes = "", response = UserModel.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
			@ApiResponse(code = 404, message = "un-authorized user") })
	public ResponseEntity getValidatedUser(
			@ApiParam(value = "User credentials to be authenticated", required = true) @RequestBody UserLogin userLogin) {
		MessageResponse messageResponse = new MessageResponse();
		logger.info(userLogin.getUsername());
		logger.info(userLogin.getPassword());
		User user = new User();
		logger.info("inside login user");
		user.setUser(userLogin.getUsername());
		user.setPass(userLogin.getPassword());
		logger.info(user.getUser());
		logger.info(user.getPass());
		logger.info(env.getProperty("AUTH_SERVER_URI"));
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		header.set("Authorization", "Basic bW9iaWxlLWFwcDpzZWNyZXQ=");
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		HttpEntity entity = new HttpEntity(header);
		ResponseEntity<Object> response = null;
		logger.info("http header");
		try {
			response = restTemplate.exchange(
					env.getProperty("AUTH_SERVER_URI") + "&username=" + user.getUser() + "&password=" + user.getPass(),
					HttpMethod.POST, entity, Object.class);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Invalid User and pass");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"un-authorized user.\"}");
		}
		logger.info(response);
		logger.info("token");
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
		AuthTokenInfo tokenInfo;
		if (map != null) {
			tokenInfo = new AuthTokenInfo();
			tokenInfo.setAccessToken((String) map.get("access_token"));
			tokenInfo.setTokenType((String) map.get("token_type"));
			tokenInfo.setExpiresIn((int) map.get("expires_in"));
			tokenInfo.setScope((String) map.get("scope"));
			logger.info(tokenInfo);
		} else {
			logger.info("No user exist----------");

			logger.info("No user exist----------");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"un-authorized user.\"}");

		}
		logger.info("After http header");

		User validUser = userService.getUserByuserId(123L);
		if (validUser == null) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"un-authorized user.\"}");

		}
		UserModel validUserModel = new UserModel();
		validUserModel.setFirstName(validUser.getFirstname());
		validUserModel.setLastName(validUser.getLastname());
		validUserModel.setUserid(validUser.getUserId());
		validUserModel.setUserName(validUser.getUser());
		validUserModel.setAuthTokenInfo(tokenInfo);
		logger.info("User Name" + validUser.getFirstname());

		
		messageResponse.setCode("200");
		messageResponse.setMessage("Test");
		

		return ResponseEntity.status(200).body(validUserModel);
		
	}

}
