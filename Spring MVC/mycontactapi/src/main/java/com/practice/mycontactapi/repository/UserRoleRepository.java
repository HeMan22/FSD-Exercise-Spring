package com.practice.mycontactapi.repository;

import com.practice.mycontactapi.model.UserRole;

public interface UserRoleRepository {
	
	public UserRole addRole(UserRole role);
	
	public UserRole getRole(String roleName);
	public UserRole getRole(Integer roleId);

}
