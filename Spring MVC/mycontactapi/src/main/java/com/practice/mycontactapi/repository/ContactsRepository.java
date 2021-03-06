package com.practice.mycontactapi.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.practice.mycontactapi.model.Contact;

@Repository
public class ContactsRepository {

	public static Map<String, Contact> CONTACTS_REPO = new HashMap<>();

	static {
		CONTACTS_REPO.put("1", new Contact("1", "John", "john@allstate.com", "Friend", "789456", "url"));
		CONTACTS_REPO.put("2", new Contact("2", "Mike", "mike@allstate.com", "Friend", "123465", "url"));
	}

	public Map<String,Contact> getContactsRepo() {
		return CONTACTS_REPO;
	}
}
