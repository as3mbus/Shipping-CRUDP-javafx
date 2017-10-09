package com.as3mbus.javafxtest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * ShippingController
 */
public class ShippingController extends AnchorPane {
    @FXML
    private TableColumn BookingID;
    @FXML
    private TableColumn ShipperColumn;
    @FXML
    private TableColumn ConsigneeColumn;
    @FXML
    private TableView ShipmentTable;
    @FXML
    private TextField FilterField;
    @FXML
    private void updateTable(){
        ShipmentTable.setItems(shipmentDatas(FilterField.getText()));
    }

    public ObservableList<ShipmentData> shipmentDatas(String keyword) {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:tcp://localhost/~/seaport";

        //  Database credentials
        final String USER = "sa";
        final String PASS = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        ObservableList<ShipmentData> data = FXCollections.observableArrayList();
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            System.out.println("Inserting records into the table...");
            stmt = conn.prepareStatement("SELECT BOOKINGID, SHIPPERNAME, CONSIGNEENAME FROM SHIPMENT WHERE UPPER(BOOKINGID) LIKE UPPER(?) OR UPPER(SHIPPERNAME) LIKE UPPER(?) OR UPPER(CONSIGNEENAME) LIKE UPPER(?);");
            stmt.setString(1, "%"+keyword+"%");
            stmt.setString(2, "%"+keyword+"%");
            stmt.setString(3, "%"+keyword+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                data.add(new ShipmentData(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
            return data;

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            } // do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
        return data;
    }

    public ShippingController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Shipping Information.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        // BookingID.prefWidthProperty().bind(ShipmentTable.widthProperty().multiply(0.2)); // w * 1/4
        // ShipperColumn.prefWidthProperty().bind(ShipmentTable.widthProperty().multiply(0.4)); // w * 1/4
        // ConsigneeColumn.prefWidthProperty().bind(ShipmentTable.widthProperty().multiply(0.4)); // w * 1/4
        BookingID.setCellValueFactory(new PropertyValueFactory<ShipmentData, String>("BookingID"));
        ShipperColumn.setCellValueFactory(new PropertyValueFactory<ShipmentData, String>("Shipper"));
        ConsigneeColumn.setCellValueFactory(new PropertyValueFactory<ShipmentData, String>("Consignee"));
        ShipmentTable.setItems(shipmentDatas(""));
    }

    public static class ShipmentData {

        private final SimpleStringProperty BookingID;
        private final SimpleStringProperty Shipper;
        private final SimpleStringProperty Consignee;

        private ShipmentData(Shipment shipm) {
            this.BookingID = new SimpleStringProperty(shipm.BookingNumber);
            this.Shipper = new SimpleStringProperty(shipm.ShipperName);
            this.Consignee = new SimpleStringProperty(shipm.ConsigneeName);
        }
        private ShipmentData(String bookingID, String shipperN, String consigneeN) {
            this.BookingID = new SimpleStringProperty(bookingID);
            this.Shipper = new SimpleStringProperty(shipperN);
            this.Consignee = new SimpleStringProperty(consigneeN);
        }

        public String getBookingID() {
            return BookingID.get();
        }

        public void setBookingID(String bookingid) {
            BookingID.set(bookingid);
        }

        public String getShipper() {
            return Shipper.get();
        }

        public void setShipper(String shipper) {
            Shipper.set(shipper);
        }

        public String getConsignee() {
            return Consignee.get();
        }

        public void setConsignee(String consignee) {
            Consignee.set(consignee);
        }
    }

}