package com.revature.domain;

import java.util.Objects;

public class Account extends SavingAccount{


    private int accountNumber;
    private double accountValue;


    public Account() {
        super();
    }


    public Account(int accountNumber, double accountValue) {
        super();
        this.accountNumber = accountNumber;
        this.accountValue = accountValue;
    }


    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }


    public double getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(double accountValue) {
        this.accountValue = accountValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getAccountNumber() == account.getAccountNumber() &&
                Double.compare(account.getAccountValue(), getAccountValue()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountNumber(), getAccountValue());
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountValue=" + accountValue +
                '}';
    }
}
