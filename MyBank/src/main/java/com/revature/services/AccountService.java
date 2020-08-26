package com.revature.services;

import com.revature.domain.Customer;
import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.OverdraftException;
import com.revature.repository.AccountRepo;


import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


/**
 *   Account Service
 *  Job 1 : update account info to database
 *      2 : give user all the account function
 *
 */

public class AccountService {

    private AccountRepo accountRepo;
    private CustomerService customerService;

    private Customer authUser;
    NumberFormat us   = NumberFormat.getCurrencyInstance(Locale.US);   // print USD format

    public AccountService() {
        customerService = new CustomerService();

        accountRepo = new AccountRepo();  // connect to CustomerRepo.class
    }

    public void setAuthUser(Customer authUser) {
        this.authUser = authUser;
    }

    public void deposit(){
        double amount = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println(" -------  Deposit --------");
        System.out.println();
        System.out.print("please Enter the amount: ");


        try {
            amount = sc.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Please input a number ");
        }

        boolean isSuccess = false;        // check is negative number
        if(amount>0) {
            isSuccess = authUser.getAccount().deposit(amount);
            System.out.println();
            System.out.println();
        }else isSuccess = false;
        if(isSuccess){
            System.out.println(" You successfully deposited : "+us.format(amount));
            System.out.println();
            System.out.println(" New Account Balance        :" + us.format(authUser.getAccount().getBalance()));
            System.out.println();
            System.out.println("  ------------------------- ");
            updateAccountInfo(authUser.getAccountNo(),authUser.getAccount().getBalance());
        }else {
            System.out.println("      Wrong Input");
            System.out.println(" Transaction unsuccessful ");
        }

    }





    public void withdraw() {
        double amount=0;
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" -------  Withdraw --------");
            System.out.println();
            System.out.print("please Enter the amount: ");
            amount = sc.nextDouble();
        }catch (InputMismatchException e) {
            System.out.println("Please input a number ");
        }

        boolean isSuccess = false;     // check is negative number
        if(amount>0) {
            try {
                isSuccess = authUser.getAccount().withdraw(amount);       // also Overdraft protection
            } catch (OverdraftException e) {
                System.out.println("Your account balance is insufficient");
            }
        }else isSuccess = false;
        System.out.println();
        System.out.println();
        if(isSuccess) {
            System.out.println("You successfully withdrawn " + us.format(amount));
            System.out.println();
            System.out.println(" New Account Balance : " + us.format(authUser.getAccount().getBalance()));
            System.out.println();
            System.out.println("  ------------------------------");
            updateAccountInfo(authUser.getAccountNo(), authUser.getAccount().getBalance());
        } else {
            System.out.println("      Wrong Input");
            System.out.println(" Transaction unsuccessful ");
        }

    }







    public void transfer(){

        double amount = 0;
        String firstname ="";
        String lastname ="";
        Integer accountNo=0;
        Customer receiver;
        try {
            amount = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println(" -------  Transfer --------");
            System.out.println("please Enter the Recipient's name.");
            System.out.print("Firstname:");
            firstname = sc.nextLine();
            System.out.print("LastName:");
            lastname = sc.nextLine();
            System.out.println();
            System.out.print("please Enter the Account Number: ");
            accountNo = sc.nextInt();
            System.out.print("please Enter Amount: ");
            amount = sc.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("You entered the wrong information");
            System.out.println(" Signing Out ... ");
        }

        if(accountNo >= 100000 && accountNo<=1000999 && amount >0 ) {    // limited user input range

            try {

                receiver = customerService.transferTo(accountNo);     // get receiver info  data type Customer
                System.out.println("The recipient name you entered            : " + firstname + "." + lastname);

                System.out.println("The name of the recipient’s account record： " + receiver.getFirstName() + "." + receiver.getLastName());
                System.out.println();
                System.out.println("Do you want to want to continue (Y/N)");     // user option
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.nextLine();
                if (!answer.equalsIgnoreCase("y")) {
                    System.out.println("The transfer did not complete successfully");
                } else {
                        authUser.getAccount().withdraw(amount);      // take money from user account
                        receiver.getAccount().deposit(amount);             // receiver's account get money

                        System.out.println("You successfully transfer " + us.format(amount) + " to Account No: "
                                + receiver.getAccount().getAccountNo() + "(" + receiver.getFirstName() + "." + receiver.getLastName() + ")");

                        // update to data base  for both of them
                        updateAccountInfo(authUser.getAccountNo(), authUser.getAccount().getBalance());

                        updateAccountInfo(receiver.getAccountNo(), receiver.getAccount().getBalance());

                    }

            } catch (InvalidRequestException e) {
                System.out.println("You entered the wrong information");
                System.out.println("The transfer did not complete successfully");
            } catch (OverdraftException e) {
                System.out.println("Insufficient balance");
                System.out.println("The transfer did not complete successfully");

            }
            System.out.println();
            System.out.println();

            System.out.println(" New Account Balance : " + us.format(authUser.getAccount().getBalance()));
        }else{
            System.out.println("      Wrong Input");
            System.out.println(" Transaction unsuccessful ");
        }

    }

    // update database !

    public void updateAccountInfo(String accountNo,double balance){
        accountRepo.UpdateAccount(accountNo,balance);
    }



}
