package com.revature.services;

import com.revature.domain.Account;
import com.revature.domain.Customer;
import com.revature.domain.Role;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.repository.AccountRepo;
import com.revature.repository.CustomerRepo;

import java.util.Optional;

public class CustomerService {

    private CustomerRepo customerRepo;
    private AccountRepo accountRepo;

    public CustomerService() {

        customerRepo = new CustomerRepo();  // connect to CustomerRepo.class
        accountRepo = new AccountRepo();
    }

    /**
     * To manipulate and search Customer Data
     * Job 1: Authentication
     *     2:  add, search Customer's data
     */

    public Customer authenticate(String username, String password) throws AuthenticationException, InvalidRequestException {

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credential values provide ! ");
        }

        Customer authenticateUser = customerRepo.findUserByCredentials(username, password).orElseThrow(AuthenticationException::new);

        if (authenticateUser == null) {
            throw new AuthenticationException(" No user found !");
        }
        Integer number = Integer.valueOf(authenticateUser.getAccountNo());

        Account acc = accountRepo.findAccountByAccountNo(number).orElseThrow(AuthenticationException::new);  // find user's account for user
        authenticateUser.setAccount(acc); // connect account to  authenticateUser

        return authenticateUser;  // return authenticateUser with account

    }

    public boolean addAccount(Customer newCustomer) {

        boolean success = false;

        if (!isUserValid(newCustomer)) {
            throw new RuntimeException("Invalid user field values provided during registration");
        }


        if (customerRepo.findUserByUsername(newCustomer.getUsername()).orElse(null) != null) {  // check if username exists
            throw new RuntimeException("Provide username is already in use!");
        }

        success = customerRepo.addCustomer(newCustomer);            // add in customer table
        if (success) accountRepo.addCustomerAccount(newCustomer);   // add in account table

        newCustomer.setRole(Role.BASIC);    // All new customer set to BASIC


        return success;

    }

    // Account function
    public Customer transferTo(Integer accountNo) throws InvalidRequestException {
        if (accountNo == null) {
            throw new InvalidRequestException("Invalid credential values provide ! ");
        } else {

            // find targetUser by accountNo
            Customer targetUser = customerRepo.findUserByAccountNo(accountNo).orElseThrow(AuthenticationException::new);

            // Also find targetUser's account by same accountNo
            Account targetAccount = accountRepo.findAccountByAccountNo(accountNo).orElseThrow(AuthenticationException::new);

            // connect account to user
            targetUser.setAccount(targetAccount);

            //  return Target User with account
            return targetUser;
        }

    }


    // Account function
    public Customer viewCustomerInfo(Customer customer) throws InvalidRequestException {

        if (customer == null) {
            throw new InvalidRequestException("Invalid credential values provide ! ");
        } else {

            // same, find user and account then connect together
            Customer targetUser = customerRepo.findUserByAccountNo(Integer.valueOf(customer.getAccountNo())).orElseThrow(AuthenticationException::new);
            Account targetAccount = accountRepo.findAccountByAccountNo(Integer.valueOf(customer.getAccountNo())).orElseThrow(AuthenticationException::new);
            targetUser.setAccount(targetAccount);


            return targetUser;
        }

    }

    // checker
    public boolean isUserValid(Customer user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;

    }

}



