package com.practice.mycontactapi.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.practice.mycontactapi.model.UserRole;

@Repository
public class UserRoleRepositoryImpl implements UserRoleRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public UserRole addRole(UserRole role) {

		manager.persist(role);
		return role;
	}

	@Override
	public UserRole getRole(String roleName) {
		return null;
	}

	@Override
	public UserRole getRole(Integer roleId) {
		return manager.find(UserRole.class, roleId);
	}

}
