package com.revature.domain;

import java.util.Objects;

/**
 * To hold customer info and link to Class.Account
 *
 */

public class Customer {

    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Account account;
    Role role;

    public Customer() {
    }
        // for sign in
    public Customer(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public Customer(Integer accountNo, String firstName, String lastName, String username, String password) {
        this.id = accountNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public Customer(Customer copy) {
        this(copy.firstName, copy.lastName, copy.username, copy.password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer accountNo) {
        this.id = accountNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId()) &&
                Objects.equals(getFirstName(), customer.getFirstName()) &&
                Objects.equals(getLastName(), customer.getLastName()) &&
                Objects.equals(getUsername(), customer.getUsername()) &&
                Objects.equals(getPassword(), customer.getPassword()) &&
                Objects.equals(getAccount(), customer.getAccount()) &&
                getRole() == customer.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getUsername(), getPassword(), getAccount(), getRole());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Id =" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", account=" + account +
                ", role=" + role +
                '}';
    }
}
