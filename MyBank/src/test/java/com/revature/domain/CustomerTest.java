package com.revature.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To Test the customer link to the account and do all the function
 */

public class CustomerTest {
    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("effie","Gu","aaaa","bbbb");


    }

    @After
    public void tearDown() throws Exception {
        customer=null;
    }

    @Test
    public void setAccount() {
        customer.setAccount(new Account(0));
        System.out.println(customer.getAccount().deposit(1000));


    }
}