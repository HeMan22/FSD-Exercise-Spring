package com.practice.mycontactapi.repository;

import com.practice.mycontactapi.model.User;

public interface UserRepository {

	public User addUser(User user);

	public Long checkUserByEmail(String email);

	public User getUser(Integer id);

	public User getUser(String email);

}
