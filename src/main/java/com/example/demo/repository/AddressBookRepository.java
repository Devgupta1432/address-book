package com.example.demo.repository;

import com.example.demo.model.AddressBookEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepository extends JpaRepository<AddressBookEntry, Long> {
}
