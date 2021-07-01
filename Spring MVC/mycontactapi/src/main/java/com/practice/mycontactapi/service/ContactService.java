package com.practice.mycontactapi.service;

import java.util.List;

import com.practice.mycontactapi.exception.ContactExistsException;
import com.practice.mycontactapi.exception.ContactNotFoundException;
import com.practice.mycontactapi.model.Contact;

public interface ContactService {
	public List<Contact> getAllContacts();
	
	Contact getContactById(String contactId) throws ContactNotFoundException;
	
	Contact addContact(Contact addContact) throws ContactExistsException;

	public void deleteContact(String contactId);

	public List<Contact> getAllContactsByCategory(String Category);
}
