package com.revature.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

   Account account;

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {

        Account account = null;

    }

    @Test
    public void getBalance() {
        assertEquals(500.00,500.00,account.getBalance());

    }

    @Test
    public void setBalance() {
        assertEquals(1200.00,1200,account.getBalance());
    }

    @Test
    public void deposit() {
        assertTrue("adding 300",account.deposit(300));
        assertEquals(1500.00,1500,account.getBalance());
    }

    @Test
    public void withdraw() throws OverdraftException {
       assertTrue( "add 500",account.withdraw(500));
       assertEquals(1000.00,1000,account.getBalance());
    }

    @Test
    public void overdraft() throws OverdraftException {
        try {
            account.withdraw(2000);
        }catch (OverdraftException e){
            System.out.println(" Insufficient funds. overdraft protection! ");
        }


    }
}