package com.revature.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class MyBankTest {

    MyBank myBank;

    @Before
    public void setUp() throws Exception {
            myBank = new MyBank();
    }

    @After
    public void tearDown() throws Exception {
        myBank = null;
    }



    @Test
    public void addCustomer() {
        myBank.addCustomer("Mike", "Lee");
        myBank.addCustomer("Effie", "Shen");
        myBank.addCustomer("jack", "Ma");
        // traversing to see if success.

//        Iterator iterator = myBank.customers.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

        // try to use initialize the account balance


       myBank.customers.get(0).setAccount(new CheckingAccount(1000,300));
        myBank.customers.get(0).getAccount().deposit(500);
        System.out.println(myBank.customers.get(0).getAccount().getBalance());


    }
}