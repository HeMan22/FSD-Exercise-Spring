package com.practice.mycontactapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {


	@JsonProperty(value="contact_ID")
	private String contactID;
	private String name;
	private String email;
	private String category;

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(String contactID, String name, String email, String category, String phone, String imageUrl) {
		super();
		this.contactID = contactID;
		this.name = name;
		this.email = email;
		this.category = category;
		this.phone = phone;
		this.imageUrl = imageUrl;
	}

	public String getContactID() {
		return contactID;
	}

	public void setContactID(String contactID) {
		this.contactID = contactID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	private String phone;
	private String imageUrl;
}
