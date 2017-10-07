package com.as3mbus.javafxtest;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * ShippingInstruction
 */
public class ShippingController {
    private BookingController bControl;
    private CargoController cControl;
    private Stage shipStage;

    public ShippingController() {
        shipStage = new Stage();
        bControl = new BookingController();
        cControl = new CargoController();
        shipStage.setScene(new Scene(bControl));
        shipStage.show();
    }

}