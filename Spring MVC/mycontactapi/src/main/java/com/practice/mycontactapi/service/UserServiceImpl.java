package com.practice.mycontactapi.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.practice.mycontactapi.model.User;
import com.practice.mycontactapi.model.UserRole;
import com.practice.mycontactapi.repository.UserRepository;
import com.practice.mycontactapi.repository.UserRoleRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	
	private UserRepository repository;
	private JWTTokenGenerator tokenGenerator;
	private UserRoleRepository roleRepository;
	
	
	@Autowired
	public UserServiceImpl(UserRepository repository, JWTTokenGenerator tokenGenerator,
			UserRoleRepository roleRepository) {
		super();
		this.repository = repository;
		this.tokenGenerator = tokenGenerator;
		this.roleRepository = roleRepository;
	}

	@Override
	public User registerUser(User user) {
		
		Long count = repository.checkUserByEmail(user.getEmail());
		if(count != 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "User with Email already exists");
		}
		
		UserRole userRole = roleRepository.getRole(1);
		user.setUserRole(userRole);

		return repository.addUser(user);
	}



	@Override
	public Map<String, String> authenticate(User user) {
		User dbUser = null;
			
		if(user.getEmail() == null) {
			
			dbUser = repository.getUser(user.getUserId());
		}else {
			dbUser = repository.getUser(user.getEmail());
		}
		
		System.out.println(dbUser);
		
		//check the creds and generate the token
		if(user.getPassword().equals(dbUser.getPassword())) {
			return Map.of("token",tokenGenerator.generateToken(dbUser.getEmail()));
		}else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}

}
