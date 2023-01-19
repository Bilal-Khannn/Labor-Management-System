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
import java.util.ResourceBundle;

public class ReportFormController implements Initializable {

    @FXML
    private ListView<String> activelist;

    @FXML
    private TextField bookingidtext;

    @FXML
    private TextField customertext;

    @FXML
    private Button dashboardbutton;

    @FXML
    private Label labeltext;

    @FXML
    private TextField laboridtext;

    @FXML
    private Button reportbutton;


    public void onDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("navigatior.fxml"));
        Stage stage = (Stage) dashboardbutton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

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

    public void onReport(ActionEvent event)
    {
        Admin admin = new Admin();

        if(bookingidtext.getText().isEmpty() || customertext.getText().isEmpty() || laboridtext.getText().isEmpty())
        {
            labeltext.setText("Please fill all the fields");
            labeltext.setTextFill(Paint.valueOf("RED"));
        }
        else
        {
            int bookingid = Integer.parseInt(bookingidtext.getText());
            int laborid = Integer.parseInt(laboridtext.getText());
            String customer = customertext.getText();

            int flag = admin.confirmBooking(laborid, customer, bookingid);

            if(flag == 1)
            {
                boolean xd = admin.updateRating(laborid, -1);
                labeltext.setText("Report updated Successfully");
                labeltext.setTextFill(Paint.valueOf("GREEN"));
            }
            else
            {
                labeltext.setText("Report updation Failed");
                labeltext.setTextFill(Paint.valueOf("RED"));
            }
        }
    }
}
