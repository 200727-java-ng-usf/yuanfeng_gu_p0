package com.revature.screens;

import com.revature.domain.Account;
import com.revature.domain.Customer;
import com.revature.domain.Role;
import com.revature.repository.AccountRepo;
import com.revature.services.AccountService;
import com.revature.services.CustomerService;
import com.revature.util.AccountNoGenerate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class RegisterScreen extends Screen{

    private CustomerService customerService;
    private AccountRepo accountRepo;

    public  RegisterScreen(){

        customerService = new CustomerService();
        accountRepo = new AccountRepo();
    }

    @Override
    public void render() {

        String firstname,lastname,username,password;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Sign up for a new account");
            System.out.print("First name: ");
            firstname = reader.readLine();
            System.out.print("Last name: ");
            lastname = reader.readLine();
            System.out.print("User name: ");
            username = reader.readLine();
            System.out.print("Password: ");
            password = reader.readLine();

            Customer newCustomer = new Customer(firstname,lastname,username,password);

            String newAccount = AccountNoGenerate.newAccountNo();

            /**
             * check account is the same account number in the database
             */
            boolean hasAcc = accountRepo.findAccountByAccountNo(Integer.valueOf(newAccount)).isPresent();
            while(hasAcc){
                newAccount = AccountNoGenerate.newAccountNo();
            }
            newCustomer.setAccountNo(AccountNoGenerate.newAccountNo());  // set new account Number for new user
            newCustomer.setRole(Role.BASIC); //set up account type


            customerService.addAccount(newCustomer);

            System.out.println(" New account successfully created !");



            System.out.println("Please log in with your new username and password");

            System.out.println();
            System.out.println();

            new LoginScreen().render();


        } catch (IOException e) {
            System.out.println(" You entered the wrong information ");

        }


    }
}
