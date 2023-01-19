//A class to handle database utilities

package com.example.sdaproject;
import java.sql.*;

public class DButil {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "RIAZKHAN8423";
    private static final String url = "jdbc:mysql://localhost:3306/sdadb";
    private static Connection con = null;

    public static void connectdb() throws SQLException {            //Connect to the database
        try
        {
            if(con == null)
            {
                Class.forName(JDBC_DRIVER);
                con = DriverManager.getConnection(url, user, pass);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void disconnectdb() throws SQLException {         //Disconnect from the database
        try
        {
            if (con != null && !con.isClosed())
            {
                con.close();
            }
        } catch (Exception e)
        {
            throw e;
        }
    }

    public static ResultSet executequery(String query) throws SQLException {        //Execute a query
        connectdb();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(query);

        return rs;
    }

    public static int executeupdate(String query) throws SQLException {        //Execute a query
        connectdb();

        Statement stmt = con.createStatement();

        int rs = stmt.executeUpdate(query);

        return rs;
    }
}