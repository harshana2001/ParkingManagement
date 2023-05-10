package com.example.park;

import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SQLcontroller {

    //polymorphism

    @FXML
    public static void createNewVehicle(Car vehiObject){

        DBConnection connection = new DBConnection();
        Connection connection1 = connection.getConnect();
        String queryadd = "INSERT INTO vehicles (VehicleNumber, OwnerID, OwnerName, ChargeRate) VALUES (?,?,?,?)";

        try {

            PreparedStatement statement = connection1.prepareStatement(queryadd);
            statement.setString(1,vehiObject.getVehiNum());
            statement.setString(2,vehiObject.getOwnerId());
            statement.setString(3,vehiObject.getOwnerName());
            statement.setInt(4,vehiObject.getChargeRate());
            statement.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public static void createNewVehicle(Bike vehiObject){
        DBConnection connection = new DBConnection();
        Connection connection1 = connection.getConnect();
        String queryadd = "INSERT INTO vehicles (VehicleNumber, OwnerID, OwnerName, ChargeRate) VALUES (?,?,?,?)";

        try {
            PreparedStatement statement = connection1.prepareStatement(queryadd);
            statement.setString(1,vehiObject.getVehiNum());
            statement.setString(2,vehiObject.getOwnerId());
            statement.setString(3,vehiObject.getOwnerName());
            statement.setInt(4,vehiObject.getChargeRate());
            statement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
