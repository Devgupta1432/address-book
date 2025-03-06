package com.example.demo.controller;

import com.example.demo.model.AddressBookEntry;
import com.example.demo.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping
    public ResponseEntity<List<AddressBookEntry>> getAllContacts() {
        List<AddressBookEntry> contacts = addressBookService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookEntry> getContactById(@PathVariable Long id) {
        Optional<AddressBookEntry> contact = addressBookService.getContactById(id);
        return contact.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AddressBookEntry> createContact(@RequestBody AddressBookEntry contact) {
        AddressBookEntry savedContact = addressBookService.saveContact(contact);
        return ResponseEntity.ok(savedContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBookEntry> updateContact(@PathVariable Long id, @RequestBody AddressBookEntry contactDetails) {
        AddressBookEntry updatedContact = addressBookService.updateContact(id, contactDetails);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        boolean deleted = addressBookService.deleteContact(id);
        if (deleted) {
            return ResponseEntity.ok("Contact deleted successfully with id " + id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
