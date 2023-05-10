package com.example.park;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable{

    @FXML
    private TableView VehicleTable;

    @FXML
    private JFXButton btnCheckout;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnNewVehicle;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton BtnCheckout;

    @FXML
    private Label menuName;
    @FXML
    private Label searchMessage;
    @FXML
    private static Label DataEntryMessage;

    @FXML
    private GridPane DashboardTab;
    @FXML
    private GridPane NewVehicleTab;
    @FXML
    private GridPane EditTab;
    @FXML
    private GridPane SearchTab;
    @FXML
    private GridPane CheckoutTab;

    @FXML
    private TextField ownerId;

    @FXML
    private TextField ownerName;

    @FXML
    private TextField vehiNumber;

    @FXML
    private TextField searchText;

    @FXML
    private TextField checkoutNum;

    @FXML
    private TextArea searchResult;

    @FXML
    private TextArea checkoutMessage;

    @FXML
    private ComboBox vehicleType;

    DBConnection connection = new DBConnection();
    Connection connection1 = connection.getConnect();


    @FXML
    private void handleClicks(ActionEvent event){
        if(event.getSource() == btnDashboard){
            menuName.setText("DASHBOARD");
            fetRowList();
            DashboardTab.toFront();
        }
        else if(event.getSource() == btnNewVehicle){
            menuName.setText("NEW VEHICLE");
            NewVehicleTab.toFront();
        }
        else if(event.getSource() == btnSearch){
            menuName.setText("SEARCH");
            SearchTab.toFront();
        }
        else if(event.getSource() == btnCheckout){
            menuName.setText("EXIT VEHICLE");
            CheckoutTab.toFront();
        }
    }

    @FXML
    private void onLogoutButtonClick() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("login.fxml",600,400,false);
    }

    @FXML
    private void onAddButtonCLick(){

        if(vehicleType.getValue().equals("Car")){
            Car vehiObject = new Car(ownerName.getText(),vehiNumber.getText(),ownerId.getText());
            SQLcontroller.createNewVehicle(vehiObject);
        }else if(vehicleType.getValue().equals("Bike")){
            Bike vehiObject = new Bike(ownerName.getText(),vehiNumber.getText(),ownerId.getText());
            SQLcontroller.createNewVehicle(vehiObject);
        }

    }

    @FXML
    private void onSearchButtonCLick(){

        String searchTerm=searchText.getText();
        String querysearch = "SELECT * FROM `vehicles` WHERE `VehicleNumber` ='"+searchTerm+"'";

        try {
            Statement statement = connection1.createStatement();
            ResultSet queryResult = statement.executeQuery(querysearch);
            while (queryResult.next()){
                searchResult.setText(
                        "\nVehicle Number : "+queryResult.getString(1)+"\nOwner ID : "+queryResult.getString(2)+"\nOwner Name : "+queryResult.getString(3)+"\nEntry Time : "+queryResult.getString(5)
                );
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void onCheckoutButtonClick() {
        String searchTerm=checkoutNum.getText();
        String sql = "DELETE FROM `vehicles` WHERE `VehicleNumber` ='"+searchTerm+"'";

        try {
            PreparedStatement statement = connection1.prepareStatement(sql);
            statement.execute();
            checkoutMessage.setText(" Operation Successful!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private ObservableList<ObservableList> data;
    String sql = "SELECT * FROM vehicles";

    //only fetch columns
    private void fetColumnList() {

        try {
            ResultSet rs = connection1.createStatement().executeQuery(sql);

            //SQL FOR SELECTING ALL OF CUSTOMER
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                VehicleTable.getColumns().removeAll(col);
                VehicleTable.getColumns().addAll(col);

                System.out.println("Column [" + i + "] ");

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }

    //fetches rows and data from the list
    private void fetRowList() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection1.createStatement().executeQuery(sql);

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            VehicleTable.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetColumnList();
        fetRowList();
    }
}
