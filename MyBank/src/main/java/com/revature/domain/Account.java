package com.revature.domain;

import com.revature.exceptions.OverdraftException;

import java.util.Objects;

/**
 *  To write the first class in the Project_0
 *  This class is to hold the account balance for the customer and
 *  do deposit,withdrew,getBalance for the customer (I will add Checking and Saving later)
 *
 */

public class Account {

    public String accountNo;
    private double balance;
    static Integer count = 1;

    public Account() {

        Customer customer = new Customer();
    }

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public Account(double balance) {
        this.balance = balance;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public boolean deposit(double amount){
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) throws OverdraftException {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            throw new OverdraftException("Insufficient funds. overdraft protection!");
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Double.compare(account.getBalance(), getBalance()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBalance());
    }

    @Override
    public String toString() {
        return " ";
    }
}
