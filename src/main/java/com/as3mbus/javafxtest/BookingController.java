package com.as3mbus.javafxtest;

import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import java.io.IOException;

import org.h2.command.dml.Insert;

public class BookingController extends AnchorPane {
    Shipment ActiveShipment;
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
    boolean Insert = true;

    public BookingController() {
        ActiveShipment = new Shipment();

        //basic loading for fxml custom controller
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Booking Information.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Insert = true;
        BookingNumberLabel.setText(ActiveShipment.BookingNumber);
        BookingDateLabel.setText(Shipment.DateString(org.joda.time.LocalDate.now()));
    }

    public BookingController(Shipment Shipment) {
        this();
        LoadShipment(Shipment);
        Insert= false;
    }
    public BookingController(Shipment Shipment, boolean insert) {
        this();
        LoadShipment(Shipment);
        Insert= insert;
    }

    public void LoadShipment(Shipment shipping) {
        ActiveShipment = shipping;
        BookingNumberLabel.setText(shipping.BookingNumber);
        try {
            BookingDateLabel.setText(Shipment.DateString(shipping.BookingDate));
        } catch (Exception e) {
            //TODO: handle exception
        }
        FromField.setText(shipping.From);
        ShipperName.setText(shipping.ShipperName);
        ShipperAddress.setText(shipping.ShipperAddress);
        ConsigneeName.setText(shipping.ConsigneeName);
        ConsigneeAddress.setText(shipping.ConsigneeAddress);
    }

    public void SaveShipment(Shipment shipment) {
        shipment.BookingNumber = BookingNumberLabel.getText();
        shipment.BookingDate = Shipment.parseDateString(BookingDateLabel.getText());
        shipment.From = FromField.getText();
        shipment.ShipperName = ShipperName.getText();
        shipment.ShipperAddress = ShipperAddress.getText();
        shipment.ConsigneeName = ConsigneeName.getText();
        shipment.ConsigneeAddress = ConsigneeAddress.getText();
        System.out.println(shipment.toString());

    }

    @FXML
    private void nextPage() {
        SaveShipment(ActiveShipment);

        //calling another scene controller and place it as new scene  
        Stage stage = (Stage) this.getScene().getWindow();
        RouteController route = new RouteController(ActiveShipment,Insert);
        stage.setScene(new Scene(route));
        stage.centerOnScreen();

    }
}