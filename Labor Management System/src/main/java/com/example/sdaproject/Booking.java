package com.example.sdaproject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Booking {

    private String laborid;
    private String name;
    private String address;
    private String cnic;
    private String phone;
    private String type;
    private String date;
    private String status;

    DButil dButil;      //To access database

    Booking() {
        laborid = "";
        name = "";
        address = "";
        cnic = "";
        phone = "";
        type = "";
        date = "";
        status = "";
    }

    Booking(String laborid, String name, String address, String cnic, String phone, String type, String date, String status) {
        this.laborid = laborid;
        this.name = name;
        this.address = address;
        this.cnic = cnic;
        this.phone = phone;
        this.type = type;
        this.date = date;
        this.status = status;
    }

    public String getLaborid() {
        return laborid;
    }

    public void setLaborid(String laborid) {
        this.laborid = laborid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean createBooking(String laborid, String name, String address, String cnic, String phone, String type, String date, String status) {
        this.laborid = laborid;
        this.name = name;
        this.address = address;
        this.cnic = cnic;
        this.phone = phone;
        this.type = type;
        this.date = date;
        this.status = status;

        dButil = new DButil();

        try {
            dButil = new DButil();
            //write a query to check if the user exists in the database
            String query =
                    "INSERT INTO Booking (LaborId, CustomerName, CustomerCnic, CustomerPhone, CustomerAddress, " +
                            "BookingType, BookingDate, BookingStatus) VALUES ('" + laborid + "', '" + name + "', '" + cnic + "', '" + phone + "', '" + address + "', '" + type + "', '" + date + "', '" + status + "')";

            int rs = dButil.executeupdate(query);

            if (rs == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet viewBooking()
    {
        try {
            dButil = new DButil();
            //write a query to check if the user exists in the database
            String query =
                    "SELECT * FROM Booking";

            ResultSet rs = dButil.executequery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cancelBooking(int BookingId)
    {
        try {
            dButil = new DButil();

            //write a query to check if the user exists in the database
            String query =
                    "DELETE FROM Booking WHERE BookingId = '" + BookingId + "'";

            int rs = dButil.executeupdate(query);

            if (rs == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean completeBooking(int id) throws SQLException {
        ResultSet rs = viewBooking();

        while(rs.next())
        {
            if(rs.getInt("BookingId") == id)
            {
                status = "Completed";
                //write a query to add record to bookingRecords table
                String query =
                        "INSERT INTO BookingRecords (LaborId, CustomerName, BookingStatus, BookingId) Values ('" + rs.getInt(2) +
                                "', '" + rs.getString(3) + "', '" + status + "', '" + rs.getInt(1) + "')";


                int rs1 = dButil.executeupdate(query);
                if(rs1 == 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }

    return false;
    }

    public int extractID(int BookingId)
    {
        try{
            dButil = new DButil();
            String query = "SELECT LaborId FROM Booking WHERE BookingId = '" + BookingId + "'";
            ResultSet rs = dButil.executequery(query);
            if(rs.next())
            {
                return rs.getInt("LaborId");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public ResultSet viewCompletedBooking()
    {
        try {
            dButil = new DButil();
            //write a query to check if the user exists in the database
            String query =
                    "SELECT * FROM BookingRecords";

            ResultSet rs = dButil.executequery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int confirmBooking(int laborid, String cust, int bookingid)
    {
        int flag = 0;
        try {
            dButil = new DButil();
            //write a query to check if the user exists in the database
            ResultSet rs = viewCompletedBooking();

            while (rs.next())
            {
                if(rs.getInt("LaborId") == laborid && rs.getString("CustomerName").equals(cust) && rs.getInt("BookingId") == bookingid)
                {
                    flag = 1;
                    break;
                }
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}