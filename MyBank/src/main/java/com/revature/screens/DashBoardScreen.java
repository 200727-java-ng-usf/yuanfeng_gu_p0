package com.revature.screens;
import java.text.NumberFormat;
import com.revature.domain.Customer;
import com.revature.exceptions.OverdraftException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.services.CustomerService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class DashBoardScreen extends Screen {

    LoginScreen loginScreen;
    Customer authUser;
    private CustomerService customerService;
    NumberFormat us   = NumberFormat.getCurrencyInstance(Locale.US);

    public DashBoardScreen() {
        loginScreen = new LoginScreen();
        customerService = new CustomerService();
    }

    public void setAuthUser(Customer authUser) {
        this.authUser = authUser;
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
     *  DashBoard functions
     */

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

        boolean isSuccess = false;
        if(amount>0) {
            isSuccess = authUser.getAccount().deposit(amount);
            System.out.println();
            System.out.println();
        }else isSuccess = false;
        if(isSuccess){
            System.out.println(" You successfully deposited : "+us.format(amount));
            System.out.println();
            System.out.println(" New Account Balance        :" + us.format(this.printAmount()));
        System.out.println();
        System.out.println("  ------------------------- ");
        customerService.updateAccountInfo(authUser.getAccountNo(),this.printAmount());
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

        boolean isSuccess = false;
        if(amount>0) {
            try {
                isSuccess = authUser.getAccount().withdraw(amount);
            } catch (OverdraftException e) {
                System.out.println("Your account balance is insufficient");
            }
        }else isSuccess = false;
            System.out.println();
            System.out.println();
        if(isSuccess) {
            System.out.println("You successfully withdrawn " + us.format(amount));
            System.out.println();
            System.out.println(" New Account Balance : " + us.format(this.printAmount()));
            System.out.println();
            System.out.println("  ------------------------------");
            customerService.updateAccountInfo(authUser.getAccountNo(), this.printAmount());
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

        if(accountNo >= 1000000 && accountNo<=1000999 && amount >0 ) {

            try {

                receiver = customerService.transferTo(accountNo);
                System.out.println("The recipient name you entered            : " + firstname + "." + lastname);

                System.out.println("The name of the recipient’s account record： " + receiver.getFirstName() + "." + receiver.getLastName());
                System.out.println();
                System.out.println("Do you want to want to continue (Y/N)");
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.nextLine();
                if (!answer.equalsIgnoreCase("y")) {
                    System.out.println("The transfer did not complete successfully");
                } else {
                    if (authUser.getAccount().withdraw(amount)) {
                        receiver.getAccount().deposit(amount);

                        System.out.println("You successfully transfer " + us.format(amount) + " to Account No: "
                                + receiver.getAccount().getAccountNo() + "(" + receiver.getFirstName() + "." + receiver.getLastName() + ")");
                        customerService.updateAccountInfo(authUser.getAccountNo(), authUser.getAccount().getBalance());
                        customerService.updateAccountInfo(receiver.getAccountNo(), receiver.getAccount().getBalance());

                    } else System.out.println("The transfer did not complete successfully");
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

            System.out.println(" New Account Balance : " + us.format(this.printAmount()));
        }else{
            System.out.println("      Wrong Input");
            System.out.println(" Transaction unsuccessful ");
        }

    }






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
                case 1: this.deposit();
                break;
                case 2: this.withdraw();
                break;
                case 3: this.transfer();
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
