package com.revature.repository;


import com.revature.domain.Account;
import com.revature.domain.Customer;
import com.revature.domain.Role;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;


/**
 * To create the connection with database
 * Job 1: read, write, change, update the data from AWS database by using postgresql
 *     2: search by name, accountNo ... etc from customer table and account table
 */

public class CustomerRepo {


    public CustomerRepo() {

    }

    public Optional<Customer> findUserByCredentials(String username, String password){

        Optional<Customer> _customer = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from mybank.customer c WHERE username = ? AND password = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            _customer = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _customer;
    }


    public boolean addCustomer(Customer newCustomer){

        boolean success = false;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "insert into mybank.customer(role_id,username,password,first_name,last_name,account_no ) values(?,?,?,?,?,?);";

            // second parameter here is used to indicate column names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newCustomer.getRole().ordinal()+1); // get the role position (index +1)
            pstmt.setString(2, newCustomer.getUsername());
            pstmt.setString(3, newCustomer.getPassword());
            pstmt.setString(4, newCustomer.getFirstName());
            pstmt.setString(5, newCustomer.getLastName());
            pstmt.setString(6,newCustomer.getAccountNo());

            pstmt.executeUpdate();

                success = true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return success;
    }



    public void UpdateAccount(String accountNo,double balance) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "UPDATE mybank.account SET balance = ? WHERE account_no = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1,balance);
            pstmt.setString(2, accountNo);

            pstmt.executeUpdate();



        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }


    }

    public void addCustomerAccount(Customer newCustomer){

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "insert into mybank.account(account_no,balance) values (?,?)";

            // second parameter here is used to indicate column names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,newCustomer.getAccountNo());
            pstmt.setDouble(2,0);

            pstmt.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }









    public Optional<Customer> findUserByUsername(String username){
        Optional<Customer> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from mybank.customer c WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }










    public Optional<Customer> findUserByAccountNo(Integer accountNo){
        Optional<Customer> _user = Optional.empty();

        String number = Integer.toString(accountNo);

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from mybank.customer c WHERE account_no = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,number);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }






    /**
     * Find Account from account table use by customer.account_no

     */


    public Optional<Account> findAccountByAccountNo(Integer accountNo){
        Optional<Account> _acc = Optional.empty();

        String number = Integer.toString(accountNo);

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from mybank.account a WHERE account_no = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,number);

            ResultSet rs = pstmt.executeQuery();
            Set<Account> acc = new HashSet<>();
            while (rs.next()) {
                Account temp = new Account();
                temp.setBalance(rs.getDouble("balance"));
                temp.setAccountNo(rs.getString("account_no"));
                System.out.println(temp);
                acc.add(temp);
            }

            return acc.stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _acc;
    }


    private Set<Customer> mapResultSet(ResultSet rs) throws SQLException {

        Set<Customer> users = new HashSet<>();

        while (rs.next()) {
            Customer temp = new Customer();
            temp.setId(rs.getInt("id"));
            temp.setFirstName(rs.getString("first_name"));
            temp.setLastName(rs.getString("last_name"));
            temp.setUsername(rs.getString("username"));
            temp.setPassword(rs.getString("password"));
            temp.setAccountNo(rs.getString("account_no"));
            System.out.println(temp);
            users.add(temp);
        }

        return users;

    }


}