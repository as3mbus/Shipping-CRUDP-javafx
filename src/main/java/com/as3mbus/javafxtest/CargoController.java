package com.as3mbus.javafxtest;

import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

public class CargoController extends AnchorPane {
    

    public CargoController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Cargo Trucking Information.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    private void prevPage(){
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(new Scene(new RouteController()));
        
    }
}