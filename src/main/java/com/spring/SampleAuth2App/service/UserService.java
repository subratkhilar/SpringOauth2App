package com.spring.SampleAuth2App.service;


import org.springframework.stereotype.Service;

import com.spring.SampleAuth2App.model.User;





@Service("userService")
public class UserService{
	public User getUserByuserId(long userId){
		
		User user = new User();
		user.setFirstname("test");
		user.setLastname("K");
		user.setPass("disha123");
		user.setUserId(userId);
		user.setUser("disha@atos.net");
		return user;
	}
	
	
}