package com.jnowlin.example.repositories;

import com.jnowlin.example.domain.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query(value = "EXECUTE [dbo].[GetAtoZCustomerId] :customerOwnerId", nativeQuery = true)
    Customer proc(@Param("customerOwnerId") Integer customerOwnerId);
}
