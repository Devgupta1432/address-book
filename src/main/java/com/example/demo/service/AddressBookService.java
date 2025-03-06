/*package com.example.demo.service;

import com.example.demo.model.AddressBookEntry;
import com.example.demo.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    public List<AddressBookEntry> getAllContacts() {
        return repository.findAll();
    }

    public Optional<AddressBookEntry> getContactById(Long id) {
        return repository.findById(id);
    }

    public AddressBookEntry saveContact(AddressBookEntry entry) {
        return repository.save(entry);
    }

    public AddressBookEntry updateContact(Long id, AddressBookEntry contactDetails) {
        AddressBookEntry contact = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id " + id));

        contact.setName(contactDetails.getName());
        contact.setPhone(contactDetails.getPhone());
        contact.setEmail(contactDetails.getEmail());
        contact.setAddress(contactDetails.getAddress());

        return repository.save(contact);
    }

    public boolean deleteContact(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
*/
package com.example.demo.service;

import com.example.demo.model.AddressBookEntry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AddressBookService {

    private final List<AddressBookEntry> addressBook = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Generates unique IDs

    public List<AddressBookEntry> getAllContacts() {
        return new ArrayList<>(addressBook); // Return a copy to avoid modification
    }

    public Optional<AddressBookEntry> getContactById(Long id) {
        return addressBook.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst();
    }

    public AddressBookEntry saveContact(AddressBookEntry entry) {
        entry.setId(idCounter.getAndIncrement()); // Assign unique ID
        addressBook.add(entry);
        return entry;
    }

    public AddressBookEntry updateContact(Long id, AddressBookEntry contactDetails) {
        for (int i = 0; i < addressBook.size(); i++) {
            if (addressBook.get(i).getId().equals(id)) {
                AddressBookEntry updatedContact = new AddressBookEntry();
                updatedContact.setId(id);
                updatedContact.setName(contactDetails.getName());
                updatedContact.setPhone(contactDetails.getPhone());
                updatedContact.setEmail(contactDetails.getEmail());
                updatedContact.setAddress(contactDetails.getAddress());

                addressBook.set(i, updatedContact);
                return updatedContact;
            }
        }
        throw new RuntimeException("Contact not found with id " + id);
    }

    public boolean deleteContact(Long id) {
        return addressBook.removeIf(contact -> contact.getId().equals(id));
    }
}
