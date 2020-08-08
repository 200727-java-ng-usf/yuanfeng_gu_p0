package com.revature.view;

import com.revature.domain.Account;
import com.revature.domain.CheckingAccount;
import com.revature.domain.Customer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BankTest {
    public static void main(String[] args) {

        // register
        Customer customer1 = new Customer();

        //type info
        customer1.setUsername("aaaa");
        customer1.setPassword("1234");
        customer1.setFirstname("yuan");
        customer1.setLastname("Gu");

        // set init balance
        customer1.setAccountValue(1000.00);





    }
}
