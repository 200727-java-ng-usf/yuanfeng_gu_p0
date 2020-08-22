package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {



        private static ConnectionFactory connFactory = new ConnectionFactory();

        private Properties newProperties = new Properties();

        private ConnectionFactory(){
            try {
                newProperties.load(new FileReader("src/main/resources/key.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public static ConnectionFactory getInstance() {
            return connFactory;
        }

        public Connection getConnection() {

            Connection conn = null;

            try {

                // Force the JVM to load the PostGreSQL JDBC driver
                Class.forName("org.postgresql.Driver");

                conn = DriverManager.getConnection(newProperties.getProperty("url"),newProperties.getProperty("username"),
                        newProperties.getProperty("password"));

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            if (conn == null) {
                throw new RuntimeException("Failed to establish connection.");
            }

            return conn;

        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

    }

