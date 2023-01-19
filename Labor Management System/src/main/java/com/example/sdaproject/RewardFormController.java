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

public class RewardFormController {
    @FXML
    private Button dashboardbutton;

    @FXML
    private TextField idtext;

    @FXML
    private Label labeltext;

    @FXML
    private Button rewardbutton;

    @FXML
    private TextField rewardtext;


    public void onDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("navigatior.fxml"));
        Stage stage = (Stage) dashboardbutton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void onReward(ActionEvent event) throws IOException
    {
        if(idtext.getText().isEmpty() || rewardtext.getText().isEmpty())
        {
            labeltext.setText("Please fill all the fields");
            labeltext.setTextFill(Paint.valueOf("RED"));
        }
        else
        {
            Admin admin = new Admin();
            boolean flag = admin.rewardLabor(Integer.parseInt(idtext.getText()), Integer.parseInt(rewardtext.getText()));
            if(flag)
            {
                labeltext.setText("Reward added successfully");
                labeltext.setTextFill(Paint.valueOf("GREEN"));
            }
            else
            {
                labeltext.setText("Reward not added");
                labeltext.setTextFill(Paint.valueOf("RED"));
            }
        }
    }

}
