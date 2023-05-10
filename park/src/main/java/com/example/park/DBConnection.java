package com.example.park;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    public static Connection getConnect(){

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkingdb","root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
