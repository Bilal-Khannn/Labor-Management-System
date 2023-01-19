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

import java.io.IOException;

public class RegisterFormController {

    Admin admin;              //An object of the controller class
    @FXML
    TextField nametext;

    @FXML
    TextField cnictext;

    @FXML
    TextField typetext;

    @FXML
    TextField addresstext;

    @FXML
    TextField phonetext;
    @FXML
    Label labeltext;

    public void onRegister(ActionEvent event)
    {
        String name = nametext.getText();
        String cnic = cnictext.getText();
        String type = typetext.getText();
        String address = addresstext.getText();
        String phone = phonetext.getText();

        if(name == "" || cnic == "" || type == "" || address == "" || phone == "")
        {
            labeltext.setTextFill(Paint.valueOf("RED"));
            labeltext.setText("Please fill all the fields!");
        }
        else
        {
            admin = new Admin();
            boolean flag = admin.addLabor(name, address, phone, cnic, type);
            if(flag)
            {
                labeltext.setTextFill(Paint.valueOf("GREEN"));
                labeltext.setText("Labor added successfully!");
            }
            else
            {
                labeltext.setTextFill(Paint.valueOf("RED"));
                labeltext.setText("Labor already exists!");
            }
        }

    }

    public void onDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("navigatior.fxml"));
        Stage stage = (Stage) phonetext.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
