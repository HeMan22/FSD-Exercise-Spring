package com.practice.mycontactapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.mycontactapi.model.Contact;
import com.practice.mycontactapi.repository.ContactsRepository;

@Service
public class ContactServiceImpl implements ContactService {

	private ContactsRepository repository;
	
	@Autowired
	public ContactServiceImpl(ContactsRepository repository) {
		super();
		this.repository = repository;
	}


	@Override
	public List<Contact> getAllContacts() {
		
		return new ArrayList<Contact>(repository.CONTACTS_REPO.values());
	}


	@Override
	public Contact getContactById(String contactId) {
		return repository.CONTACTS_REPO.get(contactId);
	}
	

}
