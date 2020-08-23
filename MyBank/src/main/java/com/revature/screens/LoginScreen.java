package com.revature.screens;

import com.revature.domain.Account;
import com.revature.domain.Customer;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.services.CustomerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;

public class LoginScreen extends Screen {
    private static int counter=3;

    private CustomerService customerService;
    private Customer authUser;


    public LoginScreen() {

        customerService = new CustomerService();  //  CustomerService.class
    }


    public int getCounter() {
        return counter;
    }

    @Override
    public void render() {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String username,password;




        try {
            System.out.println("-------- Login ----------");
            System.out.println();
            System.out.print("Username: ");
            username = console.readLine();

            System.out.print("Password: ");
            password = console.readLine();
            System.out.println();
            System.out.println();

            authUser = customerService .authenticate(username,password);

            DashBoardScreen dashBoardScreen = new DashBoardScreen();
            dashBoardScreen.setAuthUser(authUser);                           // pass authUser to dashBoardScreen.class


            dashBoardScreen.render();



        } catch (IOException | AuthenticationException e) {
            System.out.println("You entered the wrong information");
            --counter;
            System.out.println("Total 3 times try. You have "+counter+" more.");
            if (counter==0){
                System.out.println(" Force exit program....");
                System.exit(1);
            }
        } catch (InvalidRequestException e) {
            System.out.println("Invalid  Request !");
        }

    }

    public Customer getAuthUser() {
        return authUser;
    }
}
