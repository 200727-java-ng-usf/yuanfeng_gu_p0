package com.revature.domain;

public class CheckingAccount{

    private double checkingBalance;

    public CheckingAccount() {
    }

    public CheckingAccount(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }


    public double getBalance() {
        return checkingBalance;
    }


    public boolean deposit(double amount) {
        checkingBalance += amount;
        return true;
    }


    public void withdraw(double amount) throws OverdraftException {
        // get money form balance
        if (amount <= checkingBalance) {
            checkingBalance -= amount;
        }else{
            throw new OverdraftException("Insufficient funds. overdraft protection!");
        }
    }


}
