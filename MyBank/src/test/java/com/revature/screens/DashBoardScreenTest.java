package com.revature.screens;

import com.revature.domain.Account;
import com.revature.domain.Customer;
import com.revature.exceptions.InvalidRequestException;
import com.revature.repository.CustomerRepo;
import com.revature.services.CustomerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class DashBoardScreenTest {
    private DashBoardScreen dashBoardScreen = new DashBoardScreen();

    private CustomerService customerService;
    private CustomerRepo customerRepo;
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        customerService = new CustomerService();
        customerRepo = new CustomerRepo();
      customer = new Customer("aaa","bbb","ccc","ddd");

    }

    @After
    public void tearDown() throws Exception {

        customerService = null;
        customerRepo = null;
        customer = null;

    }

    @Test
    public void setAuthUser() {
        dashBoardScreen.setAuthUser(customer);
        assertEquals("bbb",dashBoardScreen.authUser.getLastName());
    }

    @Test
    public void printAmount() {
        dashBoardScreen.setAuthUser(customer);
        dashBoardScreen.authUser.setAccount(new Account("1000999",200));
        assertEquals(200.00,200,dashBoardScreen.printAmount());
    }

    @Test
    public void printName() {
        dashBoardScreen.setAuthUser(customer);
        assertEquals("aaa.bbb",dashBoardScreen.printName());
    }

    @Test
    public void printAccNo() {
        dashBoardScreen.setAuthUser(customer);
        dashBoardScreen.authUser.setAccount(new Account("1000999",200));
        assertEquals("1000999",dashBoardScreen.PrintAccNo());

    }

    @Test
    public void viewCustomerInfo() throws InvalidRequestException {

        dashBoardScreen.authUser = customerRepo.findUserByAccountNo(100005).get();
        dashBoardScreen.viewCustomerInfo();
    }


    @Test
    public void printDashMenu() {
        dashBoardScreen.printDashMenu();
    }

}