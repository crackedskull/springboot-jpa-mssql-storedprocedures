package com.jnowlin.example.controllers;

import com.jnowlin.example.domain.Customer;
import com.jnowlin.example.services.CustomerService;
import java.sql.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Long create(@RequestParam String firstName, @RequestParam String lastName, @RequestParam Date dateOfBirth) {
        return customerService.addCustomer(firstName, lastName, dateOfBirth);
    }

    @GetMapping
    public Iterable<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> findOne(@PathVariable Long id) {
        return customerService.findCustomer(id);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable Long id) {
        customerService.removeCustomer(id);
    }
}
