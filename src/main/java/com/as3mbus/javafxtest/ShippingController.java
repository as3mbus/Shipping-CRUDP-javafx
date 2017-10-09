package com.as3mbus.javafxtest;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * ShippingInstruction
 */
public class ShippingController {
    private BookingController bControl;
    private Stage shipStage;

    public ShippingController() {
        shipStage = new Stage();
        bControl = new BookingController();
        shipStage.setScene(new Scene(bControl));
        shipStage.show();
    }

}