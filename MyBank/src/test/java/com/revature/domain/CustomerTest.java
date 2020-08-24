package com.revature.domain;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;


import static org.junit.Assert.*;

/**
 * To Test the customer link to the account and do all the function
 */

public class CustomerTest {


    private Customer mockCustomer = mock(Customer.class);
    private Account mockAccount = mock(Account.class);
    private Customer customer;


    @Before
    public void setUp() throws Exception {

        mockCustomer = new Customer("eden","Gu","eden11","1114");



    }

    @After
    public void tearDown() throws Exception {
        customer=null;
        mockCustomer =null;
    }


    @Test
    public void setAccount() {
        customer.setAccount(new Account(0));
        assertNotNull(customer.getAccount().deposit(1000));
        assertTrue(customer.getAccount().deposit(1000));
    }

    @Test
    public void getAccountNo() {
        mockCustomer.setAccountNo("1000001");
       assertEquals("1000001",mockCustomer.getAccountNo());
    }


    @Test
    public void testSetAccount() {
        mockCustomer.setAccount(new Account("100000",100));
       assertEquals (100,100,mockCustomer.getAccount().getBalance());
    }


    @Test
    public void setRole() {
        mockCustomer.setRole(Role.ADMIN);
        assertEquals("ADMIN",mockCustomer.getRole().name());
    }
}