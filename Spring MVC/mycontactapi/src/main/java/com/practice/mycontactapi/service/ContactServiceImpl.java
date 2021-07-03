package com.practice.mycontactapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.mycontactapi.exception.ContactNotFoundException;
import com.practice.mycontactapi.exception.ContactExistsException;
import com.practice.mycontactapi.model.Contact;
import com.practice.mycontactapi.repository.ContactsRepository;

@Service
public class ContactServiceImpl implements ContactService{
	
	private ContactsRepository repository;
	
	
	@Autowired
	public ContactServiceImpl(ContactsRepository repository) {
		super();
		this.repository = repository;
	}



	@Override
	public List<Contact> getAllContacts() {
		return new ArrayList<Contact>(repository.getContactsRepo().values());
	}


	@Override
	public Contact getContactById(String contactId) throws ContactNotFoundException {
		Contact contact = repository.getContactsRepo().get(contactId);
		if(contact == null) {
			throw new ContactNotFoundException("Contact not found");
		}
		return contact;
	}


	@Override
	public Contact addContact(Contact newContact) throws ContactExistsException{
		newContact.setContactID(UUID.randomUUID().toString());
		
		boolean contactExists = repository.getContactsRepo()
		.values().stream()
		.anyMatch(contact -> contact.getEmail().equals(newContact.getEmail()));
		
		if(contactExists) {
			throw new ContactExistsException("Contact with Email already exists : " + newContact.getEmail());
		}
		
		
		repository.getContactsRepo().put(newContact.getContactID(), newContact);
		return repository.getContactsRepo().get(newContact.getContactID());
	}


	@Override
	public void deleteContact(String contactId) {
		repository.getContactsRepo().remove(contactId);
		
	}


	@Override
	public List<Contact> getAllContactsByCategory(String category) {

		return new ArrayList<Contact>(repository.getContactsRepo().values())
				.stream()
				.filter(contact -> contact.getCategory().equals(category))
				.collect(Collectors.toList());
		
	}

}
