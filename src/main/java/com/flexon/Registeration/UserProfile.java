package com.flexon.Registeration;

import java.sql.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfile {

    static PreparedStatement query = null;
    private static Connection userDB_conn = null;

    String first_name;
    String  last_name;
    int phone_number;
    //String dob;
    String email_id;
    String password;
    //String confirm_pwd;

    @JsonCreator
    public UserProfile (@JsonProperty("first_name") String fname, @JsonProperty("last_name")String lname, @JsonProperty("phone") int pnum, @JsonProperty("email") String email, @JsonProperty("password") String pwd){

        this.first_name = fname;
        this.last_name = lname;
        this.phone_number= pnum;
        //this.dob = dob;
        this.email_id = email;
        this.password = pwd;
    }
    public static void main (String [] args){


    }

    public static String addUser(UserProfile user) {

        try {
            userDB_conn = ConnectionManager.makeDBconn();

            String insertQueryStatement = "INSERT  INTO  user_table  VALUES  (?,?,?,?,?)";

            query = userDB_conn.prepareStatement(insertQueryStatement);
            query.setString(1, user.first_name);
            query.setString(2, user.last_name);
            query.setInt(3,  user.phone_number);
            query.setString(4, user.email_id);
            query.setString(5, user.password);

            // execute insert SQL statement
            query.executeUpdate();

            userDB_conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user.first_name + " added successfully");
    }

    public static String updateUser(UserProfile user){

        try {

            userDB_conn = ConnectionManager.makeDBconn();

            String updateQueryStatement = "UPDATE user_table  SET first_name = ?,last_name = ?, phone = ?, password = ? WHERE email = ?";

            query = userDB_conn.prepareStatement(updateQueryStatement);
            query.setString(1, user.first_name);
            query.setString(2, user.last_name);
            query.setInt(3,  user.phone_number);
            query.setString(4, user.password);
            query.setString(5, user.email_id);


            // execute insert SQL statement
            query.executeUpdate();

            userDB_conn.close();

        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        return (user.first_name + " updated successfully");

    }

    public static String viewUser(String email) {

        ResultSet rs = null;
        String resemail = null;
        String resfname = null;
        try {

            userDB_conn = ConnectionManager.makeDBconn();

            String updateQueryStatement = "SELECT * FROM user_table WHERE email = ?";

            query = userDB_conn.prepareStatement(updateQueryStatement);
            query.setString(1, email);


            // execute insert SQL statement

            rs = query.executeQuery();

            if (rs.next()) {

                resemail = rs.getString("email");
                resfname = rs.getString("first_name");

                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("first_name"));
            }
            userDB_conn.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resemail +"  " + resfname;
    }

    public static String deleteUser(String email) {

        ResultSet rs = null;
        try {

            userDB_conn = ConnectionManager.makeDBconn();

            String updateQueryStatement = "DELETE FROM user_table WHERE email = ?";

            query = userDB_conn.prepareStatement(updateQueryStatement);
            query.setString(1, email);


            // execute insert SQL statement

            query.executeUpdate();

            userDB_conn.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "Deleted user successfully";
    }

}
