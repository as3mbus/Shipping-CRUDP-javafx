package com.as3mbus.javafxtest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {
    BookingController bookControl;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        bookControl =  new BookingController(new Shipment("SMG002"));
        primaryStage.setScene(new Scene (bookControl));
        primaryStage.show();
    }
}
