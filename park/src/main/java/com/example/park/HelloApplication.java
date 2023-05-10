package com.example.park;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage stg;

    @Override
    public void start(Stage firststage) throws IOException {
        stg = firststage;
        firststage.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        firststage.setTitle("Parking Management");
        firststage.setScene(scene);
        firststage.show();
    }

    public void changeScene(String fxml,int width,int height,boolean resize) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene mainmenu = new Scene(fxmlLoader.load(),width,height);
        stg.setScene(mainmenu);
        stg.setResizable(resize);
    }

    public static void main(String[] args) {
        launch();
    }
}