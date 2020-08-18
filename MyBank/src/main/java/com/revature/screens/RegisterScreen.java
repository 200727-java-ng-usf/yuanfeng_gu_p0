package com.revature.screens;

import com.revature.domain.Account;
import com.revature.domain.Customer;
import com.revature.services.CustomerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegisterScreen extends Screen{

    private CustomerService customerService;

    public  RegisterScreen(){
        System.out.println("[log] Instantiating RegisterScreen ");
        customerService = new CustomerService();
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
            Customer newRegisterUser = customerService.addAccount(newCustomer);
            newRegisterUser.setAccount(new Account(0));

            System.out.println(newRegisterUser);


            System.out.println(" Your have New Account now !");

            new LoginScreen().render();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
