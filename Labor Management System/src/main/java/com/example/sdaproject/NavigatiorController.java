package com.example.sdaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigatiorController {

    @FXML
    Button registerbutton;
    public void onRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterForm.fxml"));
        Stage stage = (Stage) registerbutton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void onRemove(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DeleteForm.fxml"));
        Stage stage = (Stage) registerbutton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void onCancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DeleteBookingForm.fxml"));
        Stage stage = (Stage) registerbutton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void onCreate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BookingForm.fxml"));
        Stage stage = (Stage) registerbutton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void onUpdate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UpdateForm.fxml"));
        Stage stage = (Stage) registerbutton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void onReport(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReportForm.fxml"));
        Stage stage = (Stage) registerbutton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void onReward(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RewardForm.fxml"));
        Stage stage = (Stage) registerbutton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void onRate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RateForm.fxml"));
        Stage stage = (Stage) registerbutton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void onComplete(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CompleteBooking.fxml"));
        Stage stage = (Stage) registerbutton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }


}