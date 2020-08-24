package com.revature.repository;

import com.revature.domain.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;

public class CustomerRepoTest {

    private CustomerRepo mockCustomerRepo = Mockito.mock(CustomerRepo.class);
    private CustomerRepo customerRepo;
    Customer customer;



    @Before
    public void setUp() throws Exception {
        customerRepo = new CustomerRepo();

    }

    @After
    public void tearDown() throws Exception {
        customer = null;
    }

    @Test
    public void findUserByCredentials() {

        assertTrue(customerRepo.findUserByCredentials("gu8", "8888").isPresent());
        assertEquals("Gu",customerRepo.findUserByCredentials("gu8", "8888").get().getLastName());


    }

    @Test
    public void addCustomer() {

             assertFalse(mockCustomerRepo.addCustomer(customer));
    }
    @Test
    public void updateAccount() {

        mockCustomerRepo.UpdateAccount("1000878",4000.00);
    }

    @Test
    public void addCustomerAccount() {

        mockCustomerRepo.addCustomerAccount(customer);

    }

    @Test
    public void findUserByUsername() {

    assertTrue(customerRepo.findUserByUsername("gu8").isPresent());
    assertEquals("Gu",customerRepo.findUserByUsername("gu8").get().getLastName());

    }

    @Test
    public void findUserByAccountNo() {
        assertTrue(customerRepo.findAccountByAccountNo(1000697).isPresent());
        assertEquals("Gu",customerRepo.findUserByAccountNo(100005).get().getLastName());


    }

    @Test
    public void findAccountByAccountNo() {
        assertTrue(customerRepo.findAccountByAccountNo(100005).isPresent());
        assertEquals("100005",customerRepo.findAccountByAccountNo(100005).get().getAccountNo());
    }
}