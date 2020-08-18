package com.revature.repository;

import com.revature.database.CustomerDataBase;
import com.revature.domain.Customer;

public class CustomerRepo {

    private CustomerDataBase customerDataSet = CustomerDataBase.customerDataSet;

    public CustomerRepo() {
        System.out.println("[log] . CustomerRepo Instantiating ");
    }

    public Customer findUserByCredentials(String username,String password){
        return customerDataSet.findUserByCredentials(username,password);
    }

    public Customer addCustomer(Customer newCustomer){
        return customerDataSet.addCustomer(newCustomer);
    }

    public Customer findUserByUsername(String username){
        return customerDataSet.findUserByUsername(username);
    }
}
