package com.revature.services;

import com.revature.domain.Customer;
import com.revature.domain.Role;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.repository.CustomerRepo;

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

        Customer authenticateUser = customerRepo.findUserByCredentials(username,password);

        if(authenticateUser == null){
            throw new AuthenticationException(" No user found !");
        }

        return authenticateUser;

    }

    public  Customer addAccount(Customer newCustomer){

        if (!isUserValid(newCustomer)){
            //TODO implememnt a custom InvalidRequest Exception
            throw new RuntimeException("Invalid user field values provided during registration");
        }

        if(customerRepo.findUserByUsername(newCustomer.getUsername())!=null){
            // TODO implement a custom Exception
            throw new RuntimeException("Provide username is already in use!")
                    ;        }

        newCustomer.setRole(Role.BASIC);

        return customerRepo.addCustomer(newCustomer);


    }


    public boolean isUserValid(Customer user){
        if(user == null)return  false;
        if(user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if(user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if(user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if(user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;

    }





}