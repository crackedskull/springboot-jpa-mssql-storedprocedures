package com.jnowlin.example.services;

import com.jnowlin.example.domain.Customer;
import com.jnowlin.example.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findCustomer(int id) {
        return customerRepository.proc(id);
    }
}
