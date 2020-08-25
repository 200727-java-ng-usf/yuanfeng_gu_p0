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


public class AccountRepo {

    public AccountRepo() {
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






}
