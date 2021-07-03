package com.practice.mycontactapi.service;

import java.util.List;

import com.practice.mycontactapi.exception.ContactExistsException;
import com.practice.mycontactapi.exception.ContactNotFoundException;
import com.practice.mycontactapi.model.Contact;

public interface ContactService {
	
	List<Contact> getAllContacts();
	
	Contact getContactById(String contactId) throws ContactNotFoundException;

	Contact addContact(Contact newContact) throws ContactExistsException;

	void deleteContact(String contactId);

	List<Contact> getAllContactsByCategory(String category);

}
