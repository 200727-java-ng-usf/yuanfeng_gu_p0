package com.revature.domain;

import com.revature.exceptions.OverdraftException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;



public class AccountTest {

   private  Account account;
    private Account mockAccount = mock(Account.class);
    @Before
    public void setUp() throws Exception {
        mockAccount = new Account("100008",500.00);

    }

    @After
    public void tearDown() throws Exception {

        Account account = null;
        mockAccount =null;

    }

    @Test
    public void getBalance() {
        assertEquals(500.00,500.00,mockAccount.getBalance());

    }

    @Test
    public void setBalance() {
        mockAccount.setBalance(1200);
        assertEquals(1200.00,1200,mockAccount.getBalance());
    }

    @Test
    public void deposit() {
        assertTrue("adding 300",mockAccount.deposit(300));
        assertEquals(1500.00,1500,mockAccount.getBalance());
    }

    @Test
    public void withdraw() throws OverdraftException {
       assertTrue( "cut 500",mockAccount.withdraw(500));
       assertEquals(1000.00,1000,mockAccount.getBalance());
    }

    @Test
    public void overdraft() throws OverdraftException {
        try {
            mockAccount.withdraw(2000);
        }catch (OverdraftException e){
            System.out.println(" Insufficient funds. overdraft protection! ");
        }


    }
}