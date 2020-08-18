package com.revature.screens;

import com.revature.domain.Customer;

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



    @Override
    public void render() {
        System.out.println("-------------------  Dash Board --------------------");
        System.out.println();
        System.out.println("   Welcome ! "+ this.printName());
        System.out.println("   You total balance : " + this.printAmount());
    }
}
