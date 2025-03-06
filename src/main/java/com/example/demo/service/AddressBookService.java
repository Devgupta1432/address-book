package com.example.demo.service;

import com.example.demo.dto.AddressBookDTO;
import com.example.demo.model.AddressBookEntry;
import com.example.demo.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    public List<AddressBookDTO> getAllContacts() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<AddressBookDTO> getContactById(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    public AddressBookDTO saveContact(AddressBookDTO dto) {
        AddressBookEntry entry = convertToEntity(dto);
        AddressBookEntry savedEntry = repository.save(entry);
        return convertToDTO(savedEntry);
    }

    public void deleteContact(Long id) {
        repository.deleteById(id);
    }

    private AddressBookDTO convertToDTO(AddressBookEntry entry) {
        return new AddressBookDTO(entry.getName(), entry.getPhone(), entry.getEmail(), entry.getAddress());
    }

    private AddressBookEntry convertToEntity(AddressBookDTO dto) {
        AddressBookEntry entry = new AddressBookEntry();
        entry.setName(dto.getName());
        entry.setPhone(dto.getPhone());
        entry.setEmail(dto.getEmail());
        entry.setAddress(dto.getAddress());
        return entry;
    }
}
