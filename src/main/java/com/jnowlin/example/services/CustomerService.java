package com.jnowlin.example.services;

import com.jnowlin.example.domain.Customer;
import com.jnowlin.example.repositories.CustomerRepository;
import java.sql.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Long addCustomer(String firstName, String lastName, Date dateOfBirth) {
        return customerRepository.add(firstName, lastName, dateOfBirth);
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findCustomer(long id) {
        return customerRepository.findById(id);
    }

    public void removeCustomer(Long id) {
        customerRepository.del(id);
    }
}
