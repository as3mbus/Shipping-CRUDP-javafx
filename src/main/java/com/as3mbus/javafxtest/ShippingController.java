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
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.Node;

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
    @FXML
    private void EditShipment(){
        ShipmentData selected = (ShipmentData) ShipmentTable.getSelectionModel().getSelectedItem();
        System.out.println(selected.getBookingID());
        Stage stage = new Stage();
        stage.setTitle("Edit Shipment "+selected.BookingID);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
        
            public void handle(WindowEvent event) {
                ShippingController mainstage =(ShippingController) ShipmentTable.getScene().getRoot();
                mainstage.setDisable(false);
                FilterField.setText("");
                updateTable();
            }
        });
        this.getScene().getRoot().setDisable(true);
        
        stage.setScene(new Scene(new BookingController(new Shipment(selected.getBookingID()),false)));
        stage.show();
        
    }
    @FXML
    private void CreateShipment(){
    
        Stage stage = new Stage();
        stage.setTitle("New Shipment");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
        
            public void handle(WindowEvent event) {
                ShippingController mainstage =(ShippingController) ShipmentTable.getScene().getRoot();
                mainstage.setDisable(false);
                FilterField.setText("");
                updateTable();
            }
        });
        this.getScene().getRoot().setDisable(true);
        
        stage.setScene(new Scene(new BookingController()));
        stage.show();
        
    }
    @FXML
    private void DeleteShipment(){
        
    }
    @FXML
    private void PrintShipment(){

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
            System.out.println("Retrieving records into the table...");
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