package com.example.demo.service;

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

    public void deleteContact(Long id) {
        repository.deleteById(id);
    }
}
