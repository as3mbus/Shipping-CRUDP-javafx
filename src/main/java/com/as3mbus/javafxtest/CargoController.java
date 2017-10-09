package com.as3mbus.javafxtest;

import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.DatePicker;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;

import com.as3mbus.javafxtest.Shipment.Freight;

import com.as3mbus.javafxtest.Shipment;
import com.as3mbus.javafxtest.Shipment.CargoSize;
import com.as3mbus.javafxtest.Shipment.CargoType;

public class CargoController extends AnchorPane {
    public Shipment ActiveShipment;
    @FXML
    private ToggleGroup size;
    @FXML
    private ToggleGroup type;
    @FXML
    private ToggleGroup freight;
    @FXML
    private TextField VolumeContField;
    @FXML
    private TextArea CommodityArea;
    @FXML
    private TextField StuffingPlaceField;
    @FXML
    private DatePicker StuffingDatePicker;
    boolean Insert = true;

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

    public CargoController(Shipment shipment) {
        this();
        loadShipment(shipment);
    }

    public CargoController(Shipment shipment, boolean insert) {
        this(shipment);
        Insert = insert;
    }

    public void saveShipment(Shipment shipment) {
        try {
            shipment.CargoSize = getSelectedRadioText(size).equalsIgnoreCase("20'") ? CargoSize.Twenty
                    : CargoSize.Forty;
        } catch (Exception e) {
            //TODO: handle exception
        }
        try {
            shipment.CargoType = getSelectedRadioText(type).equalsIgnoreCase("GP") ? CargoType.GP : CargoType.HC;

        } catch (Exception e) {
            //TODO: handle exception
        }
        shipment.VolumeCont = Integer.parseInt(VolumeContField.getText());
        shipment.Commodity = CommodityArea.getText();
        try {
            shipment.Freight = getSelectedRadioText(freight).equalsIgnoreCase("Collect") ? Freight.Collect
                    : Freight.Prepaid;
        } catch (Exception e) {
            //TODO: handle exception
        }
        shipment.StuffingPlace = StuffingPlaceField.getText();
        try {

            shipment.StuffingDate = org.joda.time.LocalDate.parse(StuffingDatePicker.getValue().toString());
            ;
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(shipment.toString());

    }

    public void loadShipment(Shipment shipment) {
        ActiveShipment = shipment;
        try {

            if (shipment.CargoSize.name() == CargoSize.Twenty.name())
                setToggleGroup(size, CargoSize.Twenty.name);

            else
                setToggleGroup(size, CargoSize.Forty.name);

        } catch (Exception e) {
            //TODO: handle exception
        }
        try {
            if (shipment.CargoType.name() == (CargoType.GP.name()))
                setToggleGroup(type, CargoType.GP.name());
            else
                setToggleGroup(type, CargoType.HC.name());

        } catch (Exception e) {
            //TODO: handle exception
        }
        VolumeContField.setText("" + shipment.VolumeCont);
        CommodityArea.setText(shipment.Commodity);
        try {
            if (shipment.Freight.name() == (Freight.Collect.name()))
                setToggleGroup(freight, Freight.Collect.name());
            else
                setToggleGroup(freight, Freight.Prepaid.name());

        } catch (Exception e) {
            //TODO: handle exception
        }
        StuffingPlaceField.setText(shipment.StuffingPlace);
        try {

            StuffingDatePicker.setValue(LocalDate.of(shipment.StuffingDate.getYear(),
                    shipment.StuffingDate.getMonthOfYear(), shipment.StuffingDate.getDayOfMonth()));
        } catch (Exception e) {
            //TODO: handle exception
        }

    }

    @FXML
    private void prevPage() {
        saveShipment(ActiveShipment);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(new Scene(new RouteController(ActiveShipment,Insert)));
    }

    @FXML
    private void Done() {
        saveShipment(ActiveShipment);
        // ActiveShipment.printIText();
        if (Insert)
            ActiveShipment.insertDatabase();
        else 
            ActiveShipment.updateDatabase();
        Stage s = (Stage) this.getScene().getWindow();
        s.getOnCloseRequest().handle(
            new WindowEvent(
                s,
                WindowEvent.WINDOW_CLOSE_REQUEST
            )
        );
        s.close();
    }

    public static String getSelectedRadioText(ToggleGroup group) {
        RadioButton rb = (RadioButton) group.getSelectedToggle();
        return rb.getText();
    }

    public static void setToggleGroup(ToggleGroup group, String s) {
        for (Iterator<Toggle> i = group.getToggles().iterator(); i.hasNext();) {
            RadioButton item = (RadioButton) i.next();
            if (item.getText().equalsIgnoreCase(s)) {
                item.setSelected(true);
                return;
            }
        }
    }
}