package com.jnowlin.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @Column(name = "customerownerid")
    private int customerOwnerId;
    @Column(name = "atozcustomerid")
    private int atozCustomerId;

    public int getCustomerOwnerId() {
        return customerOwnerId;
    }

    public void setCustomerOwnerId(int customerOwnerId) {
        this.customerOwnerId = customerOwnerId;
    }

    public int getAtozCustomerId() {
        return atozCustomerId;
    }

    public void setAtozCustomerId(int atozCustomerId) {
        this.atozCustomerId = atozCustomerId;
    }
}
