package com.as3mbus.javafxtest;

import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;


public class RouteController extends AnchorPane {
    Shipping ActiveShipment = new Shipping();
    @FXML
    TextField PoReceiptField;
    @FXML
    private DatePicker VesselETD;

    public RouteController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Route Information.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    private void nextPage(){
        VesselETD.setValue(LocalDate.now());
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(new Scene(new CargoController()));
        
    }
    @FXML
    private void prevPage(){
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(new Scene(new BookingController()));
        
    }
}