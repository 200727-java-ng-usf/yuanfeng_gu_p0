package com.revature.domain;

import com.revature.exceptions.OverdraftException;

/**
 *  checking Account
 *
 *
 */

public class CheckingAccount extends Account {
    private double balance;
    private Customer customer;
    protected double checkingAccountBalance;
    protected double savingAccountBalance;

    public CheckingAccount() {
    }

    public CheckingAccount(double checkingAccountBalance, double savingAccountBalance) {
        this.checkingAccountBalance = checkingAccountBalance;
        this.savingAccountBalance = savingAccountBalance;
    }

    public CheckingAccount(Customer customer) {
        this.customer =customer;

    }

    public void setCheckingAccountBalance(double checkingAccountBalance) {
        this.checkingAccountBalance = checkingAccountBalance;
    }

    public double getCheckingAccountBalance() {
        return checkingAccountBalance;
    }

    @Override
    public double getBalance() {
        this.balance = checkingAccountBalance + savingAccountBalance;
        return balance;
    }


    @Override
    public boolean deposit(double amount) {
        checkingAccountBalance += amount;
        return true;
    }

    @Override
    public boolean withdraw(double amount) throws OverdraftException {
        // get money form balance
        if (amount <= checkingAccountBalance) {
            checkingAccountBalance -= amount;
            return true;
        } else {
            throw new OverdraftException("Insufficient funds. overdraft protection!");
        }
    }
}
