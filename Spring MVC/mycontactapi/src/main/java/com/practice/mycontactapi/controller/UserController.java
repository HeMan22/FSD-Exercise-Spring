package com.practice.mycontactapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.mycontactapi.model.User;
import com.practice.mycontactapi.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService service;

	
	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
		
		User registeredUser = service.registerUser(user);
		return String.join(":", "User Registered ", registeredUser.getUserId().toString());
		
	}
	
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody User user) {
		 return service.authenticate(user);
	}
	
	
}

