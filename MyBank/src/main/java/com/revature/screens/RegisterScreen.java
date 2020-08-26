package com.revature.screens;

import com.revature.domain.Account;
import com.revature.domain.Customer;
import com.revature.domain.Role;
import com.revature.repository.AccountRepo;
import com.revature.repository.CustomerRepo;
import com.revature.services.AccountService;
import com.revature.services.CustomerService;
import com.revature.util.AccountNoGenerate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterScreen extends Screen{

    private CustomerService customerService;
    private AccountRepo accountRepo;
    private CustomerRepo customerRepo;

    public  RegisterScreen(){

        customerService = new CustomerService();
        accountRepo = new AccountRepo();
        customerRepo = new CustomerRepo();
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

         // check if firstname and lastname includes numbers
            while(HasDigit(firstname)||HasDigit(lastname)){
                System.out.println("sorry ...");
                System.out.println("No numbers allow in your first or last name");
                System.out.println("please enter your name again ");
                System.out.println();
                System.out.print("Legal First name: ");
                firstname = reader.readLine();
                System.out.print("Legal Last name: ");
                lastname = reader.readLine();
            }


            System.out.print("User name: ");
            username = reader.readLine();

            while (customerRepo.findUserByUsername(username).isPresent()==true){
                System.out.println("username already exists");
                System.out.println("please pick another username ");
                System.out.println();
                System.out.print("User name: ");
                username = reader.readLine();
            }
            System.out.print("Password: ");
            password = reader.readLine();



            Customer newCustomer = new Customer(firstname,lastname,username,password);



            /**
             * check account is the same account number in the database
             *
             *
             */

            String newAccount;
            boolean hasAcc = true;
            while(hasAcc){
                newAccount = AccountNoGenerate.newAccountNo();
                hasAcc = accountRepo.findAccountByAccountNo(Integer.valueOf(newAccount)).isPresent(); // set new account Number for new user
            }
            newCustomer.setAccountNo(AccountNoGenerate.newAccountNo());
            newCustomer.setRole(Role.BASIC); //set up account type


            customerService.addAccount(newCustomer);     // pass the user

            System.out.println(" New account successfully created !");



            System.out.println("Please log in with your new username and password");

            System.out.println();
            System.out.println();

            new LoginScreen().render();


        } catch (IOException e) {
            System.out.println(" You entered the wrong information ");

        }

    }
// String checker
    public boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }
}
