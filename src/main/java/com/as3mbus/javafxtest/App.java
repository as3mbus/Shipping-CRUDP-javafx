package com.as3mbus.javafxtest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

    //basic java fx launch argument using custom controller for fxml
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // ShippingController shippControl =  new ShippingController();
        primaryStage.setScene(new Scene (new ShippingController()));
        primaryStage.show();
    }
}
