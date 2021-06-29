package com.practice.mycontactapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.mycontactapi.exception.ContactNotFoundException;
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
	public Contact getContactById(String contactId) throws ContactNotFoundException {
		Contact contact = repository.CONTACTS_REPO.get(contactId);
		if(contact==null) {
			throw new ContactNotFoundException("Contact Not Found");
		}
		
		return contact;
	}

	@Override
	public Contact addContact(Contact newContact) {
		newContact.setContactID(UUID.randomUUID().toString());
		repository.CONTACTS_REPO.put(newContact.getContactID(), newContact);
		return repository.CONTACTS_REPO.get(newContact.getContactID());
	}

	@Override
	public void deleteContact(String contactId) {
		repository.CONTACTS_REPO.remove(contactId);
	}

	@Override
	public List<Contact> getAllContactsByCategory(String category) {
		return new ArrayList<Contact>(repository.CONTACTS_REPO.values()).stream()
				.filter(contact -> contact.getCategory().equals(category)).collect(Collectors.toList());

	}

}
