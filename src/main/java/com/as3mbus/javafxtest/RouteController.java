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

    public RouteController(Shipping shipment) {
        ActiveShipment = shipment;
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
    private void nextPage() {
        saveShipment(ActiveShipment);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(new Scene(new CargoController()));

    }
    public void saveShipment(Shipping shipment){
        shipment.PlaceOfReceipt = PoReceiptField.getText();
        shipment.PortOfLoading = PoLoadingField.getText();
        shipment.VesselOrVoyage = VesselVoyageField.getText();
        shipment.VesselETD = Shipping.parseBasicDateString(VesselETD.getValue().toString());
        shipment.VesselETA = Shipping.parseBasicDateString(VesselETA.getValue().toString());
        shipment.TransShipmentPort = TransshipmentPortField.getText();
        shipment.IntendedVesserlOrVoyage = IntendedVesselVoyageField.getText();
        shipment.IVesselETA = Shipping.parseBasicDateString(IntendedVesselETA.getValue().toString());
        shipment.IVesselETD = Shipping.parseBasicDateString(IntendedVesselETD.getValue().toString());
        shipment.PortOfDischarge = PoDischargeField.getText();
        shipment.DischargeETA = Shipping.parseBasicDateString(PoDischargeETA.getValue().toString());
        shipment.FinalDestination = FinalDestinationField.getText();
    }
    public void LoadShipment(Shipping shipping) {
        ActiveShipment = shipping;
        PoReceiptField.setText(shipping.PlaceOfReceipt);
        PoLoadingField.setText(shipping.PortOfLoading);
        VesselVoyageField.setText(shipping.VesselOrVoyage);
        VesselETD.setValue(LocalDate.of(shipping.VesselETD.getYear(), shipping.VesselETD.getMonthOfYear(),
                shipping.VesselETD.getDayOfMonth()));
        VesselETA.setValue(LocalDate.of(shipping.VesselETA.getYear(), shipping.VesselETA.getMonthOfYear(),
                shipping.VesselETA.getDayOfMonth()));
        TransshipmentPortField.setText(shipping.TransShipmentPort);
        IntendedVesselETA.setValue(LocalDate.of(shipping.IVesselETA.getYear(), shipping.IVesselETA.getMonthOfYear(),
                shipping.IVesselETA.getDayOfMonth()));
        IntendedVesselETD.setValue(LocalDate.of(shipping.IVesselETD.getYear(), shipping.IVesselETD.getMonthOfYear(),
                shipping.IVesselETD.getDayOfMonth()));
        PoDischargeField.setText(shipping.PortOfDischarge);
        PoDischargeETA.setValue(LocalDate.of(shipping.DischargeETA.getYear(), shipping.DischargeETA.getMonthOfYear(),
                shipping.DischargeETA.getDayOfMonth()));
        FinalDestinationField.setText(shipping.FinalDestination);

    }

    @FXML
    private void prevPage() {
        Stage stage = (Stage) this.getScene().getWindow();
        BookingController bcontrol = new BookingController();
        bcontrol.LoadShipment(ActiveShipment);
        stage.setScene(new Scene(bcontrol));

    }
}