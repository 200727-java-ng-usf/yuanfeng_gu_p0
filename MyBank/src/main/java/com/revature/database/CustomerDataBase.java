package com.revature.database;

import com.revature.domain.Account;
import com.revature.domain.Customer;

import java.util.HashMap;

public class CustomerDataBase extends HashMap<Integer, Customer> {

    public static CustomerDataBase customerDataSet = new CustomerDataBase();

    public static int key = 1;

    static {

        customerDataSet.addCustomer(new Customer("yuanfeng","Gu","guyuanfeng8","1101")).setAccount(new Account(1500));
        customerDataSet.addCustomer(new Customer("effie","shen","effie1122","1122")).setAccount(new Account(1500));
        customerDataSet.addCustomer(new Customer("shao","Gu","gushaoxian","1008")).setAccount(new Account(1500));
        customerDataSet.addCustomer(new Customer("yuqin","zhao","zhaoyuqin","1112")).setAccount(new Account(1500));
        customerDataSet.addCustomer(new Customer("eden","Gu","edenfgu","1114")).setAccount(new Account(1500));


    }

    public Customer addCustomer(Customer newCustomer){

        Customer nCustomer = new Customer(newCustomer);

        nCustomer.setAccountNo(key);

        customerDataSet.put(key++,nCustomer);

        return nCustomer;
    }

    public Customer findUserByCredentials(String username,String password){

        for(Customer user: customerDataSet.values()){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public Customer findUserByUsername(String username){
        for (Customer user:customerDataSet.values()
             ) {if(user.getUsername().equals(username)){
                 return  user;
        }

        }
        return null;
    }



}
