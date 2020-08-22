package com.revature.services;

import com.revature.domain.Account;
import com.revature.domain.Customer;
import com.revature.domain.Role;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.repository.CustomerRepo;

import java.util.Optional;

public class CustomerService {

    private CustomerRepo customerRepo;

    public CustomerService() {
        System.out.println("[log] CustomerService instantiating ");
        customerRepo = new CustomerRepo();
    }

    public Customer authenticate(String username,String password) throws AuthenticationException, InvalidRequestException {

        if(username == null || username.trim().equals("")||password == null || password.trim().equals("")){
               throw new InvalidRequestException("Invalid credential values provide ! ");
        }

        Customer authenticateUser = customerRepo.findUserByCredentials(username,password).orElseThrow(AuthenticationException::new);

        if(authenticateUser == null){
            throw new AuthenticationException(" No user found !");
        }
        Integer number = Integer.valueOf(authenticateUser.getAccountNo());

        Account acc = customerRepo.findAccountByAccountNo(number).orElseThrow(AuthenticationException::new);
        authenticateUser.setAccount(acc);

        return authenticateUser;

    }

    public boolean addAccount(Customer newCustomer){

        boolean success = false;

        if (!isUserValid(newCustomer)){
            throw new RuntimeException("Invalid user field values provided during registration");
        }




      if( customerRepo.findUserByUsername(newCustomer.getUsername()).orElse(null)!=null){  // check if username exists
          throw new RuntimeException("Provide username is already in use!");        }

        success = customerRepo.addCustomer(newCustomer);            // add in customer table
        if (success)customerRepo.addCustomerAccount(newCustomer);   // add in account table


        return success;

    }

    public Customer transferTo(Integer accountNo) throws InvalidRequestException {
        if(accountNo == null){
            throw new InvalidRequestException("Invalid credential values provide ! ");
        }else {
            Customer targetUser = customerRepo.findUserByAccountNo(accountNo).orElseThrow(AuthenticationException::new);
            Account targetAccount = customerRepo.findAccountByAccountNo(accountNo).orElseThrow(AuthenticationException::new);
            targetUser.setAccount(targetAccount);


            return targetUser;
        }

    }


    public boolean isUserValid(Customer user){
        if(user == null)return  false;
        if(user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if(user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if(user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if(user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;

    }

   public void updateAccountInfo(String accountNo,double balance){
        customerRepo.UpdateAccount(accountNo,balance);
   }



}