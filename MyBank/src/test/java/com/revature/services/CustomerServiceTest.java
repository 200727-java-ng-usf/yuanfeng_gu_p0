package com.revature.services;
import static org.mockito.Mockito.*;
import com.revature.domain.Account;
import com.revature.domain.Customer;
import com.revature.exceptions.InvalidRequestException;
import com.revature.repository.CustomerRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerRepo customerRepo;
    private Customer customer;
    private CustomerService mockService = mock(CustomerService.class);
    private CustomerRepo mockRepo = mock(CustomerRepo.class);

    @Before
    public void setUp() throws Exception {
        customer = new Customer("aaa","bbb","ccc","ddd");
        customerRepo = new CustomerRepo();
        customerService = new CustomerService();


    }

    @After
    public void tearDown() throws Exception {
        customer = null;
        customerRepo = null;
        customerService = null;

    }

    @Test
    public void transferTo() throws InvalidRequestException {

       customer = customerService.transferTo(100005);
       assertEquals("100005",customer.getAccount().getAccountNo());
       assertEquals("Gu",customer.getLastName());
       assertEquals("gu8",customer.getUsername());


    }

    @Test
    public void authenticate() throws InvalidRequestException {
        customer = customerService.authenticate("gu8","8888");
        assertEquals("100005",customer.getAccount().getAccountNo());
        assertEquals("Gu",customer.getLastName());
        assertEquals("gu8",customer.getUsername());
    }

    @Test
    public void addAccount() {
        Mockito.when(mockService.addAccount(customer)).thenReturn(true);
        assertTrue(mockService.addAccount(customer));
    }


    @Test
    public void viewCustomerInfo() throws InvalidRequestException {

        Mockito.when(mockService.viewCustomerInfo(customer)).thenReturn(customer);
        assertNotNull(mockService.viewCustomerInfo(customer));
        assertEquals("ccc",mockService.viewCustomerInfo(customer).getUsername());
    }

    @Test
    public void isUserValid() {
        assertTrue(customerService.isUserValid(customer));
    }

}