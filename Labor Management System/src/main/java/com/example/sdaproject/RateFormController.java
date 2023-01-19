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

public class RateFormController implements Initializable {

    @FXML
    private ListView<String> activelist;

    @FXML
    private TextField bookingidtext;

    @FXML
    private Button dashboardbutton;

    @FXML
    private Label labeltext;

    @FXML
    private TextField laboridtext;

    @FXML
    private TextField nametext;

    @FXML
    private RadioButton negradbutton;

    @FXML
    private RadioButton posradbutton;

    @FXML
    private Button ratebutton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Admin admin = new Admin();
        ResultSet rs = admin.viewCompletedBooking();

        activelist.getItems().clear();

        try {
            while (rs.next()) {
                activelist.getItems().add(rs.getString(1) + "           " + rs.getString(2) + "          " + rs.getString(
                        3) + "           " + rs.getString(4) + "          "  + rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void onDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("navigatior.fxml"));
        Stage stage = (Stage) dashboardbutton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void onRate(ActionEvent event) throws IOException {


        int rating = 0;

        if(posradbutton.isSelected() && negradbutton.isSelected())
        {
            labeltext.setTextFill(Paint.valueOf("RED"));
            labeltext.setText("Please select only one option");
        }
        else if (laboridtext.getText().isEmpty() || nametext.getText().isEmpty() || bookingidtext.getText().isEmpty()) {
            labeltext.setTextFill(Paint.valueOf("RED"));
            labeltext.setText("Please fill all the fields");
        }
        else {
            Admin admin = new Admin();
            int bookingid = Integer.parseInt(bookingidtext.getText());
            int laborid = Integer.parseInt(laboridtext.getText());
            String name = nametext.getText();

            if(posradbutton.isSelected())
            {
                rating = 1;
            } else if (negradbutton.isSelected()) {
                rating = -1;
            }

            int flag = admin.confirmBooking(laborid, name, bookingid);

            if(flag == 1)
            {
                boolean xd = admin.updateRating(laborid, rating);
                labeltext.setText("Rating updated Successfully");
                labeltext.setTextFill(Paint.valueOf("GREEN"));
            }
            else
            {
                labeltext.setText("Rating updation Failed");
                labeltext.setTextFill(Paint.valueOf("RED"));
            }


        }

    }

}
