package com.jnowlin.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "dbo.Customers")
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "add",
        procedureName = "dbo.AddCustomer",
        parameters = {
            @StoredProcedureParameter(
                name = "id",
                mode = ParameterMode.OUT,
                type = Long.class
            ),
            @StoredProcedureParameter(
                name = "firstName",
                mode = ParameterMode.IN,
                type = String.class
            ),
            @StoredProcedureParameter(
                name = "lastName",
                mode = ParameterMode.IN,
                type = String.class
            )
        }
    ),
    @NamedStoredProcedureQuery(
        name = "del",
        procedureName = "dbo.RemoveCustomer",
        parameters = {
            @StoredProcedureParameter(
                name = "id",
                mode = ParameterMode.IN,
                type = Long.class
            )
        }
    )
})
public class Customer {

    @Id
    @Column(name = "Id")
    private Long id;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
