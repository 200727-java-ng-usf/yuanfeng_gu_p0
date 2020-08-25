package com.revature.screens;
import java.text.NumberFormat;

import com.revature.domain.Account;
import com.revature.domain.Customer;
import com.revature.exceptions.OverdraftException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.services.AccountService;
import com.revature.services.CustomerService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 *  DashBoard Menu and Account Service
 *  Job 1 : show the dashboard menu;
 *      2 : give user options to pick;
 *      3 : all of the account manipulations
 */

public class DashBoardScreen extends Screen {

    LoginScreen loginScreen;
    Customer authUser; //
    private CustomerService customerService;
    private AccountService accountService;
    NumberFormat us   = NumberFormat.getCurrencyInstance(Locale.US);

    public DashBoardScreen() {
        loginScreen = new LoginScreen();
        customerService = new CustomerService();
        accountService = new AccountService();
    }

    public void setAuthUser(Customer authUser) {

        this.authUser = authUser;
        accountService.setAuthUser(authUser);
    }    // get the user info form login Screen

    /**
     *
     * @return print the user info
     */

    public double printAmount(){
        return authUser.getAccount().getBalance();
    }
    public String printName(){
        return authUser.getFirstName() + "." + authUser.getLastName();
    }
    public String PrintAccNo(){return authUser.getAccount().getAccountNo();}


    /**
     *  DashBoard function:Account Service
     *  deposit , withdrew , transfer , and show UserInfo(updated info).
     *
     *
     */


    public void viewCustomerInfo(){
        try {
            Customer customerInfo = customerService.viewCustomerInfo(authUser);
            System.out.println();
            System.out.println(" ----------  Customer Information --------------- ");
            System.out.println();
            System.out.println("   Name:            " + customerInfo.getFirstName() + "." + customerInfo.getLastName());
            System.out.println("   Account NO:      " + customerInfo.getAccountNo());
            System.out.println("   Account Balance: " + us.format(customerInfo.getAccount().getBalance()));
            System.out.println();
            System.out.println(" --------------------------------------------------");


        } catch (InvalidRequestException e) {
            System.out.println("Invalid Request");
        }
    }


    /**
     *  all of the Menu and options
     */
    @Override
    public void render() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-------------------  Dash Board --------------------");
        System.out.println();
        System.out.println("   Welcome ! "+ this.printName());
        System.out.println("   Account No: " + this.PrintAccNo());
        System.out.println("   You total balance : " + us.format(this.printAmount()));
        dashOptions();






    }


    public void printDashMenu(){
        System.out.println();
        System.out.println();
        System.out.println(" ---------------  Account management  -------------------");
        System.out.println();
        System.out.println("                 1) Deposit                       ");
        System.out.println("                 2) Withdrew                    ");
        System.out.println("                 3) Transfer                        ");
        System.out.println("                 4) View Info                       ");
        System.out.println("                 5) Sign Out                       ");
        System.out.println();
        System.out.println("-----------------  END  ------------------------");

    }

    public void dashOptions(){
        int option = 372819387;

        while (option!=5){
            this.printDashMenu();
            System.out.println();
            System.out.println();

            System.out.print("please Enter the number: ");
            try{
                Scanner sc = new Scanner(System.in);
                option = sc.nextInt();

            } catch (InputMismatchException e){
                System.out.println("Wrong Input ! Sign Out ...");
                System.out.println();
                System.out.println();
            }
            switch (option) {
                case 1: accountService.deposit();
                break;
                case 2: accountService.withdraw();
                break;
                case 3: accountService.transfer();
                break;
                case 4: this.viewCustomerInfo();
                break;
                case 5:  System.out.println("Exit...");break;
                default:
                    System.out.println(" Try again please......");

            }

        }
    }


}
