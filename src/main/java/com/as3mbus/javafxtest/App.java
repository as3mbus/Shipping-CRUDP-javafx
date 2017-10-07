package com.as3mbus.javafxtest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import java.net.URL;

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
        bookControl =  new BookingController();
        primaryStage.setScene(new Scene (bookControl));
        primaryStage.show();
    }
}
