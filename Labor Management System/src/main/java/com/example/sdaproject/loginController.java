package com.example.sdaproject;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;

public class loginController {
    @FXML
    Label loginlabel;

    @FXML
    TextField usertext;

    @FXML
    TextField passtext;

    public void loginClick(ActionEvent event) throws IOException {
        String user = usertext.getText();
        String pass = passtext.getText();

        Admin admin = new Admin();

        boolean flag = admin.adminVerify(user, pass);

        if(flag)
        {
            usertext.setStyle("-fx-border-color: gray;");
            passtext.setStyle("-fx-border-color: gray;");
            loginlabel.setTextFill(Paint.valueOf("GREEN"));
            loginlabel.setText("Login Successful!");

            //Load the next scene upon successful verification
            Parent root = FXMLLoader.load(getClass().getResource("navigatior.fxml"));
            Stage stage = (Stage) loginlabel.getScene().getWindow();
            stage.setScene(new Scene(root));

        }
        else
        {
            usertext.setStyle("-fx-border-color: red;");
            passtext.setStyle("-fx-border-color: red;");
            loginlabel.setTextFill(Paint.valueOf("RED"));
            loginlabel.setText("Please check your password and account name and try again.\n");
        }
    }

}