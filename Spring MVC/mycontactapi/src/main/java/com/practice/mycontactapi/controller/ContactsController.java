package com.practice.mycontactapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.mycontactapi.exception.ContactNotFoundException;
import com.practice.mycontactapi.model.Contact;
import com.practice.mycontactapi.service.ContactService;

@RestController
@RequestMapping("/api/v1/")
public class ContactsController {

	private ContactService service;

	@Autowired
	public ContactsController(ContactService service) {
		super();
		this.service = service;
	}

	@GetMapping("/contacts-info")
	public String apiInfo() {
		return "Contacts API is up and running";
	}

	@GetMapping(path = "/contacts", produces = "application/json") // produces is optional to write
	public List<Contact> getContacts() {
		return service.getAllContacts();
	}

	// public @ResponseBody List<Contact> getContacts(){ When using @Controller
	// annotation in place of @RestController

	@GetMapping("/contacts/{contact_Id}")
	public Contact getContact(@PathVariable("contact_Id") String contactId) throws ContactNotFoundException {
		return service.getContactById(contactId);
	}

	@GetMapping(path = "/contacts", params = { "category", "test" })
	public List<Contact> getContactByCategory(@RequestParam String category, @RequestParam String test) {
		System.out.println(test);
		return service.getAllContactsByCategory(category);
	}

	@PostMapping("/contacts")
	public ResponseEntity<Contact> addContact(@RequestBody Contact newContact) {

		return new ResponseEntity(service.addContact(newContact), HttpStatus.CREATED);
	}

	@DeleteMapping("/contacts/{contactId}")
	public ResponseEntity<?> deleteContact(@PathVariable String contactId) {
		service.deleteContact(contactId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);

	}

}
