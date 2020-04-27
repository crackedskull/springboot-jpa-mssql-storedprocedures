package com.jnowlin.example.repositories;

import com.jnowlin.example.domain.Customer;
import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Procedure(name = "add")
    Long add(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("dateOfBirth") Date dateOfBirth);

    @Procedure(name = "del")
    void del(@Param("id") Long id);
}
