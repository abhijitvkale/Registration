package com.flexon.Registeration;
import javax.sound.midi.SysexMessage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {

    private static Connection userDB_conn = null;

    public static Connection makeDBconn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Congrats - Seems your MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry, couldn't find JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
            e.printStackTrace();
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            userDB_conn = DriverManager.getConnection("jdbc:mysql://172.17.0.2:3306/test_schema", "root", "password");
            if (userDB_conn != null) {
                System.out.println("Connection Successful! Enjoy. Now it's time to push data");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("MySQL Connection Failed!");
            e.printStackTrace();
        }
        return userDB_conn;
    }
}
