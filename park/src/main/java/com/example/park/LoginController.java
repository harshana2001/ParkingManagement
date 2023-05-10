package com.example.park;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label loginMessage;

    @FXML
    protected void onLoginButtonClick() throws IOException {
        loginValidation();
    }

    private void loginValidation() {
        DBConnection connection = new DBConnection();
        Connection connection1 = connection.getConnect();

        String querylogin = "SELECT count(1) FROM users WHERE UserName ='"+username.getText()+"'AND Password ='"+password.getText()+"'";

        try{
            Statement statement = connection1.createStatement();
            ResultSet queryResult = statement.executeQuery(querylogin);

            while (queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    HelloApplication m = new HelloApplication();
                    m.changeScene("main-menu.fxml",960,600,true);
                }
                else {
                    loginMessage.setText("Invalid Login");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCancelButtonClick() {
        System.exit(0);
    }
}
