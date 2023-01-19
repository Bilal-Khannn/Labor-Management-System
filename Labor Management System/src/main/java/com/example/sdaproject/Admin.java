package com.example.sdaproject;

//A connector class which connects the business logic with UI

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    public boolean adminVerify(String user, String pass)
    {
        boolean flag;
        AdminVerification adminVerification = new AdminVerification();
        flag = adminVerification.verify(user, pass);
        return flag;
    }

    public boolean addLabor(String name, String address, String phone, String cnic, String type)
    {
        Labor labor = new Labor();
        boolean flag = labor.addLabor(name, address, phone, cnic, type);
        return flag;
    }

    public ResultSet viewLabor()
    {
        Labor labor = new Labor();
        ResultSet rs = labor.viewLabor();
        return rs;
    }

    public boolean deleteLabor(int id)
    {
        Labor labor = new Labor();
        boolean flag = labor.deleteLabor(id);
        return flag;
    }

    public ResultSet searchlabor(int id)
    {
        Labor labor = new Labor();
        ResultSet rs = labor.searchLabor(id);
        return rs;
    }

    public boolean updatelabor(String id, String name, String address, String cnic, String phone, String type,
                               String rating, String booking, String status)
    {
        Labor labor = new Labor();
        boolean flag = labor.updateLabor(id, name, address, cnic, phone, type, rating, booking, status);
        return flag;
    }

    public ResultSet availableLabors()
    {
        Labor labor = new Labor();
        ResultSet rs = labor.availableLabors();
        return rs;
    }

    public boolean createBooking(String laborid, String name, String address, String cnic, String phone, String type, String date
            , String status)
    {
        Booking booking = new Booking();
        boolean flag = booking.createBooking(laborid, name, address, cnic, phone, type, date, status);
        return flag;
    }

    public boolean updateStatus(int id, String status)
    {
        Labor labor = new Labor();
        boolean flag = labor.updateStatus(id, status);
        return flag;
    }

    public ResultSet viewBooking()
    {
        Booking booking = new Booking();
        ResultSet rs = booking.viewBooking();
        return rs;
    }

    public boolean deleteBooking(int id)
    {
        Booking booking = new Booking();
        boolean val = booking.cancelBooking(id);
        return val;
    }

    public boolean completeBooking(int id) throws SQLException {
        Booking booking = new Booking();
        boolean flag = booking.completeBooking(id);
        return flag;
    }

    public int extractLaborID(int BookingID)
    {
        Booking booking = new Booking();
        int extractedID = booking.extractID(BookingID);
        return extractedID;
    }

    public boolean rewardLabor(int laborID, int reward)
    {
        Labor labor = new Labor();
        boolean flag = labor.rewardLabor(laborID, reward);
        return flag;
    }

    public boolean updateBookingCount(int laborID)
    {
        Labor labor = new Labor();
        boolean flag = labor.updateBookingCount(laborID);
        return flag;
    }

    public ResultSet viewCompletedBooking()
    {
        Booking booking = new Booking();
        ResultSet rs = booking.viewCompletedBooking();
        return rs;
    }

    public int confirmBooking(int laborid, String custname, int bookingid){

        Booking booking = new Booking();
        int val = booking.confirmBooking(laborid, custname, bookingid);
        return val;
    }

    public boolean updateRating(int laborid, int rating){
        Labor labor = new Labor();
        boolean flag = labor.updateRating(laborid, rating);
        return flag;
    }
}