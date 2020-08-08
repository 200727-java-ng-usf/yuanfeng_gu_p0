package com.revature.domain;

import sun.misc.ClassFileTransformer;

/**
 * The saving account .
 */

public class SavingAccount extends CheckingAccount {


    private double interest;

    public SavingAccount(double savingAccountBalance, double interest) {
        super();
        this.savingAccountBalance = savingAccountBalance;
        this.interest = interest;
    }

    public double getSavingAccountBalance() {
        return savingAccountBalance;
    }

}
