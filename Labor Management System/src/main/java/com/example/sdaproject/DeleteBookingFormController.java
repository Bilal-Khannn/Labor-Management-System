package com.example.sdaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteBookingFormController {

    @FXML
    private Button cancelbutton;

    @FXML
    private TextField idtext;

    @FXML
    private Label labeltext;

    @FXML
    private ListView<String> laborlist;

    @FXML
    private Button viewbookingbutton;

    public void onCancel(ActionEvent event) throws SQLException {
        if(idtext.getText().isEmpty())
        {
            labeltext.setTextFill(Paint.valueOf("RED"));
            labeltext.setText("Please enter a booking id");
        }
        else
        {
            int id = Integer.parseInt(idtext.getText());
            Admin admin = new Admin();
            int extractedid = admin.extractLaborID(id);
            admin.updateStatus(extractedid, "Available");
            boolean flag = admin.deleteBooking(id);

            ResultSet rs = admin.viewBooking();

            if(flag)
            {
                labeltext.setTextFill(Paint.valueOf("GREEN"));
                labeltext.setText("Booking cancelled successfully");
            }
            else
            {
                labeltext.setTextFill(Paint.valueOf("RED"));
                labeltext.setText("Booking not cancelled");
            }

        }
    }

    public void onViewBooking(ActionEvent event)
    {
        laborlist.getItems().clear();
        Admin admin = new Admin();
        ResultSet rs  = admin.viewBooking();
        try {
            while (rs.next()) {
                laborlist.getItems().add(rs.getString(1) + "     " + rs.getString(2) + "     " + rs.getString(3) + " " +
                        "    " + rs.getString(4) + "     " + rs.getString(5) + "     " + rs.getString(6) + "     " + rs.getString(7) + "     " + rs.getString(8) + "     " + rs.getString(9));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void onDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("navigatior.fxml"));
        Stage stage = (Stage) labeltext.getScene().getWindow();
        stage.setScene(new Scene(root));
    }


}