package com.example.sdaproject;

import java.net.ConnectException;
import java.sql.*;

public class AdminVerification {
    DButil dbobj;           //An object of database utility class

    public boolean verify(String user, String pass)
    {

        try{
            dbobj = new DButil();
            //write a query to check if the user exists in the database
            String query = "SELECT * FROM Admininfo WHERE username = '"+user+"' AND pass = '"+pass+"'";
            ResultSet rs = dbobj.executequery(query);
            if(rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
}