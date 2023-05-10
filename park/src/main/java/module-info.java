module com.example.park {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;

    opens com.example.park to javafx.fxml;
    exports com.example.park;
}