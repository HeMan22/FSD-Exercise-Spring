package com.practice.mycontactapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REGD_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	private String password;
	
	
	@ManyToOne(fetch = FetchType.EAGER)   // fetch tpe is default and can be avoided. only for ref
	@JoinColumn(name = "USER_ROLE_ID")
	private UserRole userRole;
	
	
	public User() {}
	
	public User(Integer userId, String email, String password) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
	}

	public User(Integer userId, String email, String password, UserRole userRole) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", userRole=" + userRole
				+ "]";
	}
	
	
	
}