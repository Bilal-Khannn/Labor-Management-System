package com.example.sdaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateFormController {
    @FXML
    private TextField addresstext;

    @FXML
    private TextField bookingtext;

    @FXML
    private TextField cnictext;

    @FXML
    private TextField idtext;

    @FXML
    private Label labeltext;

    @FXML
    private TextField nametext;

    @FXML
    private TextField phonetext;

    @FXML
    private TextField ratingtext;

    @FXML
    private Button searchbutton;

    @FXML
    private TextField statustext;

    @FXML
    private TextField typetext;

    @FXML
    private Button updatebutton;

    @FXML
    private Label updatelabeltext;

    public void onSearch(ActionEvent event) throws SQLException {
        Admin admin = new Admin();
        ResultSet rs = admin.searchlabor(Integer.parseInt(idtext.getText()));

        if(!rs.isBeforeFirst())
        {
            labeltext.setTextFill(Paint.valueOf("RED"));
            labeltext.setText("No record found!");

            nametext.setText("");
            phonetext.setText("");
            cnictext.setText("");
            addresstext.setText("");
            typetext.setText("");
            ratingtext.setText("");
            bookingtext.setText("");
            statustext.setText("");
        }
        else
        {
            labeltext.setText("");
            try {
                while (rs.next()) {
                    nametext.setText(rs.getString(2));
                    phonetext.setText(rs.getString(3));
                    cnictext.setText(rs.getString(4));
                    addresstext.setText(rs.getString(5));
                    typetext.setText(rs.getString(6));
                    ratingtext.setText(rs.getString(7));
                    bookingtext.setText(rs.getString(8));
                    statustext.setText(rs.getString(9));
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

    public void onUpdate(ActionEvent event)
    {
        Admin admin = new Admin();
        if(nametext.getText() == "" || addresstext.getText() == "" || cnictext.getText() == "" || phonetext.getText() == ""
        || typetext.getText() == "" || ratingtext.getText() == "" || bookingtext.getText() == "" || statustext.getText() == "")
        {
            updatelabeltext.setTextFill(Paint.valueOf("RED"));
            updatelabeltext.setText("Update Failed!");
        }
        else
        {
            boolean flag = admin.updatelabor(idtext.getText(), nametext.getText(), addresstext.getText(),
                cnictext.getText(),
                    phonetext.getText(), typetext.getText(), ratingtext.getText(), bookingtext.getText(),
                    statustext.getText());

            if(flag)
            {
                updatelabeltext.setTextFill(Paint.valueOf("GREEN"));
                updatelabeltext.setText("Update Successful!");
            }
            else
            {
                updatelabeltext.setTextFill(Paint.valueOf("RED"));
                updatelabeltext.setText("Update Failed!");
            }
        }
    }

    public void onDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("navigatior.fxml"));
        Stage stage = (Stage) labeltext.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
