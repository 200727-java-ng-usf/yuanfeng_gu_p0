package com.revature.repository;
import com.revature.domain.Account;
import org.mockito.Mockito;
import com.revature.domain.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AccountRepoTest {


    private AccountRepo mockAccountRepo = Mockito.mock(AccountRepo.class);
    private AccountRepo accountRepo;
    Account account;
    Customer customer;

    @Before
    public void setUp() throws Exception {
        accountRepo = new AccountRepo();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void updateAccount() {

        mockAccountRepo.UpdateAccount("1000878",4000.00);
    }

    @Test
    public void addCustomerAccount() {

                mockAccountRepo.addCustomerAccount(customer);
    }

    @Test
    public void findAccountByAccountNo() {

                assertTrue(accountRepo.findAccountByAccountNo(100001).isPresent());
           assertEquals("100005",accountRepo.findAccountByAccountNo(100005).get().getAccountNo());

            }
}