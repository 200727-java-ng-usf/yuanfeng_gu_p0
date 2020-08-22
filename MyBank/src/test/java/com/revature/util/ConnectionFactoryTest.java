package com.revature.util;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ConnectionFactoryTest {

    @Test
    public void getConnection() {

        Connection conn = ConnectionFactory.getInstance().getConnection();
        assertNotNull(conn);

    }
}