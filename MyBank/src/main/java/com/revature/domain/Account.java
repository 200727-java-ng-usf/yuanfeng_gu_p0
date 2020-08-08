package com.revature.domain;

/**
 *  To write the first class in the Project_0
 *  This class is to hold the account balance for the customer and
 *  do deposit,withdrew,getBalance for the customer (I will add Checking and Saving later)
 *
 */

public abstract class Account {

    public Account() {
    }

    public abstract double getBalance();

    public abstract boolean deposit(double amount);

    public abstract boolean withdraw(double amount) throws OverdraftException;


}
