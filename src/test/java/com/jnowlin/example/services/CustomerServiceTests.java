package com.jnowlin.example.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jnowlin.example.repositories.CustomerRepository;
import java.sql.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CustomerService.class})
public class CustomerServiceTests {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void itShouldAddNewCustomer() {
        String firstName = "test";
        String lastName = "user";
        Date dateOfBirth = Date.valueOf("2020-04-20");
        when(customerRepository.add(firstName, lastName, dateOfBirth)).thenReturn(1L);

        Long id = customerService.addCustomer(firstName, lastName, dateOfBirth);

        verify(customerRepository, times(1)).add(firstName, lastName, dateOfBirth);
        assertThat(id, is(1L));
    }

    @Test
    public void itShouldRemoveCustomer() {
        Long id = 1L;

        customerService.removeCustomer(id);

        verify(customerRepository, times(1)).del(id);
    }
}
