package com.revature.domain;

public class SavingAccount extends CheckingAccount{
    private double interestRate;
    private double savingBalance;

    public SavingAccount() {
    }

    public SavingAccount(double interestRate, double savingBalance) {
        this.interestRate = interestRate;
        this.savingBalance = savingBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }
}
