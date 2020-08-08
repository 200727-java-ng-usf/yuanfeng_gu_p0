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
        customer = new Customer("Eden","Gu"); // set Account name

    }

    @After
    public void tearDown() throws Exception {
        customer=null;
    }

    @Test
    public void setAccount() {

        assertTrue(customer.getAccount().deposit(500)); // and deposit 500
        try {
            assertTrue(customer.getAccount().withdraw(1000));  // take 1000 off
            System.out.println(customer.getAccount().getBalance()); // print result to check
        } catch (OverdraftException e) {
            e.printStackTrace();
        }

    }
}