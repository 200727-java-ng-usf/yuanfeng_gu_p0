package com.revature.screens;

import com.revature.domain.Customer;
import com.revature.domain.OverdraftException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DashBoardScreen extends Screen {

    LoginScreen loginScreen;
    Customer authUser;

    public DashBoardScreen() {
        loginScreen = new LoginScreen();
    }

    public void setAuthUser(Customer authUser) {
        this.authUser = authUser;
    }

    public double printAmount(){
        return authUser.getAccount().getBalance();
    }
    public String printName(){
        return authUser.getFirstName() + "." + authUser.getLastName();
    }

    public Integer PrintAccNo(){return authUser.getAccount().getAccountNo();}

    public void deposit(){
        double amount = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println(" -------  Deposit --------");
        System.out.println();
        System.out.print("please Enter the amount: ");
        amount = sc.nextDouble();

        boolean isSuccess = false;
        isSuccess = authUser.getAccount().deposit(amount);
        System.out.println();
        System.out.println();
        if(isSuccess) System.out.println("You successfully deposited "+amount+" USD");
        System.out.println(" New Account Balance : " + this.printAmount());

    }

    public void withdraw() {
        double amount = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println(" -------  Withdraw --------");
        System.out.println();
        System.out.print("please Enter the amount: ");
        amount = sc.nextDouble();

        boolean isSuccess = false;
        try {
            isSuccess = authUser.getAccount().withdraw(amount);
        } catch (OverdraftException e) {
            System.out.println("Your account balance is insufficient");
        }
        System.out.println();
        System.out.println();
        if(isSuccess) System.out.println("You successfully withdrawn "+amount+" USD");
        System.out.println(" New Account Balance : " + this.printAmount());

    }

    @Override
    public void render() {
        System.out.println("-------------------  Dash Board --------------------");
        System.out.println();
        System.out.println("   Welcome ! "+ this.printName());
        System.out.println("   Account No: " + this.PrintAccNo());
        System.out.println("   You total balance : " + this.printAmount());
        dashOptions();






    }


    public void printDashMenu(){
        System.out.println();
        System.out.println();
        System.out.println(" ---------------  Account management  -------------------");
        System.out.println();
        System.out.println("                 1) Deposit                       ");
        System.out.println("                 2) Withdrew                    ");
        System.out.println("                 3) Go Back                        ");
        System.out.println();
        System.out.println("-----------------  END  ------------------------");

    }

    public void dashOptions(){
        int option = 372819387;

        while (option!=3){
            this.printDashMenu();
            System.out.println();
            System.out.println();

            System.out.print("please Enter the number: ");
            try{
                Scanner sc = new Scanner(System.in);
                option = sc.nextInt();

            } catch (InputMismatchException e){
                System.out.println("Please enter 1 , 2 or 3.");
                System.out.println();
                System.out.println();
            }
            switch (option) {
                case 1: this.deposit();
                break;
                case 2: this.withdraw();
                break;
                case 3:  System.out.println("Exit...");break;
                default:
                    System.out.println(" Try again please......");

            }

        }
    }


}
