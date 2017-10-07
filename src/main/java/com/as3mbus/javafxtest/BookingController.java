package com.as3mbus.javafxtest;

import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.time.LocalDate;
import java.io.IOException;

public class BookingController extends AnchorPane {
    @FXML
    private Label BookingDate;

    public BookingController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Booking Information.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        LocalDate Today = LocalDate.now();
        BookingDate.setText(""+Today.getDayOfMonth()+"/"+Today.getMonth().toString()+"/"+Today.getYear());
    }

    @FXML
    private void nextPage() {

        Stage stage = (Stage) this.getScene().getWindow();
        RouteController route = new RouteController();
        route.PoReceiptField.setText("Testing");
        stage.setScene(new Scene(route));

    }
}