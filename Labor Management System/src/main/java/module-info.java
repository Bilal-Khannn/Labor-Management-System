module com.example.sdaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sdaproject to javafx.fxml;
    exports com.example.sdaproject;
}