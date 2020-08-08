package com.revature.domain;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

/**
 *  MyBank is to create a ArrayList for organize the Class.Customer
 *  and Make a Joint Account function later
 */

public class MyBank {
    ArrayList<Customer> customers = new ArrayList<>();

    public MyBank() {
    }

    public boolean addCustomer(String firstname,String lastname){    // add customer
        try {
            customers.add(new Customer(firstname,lastname));
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return true;
    }
}
