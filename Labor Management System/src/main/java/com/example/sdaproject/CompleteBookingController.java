package com.example.sdaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CompleteBookingController implements Initializable {

    @FXML
    private ListView<String> activelist;

    @FXML
    private Button completebutton;

    @FXML
    private Button dashboardbutton;

    @FXML
    private TextField idtext;

    @FXML
    private Label labeltext;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Admin admin = new Admin();
        ResultSet rs = admin.viewBooking();

        activelist.getItems().clear();

        try {
            while (rs.next()) {
                activelist.getItems().add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(
                        3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(
                        6) + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(
                        9));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onComplete(ActionEvent event) throws SQLException {

        if(idtext.getText().isEmpty())
        {
            labeltext.setTextFill(Paint.valueOf("RED"));
            labeltext.setText("Please enter a booking id");
        }
        else
        {
            Admin admin = new Admin();
            boolean flag = admin.completeBooking(Integer.parseInt(idtext.getText()));
            int extractedid = admin.extractLaborID(Integer.parseInt(idtext.getText()));

            boolean flag1 = admin.updateStatus(extractedid, "Available");

            boolean flag3 = admin.updateBookingCount(extractedid);

            boolean flag2 = admin.deleteBooking(Integer.parseInt(idtext.getText()));

            if(flag)
            {
                labeltext.setTextFill(Paint.valueOf("GREEN"));
                labeltext.setText("Booking completed successfully");

                ResultSet rs = admin.viewBooking();

                activelist.getItems().clear();

                try {
                    while (rs.next()) {
                        activelist.getItems().add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(
                                3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(
                                6) + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(
                                9));
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            else
            {
                labeltext.setTextFill(Paint.valueOf("RED"));
                labeltext.setText("Booking not completed");
            }
        }

    }


    public void onDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("navigatior.fxml"));
        Stage stage = (Stage) labeltext.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}