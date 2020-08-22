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

    private CustomerService customerService;
    private Customer authUser;
    private Customer findUser;

    public LoginScreen() {
        System.out.println("[log] LoginScreen instantiating ");
        customerService = new CustomerService();
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
            System.out.println("You entered Username : " + username + " Password: " + password);
            System.out.println(authUser.getAccountNo());
            System.out.println(authUser.getAccount());

            DashBoardScreen dashBoardScreen = new DashBoardScreen();
            dashBoardScreen.setAuthUser(authUser);


            dashBoardScreen.render();



        } catch (IOException | AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

    }

    public Customer getAuthUser() {
        return authUser;
    }
}
