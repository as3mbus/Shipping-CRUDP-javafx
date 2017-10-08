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
    Shipment ActiveShipment = new Shipment();
    @FXML
    TextField PoReceiptField;
    @FXML
    TextField PoLoadingField;
    @FXML
    TextField VesselVoyageField;
    @FXML
    private DatePicker VesselETD;
    @FXML
    private DatePicker VesselETA;
    @FXML
    TextField TransshipmentPortField;
    @FXML
    TextField IntendedVesselVoyageField;
    @FXML
    private DatePicker IntendedVesselETD;
    @FXML
    private DatePicker IntendedVesselETA;
    @FXML
    TextField PoDischargeField;
    @FXML
    private DatePicker PoDischargeETA;
    @FXML
    TextField FinalDestinationField;

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

    public RouteController(Shipment shipment) {
        this();
        LoadShipment(shipment);
    }

    public void saveShipment(Shipment shipment) {
        shipment.PlaceOfReceipt = PoReceiptField.getText();
        shipment.PortOfLoading = PoLoadingField.getText();
        shipment.VesselOrVoyage = VesselVoyageField.getText();
        try {
            shipment.VesselETD = org.joda.time.LocalDate.parse(VesselETD.getValue().toString());
            shipment.VesselETA = org.joda.time.LocalDate.parse(VesselETA.getValue().toString());
        } catch (Exception e) {
            //TODO: handle exception
        }
        shipment.TransShipmentPort = TransshipmentPortField.getText();
        shipment.IntendedVesserlOrVoyage = IntendedVesselVoyageField.getText();
        try {
            shipment.IVesselETA = org.joda.time.LocalDate.parse(IntendedVesselETA.getValue().toString());
            shipment.IVesselETD = org.joda.time.LocalDate.parse(IntendedVesselETD.getValue().toString());
        } catch (Exception e) {
            //TODO: handle exception
        }
        try {
            shipment.DischargeETA = org.joda.time.LocalDate.parse(PoDischargeETA.getValue().toString());
        } catch (Exception e) {
            //TODO: handle exception
        }
        shipment.PortOfDischarge = PoDischargeField.getText();
        shipment.FinalDestination = FinalDestinationField.getText();
        System.out.println(shipment.toString());
        
    }

    public void LoadShipment(Shipment shipping) {
        ActiveShipment = shipping;
        PoReceiptField.setText(shipping.PlaceOfReceipt);
        PoLoadingField.setText(shipping.PortOfLoading);
        VesselVoyageField.setText(shipping.VesselOrVoyage);
        try {
            VesselETD.setValue(LocalDate.of(shipping.VesselETD.getYear(), shipping.VesselETD.getMonthOfYear(),
                    shipping.VesselETD.getDayOfMonth()));
            VesselETA.setValue(LocalDate.of(shipping.VesselETA.getYear(), shipping.VesselETA.getMonthOfYear(),
                    shipping.VesselETA.getDayOfMonth()));
            TransshipmentPortField.setText(shipping.TransShipmentPort);
            IntendedVesselETA.setValue(LocalDate.of(shipping.IVesselETA.getYear(), shipping.IVesselETA.getMonthOfYear(),
                    shipping.IVesselETA.getDayOfMonth()));
            IntendedVesselETD.setValue(LocalDate.of(shipping.IVesselETD.getYear(), shipping.IVesselETD.getMonthOfYear(),
                    shipping.IVesselETD.getDayOfMonth()));

        } catch (Exception e) {
            //TODO: handle exception
        }
        IntendedVesselVoyageField.setText(shipping.IntendedVesserlOrVoyage);
        PoDischargeField.setText(shipping.PortOfDischarge);
        try {
            PoDischargeETA.setValue(LocalDate.of(shipping.DischargeETA.getYear(),
                    shipping.DischargeETA.getMonthOfYear(), shipping.DischargeETA.getDayOfMonth()));

        } catch (Exception e) {
            //TODO: handle exception
        }
        FinalDestinationField.setText(shipping.FinalDestination);

    }

    @FXML
    private void nextPage() {
        saveShipment(ActiveShipment);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(new Scene(new CargoController(ActiveShipment)));

    }

    @FXML
    private void prevPage() {
        saveShipment(ActiveShipment);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(new Scene(new BookingController(ActiveShipment)));

    }
}