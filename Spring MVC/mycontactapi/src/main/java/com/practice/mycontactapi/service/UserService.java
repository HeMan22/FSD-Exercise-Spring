package com.practice.mycontactapi.service;

import java.util.Map;

import com.practice.mycontactapi.model.User;

public interface UserService {
	
	
	public User registerUser(User user);
	
	public Map<String, String> authenticate(User user);
	

}
