package com.revature.repository;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerRepoTest {

    @Test
    public void updateAccount() {
        CustomerRepo customerRepo = new CustomerRepo();

        customerRepo.UpdateAccount("1000878",4000.00);

    }
}