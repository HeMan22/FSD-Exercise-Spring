package com.practice.mycontactapi.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.practice.mycontactapi.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public User addUser(User user) {
		manager.persist(user); // find remove merge
		return user;
	}

	@Override
	public Long checkUserByEmail(String email) {
		return (Long) manager.createQuery("SELECT COUNT(u) from User u where u.email=?1").setParameter(1, email)
				.getSingleResult();

		// getResultList
	}

	@Override
	public User getUser(Integer id) {
		return manager.find(User.class, id);
	}

	@Override
	public User getUser(String email) {
		return manager.createQuery("SELECT u from User u where u.email=?1", User.class)
				.setParameter(1, email)
				.getSingleResult();
	}

}
