package com.example.sdaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class BookingFormController implements Initializable {
    @FXML
    private TextField addresstext;

    @FXML
    private TextField cnictext;

    @FXML
    private Button hirebutton;

    @FXML
    private TextField idtext;

    @FXML
    private Label labeltext;

    @FXML
    private TextField nametext;

    @FXML
    private TextField phonetext;

    @FXML
    private TextField typetext;

    @FXML
    private ListView<String> listview;

    @FXML
    private DatePicker datetext;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Admin admin = new Admin();
        ResultSet rs = admin.availableLabors();

        listview.getItems().clear();

        try {
            while (rs.next()) {
                listview.getItems().add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(
                        3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(
                                6) + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(
                                        9));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onBooking(ActionEvent event) {

        String id = idtext.getText();
        String name = nametext.getText();
        String cnic = cnictext.getText();
        String address = addresstext.getText();
        String phone = phonetext.getText();
        String type = typetext.getText();
        String date = datetext.getValue().toString();

        Admin admin = new Admin();
        ResultSet rs;

        if(id.isEmpty() || name.isEmpty() || cnic.isEmpty() || address.isEmpty() || phone.isEmpty() || type.isEmpty() || date.isEmpty())
        {
            labeltext.setText("Please fill all the fields");
            labeltext.setTextFill(Paint.valueOf("RED"));
        }
        else
        {
            rs = admin.searchlabor(Integer.parseInt(id));

            try {
                if (rs.next()) {
                        if (rs.getString(9).equals("Available")) {
                            boolean flag = admin.createBooking(id, name, address, cnic, phone, type, date, "Pending");
                            if (flag) {
                                labeltext.setTextFill(Paint.valueOf("GREEN"));
                                admin.updateStatus(Integer.parseInt(id), "Booked");
                                labeltext.setText("Booking created successfully");
                            } else {
                                labeltext.setTextFill(Paint.valueOf("RED"));
                                labeltext.setText("Booking creation failed");
                            }
                        } else {
                            labeltext.setTextFill(Paint.valueOf("RED"));
                            labeltext.setText("Labor is not available");
                        }
                } else {
                    labeltext.setTextFill(Paint.valueOf("RED"));
                    labeltext.setText("Labor not found");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        rs = admin.availableLabors();

        listview.getItems().clear();

        try {
            while (rs.next()) {
                if (rs.getString("BookingStatus").equals("Available")) {
                    listview.getItems().add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(
                            3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(
                            6) + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(
                            9));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("navigatior.fxml"));
        Stage stage = (Stage) phonetext.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}