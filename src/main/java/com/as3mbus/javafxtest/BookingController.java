package com.as3mbus.javafxtest;

import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import java.time.LocalDate;
import java.io.IOException;

public class BookingController extends AnchorPane {
    Shipping ActiveShipment = new Shipping();
    @FXML
    private Label BookingDateLabel;
    @FXML
    private Label BookingNumberLabel;
    @FXML
    private TextField FromField;
    @FXML
    private TextField ShipperName;
    @FXML
    private TextArea ShipperAddress;
    @FXML
    private TextField ConsigneeName;
    @FXML
    private TextArea ConsigneeAddress;
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
        BookingDateLabel.setText(""+Today.getDayOfMonth()+"/"+Today.getMonth().toString()+"/"+Today.getYear());
    }
    public BookingController(Shipping Shipment) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Booking Information.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        ActiveShipment = Shipment;
    }

    @FXML
    private void nextPage() {

        Stage stage = (Stage) this.getScene().getWindow();
        ActiveShipment.bookingNumber = BookingNumberLabel.getText();
        ActiveShipment.BookingDate =Shipping.parseDateString(BookingDateLabel.getText());
        ActiveShipment.From = FromField.getText();
        ActiveShipment.ShipperName = ShipperName.getText();
        ActiveShipment.ShipperAddress = ShipperAddress.getText();
        ActiveShipment.ConsigneeName = ConsigneeName.getText();
        ActiveShipment.ConsigneeAddress = ConsigneeAddress.getText();
        System.out.println(ActiveShipment.BookingInformation());
        RouteController route = new RouteController();
        route.PoReceiptField.setText("Testing");
        stage.setScene(new Scene(route));

    }
}