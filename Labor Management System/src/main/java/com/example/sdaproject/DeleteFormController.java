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

public class DeleteFormController {
    @FXML
    ListView<String> laborlist;
    @FXML
    Label labeltext;

    @FXML
    TextField idtext;

    @FXML
    Button deletebutton;

    @FXML
    Button viewlaborbutton;

    //onviewlabour button click call onviewlabout

    public void onViewLabor(ActionEvent event)
    {
        laborlist.getItems().clear();
        Admin admin = new Admin();
        ResultSet rs  = admin.viewLabor();
        try {
            while (rs.next()) {
                laborlist.getItems().add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void onDelete(ActionEvent event)
    {
        Admin admin = new Admin();

        int id = Integer.parseInt(idtext.getText());

        boolean flag = admin.deleteLabor(id);

        if(flag)
        {
            labeltext.setTextFill(Paint.valueOf("GREEN"));
            labeltext.setText("Deletion Successful!");
        }
        else
        {
            labeltext.setTextFill(Paint.valueOf("RED"));
            labeltext.setText("No labor associated with entered id!");
        }
    }

    public void onDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("navigatior.fxml"));
        Stage stage = (Stage) labeltext.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}