package com.example.sdaproject;

import java.sql.ResultSet;

public class Labor {

    DButil dButil;
    private String name;
    private String address;
    private String phone;
    private String cnic;
    private int rating;
    private String type;
    private String status;
    private String id;
    private int noofbookings;

    Labor() {
        name = "";
        address = "";
        phone = "";
        cnic = "";
        rating = 0;
        type = "";
        status = "";
        id = "";
        noofbookings = 0;
    }

    Labor(String name, String address, String phone, String cnic, int rating, String type, String status, String id, int noofbookings) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cnic = cnic;
        this.rating = rating;
        this.type = type;
        this.status = status;
        this.id = id;
        this.noofbookings = noofbookings;
    }

    public boolean addLabor(String name, String address, String phone, String cnic, String type) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cnic = cnic;
        this.type = type;
        this.status = "Available";
        this.rating = 0;
        this.noofbookings = 0;

        dButil = new DButil();

        try {
            dButil = new DButil();
            //write a query to check if the user exists in the database
            String query =
                    "INSERT INTO Labor (LaborName, Address, PhoneNo, Cnic, LaborType, BookingStatus, Rating, NoOfBookings) VALUES ('" + name + "', '" + address + "', '" + phone + "', '" + cnic + "', '" + type + "', '" + status + "', '" + rating + "', '" + noofbookings + "')";

            int rs = dButil.executeupdate(query);

            if (rs == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public ResultSet viewLabor() {
        dButil = new DButil();
        try {
            dButil = new DButil();
            //write a query to check if the user exists in the database
            String query = "SELECT * FROM Labor";

            ResultSet rs = dButil.executequery(query);

            return rs;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public boolean deleteLabor(int id)
    {
        dButil = new DButil();
        try {
            dButil = new DButil();
            //write a query to check if the user exists in the database
            String query = "DELETE FROM Labor WHERE id = " + id;

            int rs = dButil.executeupdate(query);

            if (rs == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public ResultSet searchLabor(int id)
    {
        try {
            dButil = new DButil();
            //write a query to check if the user exists in the database
            String query = "SELECT * FROM Labor where id = " + id;

            ResultSet rs = dButil.executequery(query);

            return rs;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean updateLabor(String id, String name, String address, String cnic, String phone, String type,
                               String rating, String booking, String status)
    {
        try {
            dButil = new DButil();

            int converted_id = Integer.parseInt(id);
            int converted_rating = Integer.parseInt(rating);
            int converted_noofbookings = Integer.parseInt(booking);

            //write a query to check if the user exists in the database
            String query =
                    "UPDATE Labor SET LaborName = '" + name + "', Address = '" + address + "', PhoneNo = '" + phone + "', Cnic = '" + cnic + "', LaborType = '" + type + "', BookingStatus = '" + status + "', Rating = '" + converted_rating + "', NoOfBookings = '" + converted_noofbookings + "' WHERE id = " + converted_id;

            int rs = dButil.executeupdate(query);

            if (rs == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public ResultSet availableLabors()
    {
        try {
            dButil = new DButil();
            //write a query to check if the user exists in the database
            String query = "SELECT * FROM Labor where BookingStatus = 'Available'";

            ResultSet rs = dButil.executequery(query);

            return rs;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean updateStatus(int id, String status)
    {
        try {
            dButil = new DButil();

            //write a query to check if the user exists in the database
            String query =
                    "UPDATE Labor SET BookingStatus = '" + status + "' WHERE id = " + id;

            int rs = dButil.executeupdate(query);

            if (rs == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean rewardLabor(int id, int reward)
    {
        try {
            dButil = new DButil();

            //write a query to check if the user exists in the database

            String query = "UPDATE Labor SET RewardAmount = " + reward + " WHERE id = " + id + " AND NoOfBookings > 5";
            int rs = dButil.executeupdate(query);

            if (rs == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateBookingCount(int LaborId)
    {
        try {
            dButil = new DButil();

            //write a query to check if the user exists in the database
            String query =
                    "UPDATE Labor SET NoOfBookings = NoOfBookings + 1 WHERE id = " + LaborId;

            int rs = dButil.executeupdate(query);

            if (rs == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateRating(int laborId, int rating)
    {
        try {
            dButil = new DButil();

            //write a query to check if the user exists in the database
            String query =
                     "Update Labor SET Rating = Rating + " + rating + " WHERE id = " + laborId;

            int rs = dButil.executeupdate(query);

            if (rs == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}