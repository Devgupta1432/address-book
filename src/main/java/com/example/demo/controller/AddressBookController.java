package com.example.demo.controller;

import com.example.demo.model.AddressBookEntry;
import com.example.demo.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @GetMapping
    public List<AddressBookEntry> getAllContacts() {
        return addressBookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<AddressBookEntry> getContactById(@PathVariable Long id) {
        return addressBookRepository.findById(id);
    }

    @PostMapping
    public AddressBookEntry createContact(@RequestBody AddressBookEntry contact) {
        return addressBookRepository.save(contact);
    }

    @PutMapping("/{id}")
    public AddressBookEntry updateContact(@PathVariable Long id, @RequestBody AddressBookEntry contactDetails) {
        AddressBookEntry contact = addressBookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id " + id));

        contact.setName(contactDetails.getName());
        contact.setPhone(contactDetails.getPhone());
        contact.setEmail(contactDetails.getEmail());
        contact.setAddress(contactDetails.getAddress());

        return addressBookRepository.save(contact);
    }

    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable Long id) {
        addressBookRepository.deleteById(id);
        return "Contact deleted successfully with id " + id;
    }
}
