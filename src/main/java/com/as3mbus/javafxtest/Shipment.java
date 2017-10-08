package com.as3mbus.javafxtest;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

/**
 * Shipment
 */
public class Shipment {
    public String bookingNumber, From, ShipperName, ShipperAddress, ConsigneeName, ConsigneeAddress, PlaceOfReceipt,
            PortOfLoading, VesselOrVoyage, TransShipmentPort, IntendedVesserlOrVoyage, PortOfDischarge,
            FinalDestination, Commodity, StuffingPlace;
    public int VolumeCont;
    public LocalDate BookingDate, VesselETD, VesselETA, IVesselETD, IVesselETA, DischargeETA, StuffingDate;

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/seaport";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    public enum CargoSize {
        Twenty("20'"), Forty("40'");
        public final String name;

        private CargoSize(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            // (otherName == null) check is not needed because name.equals(null) returns false 
            return name.equals(otherName);
        }


    };

    public static enum CargoType {
        GP, HC
    };

    public static enum Freight {
        Collect, Prepaid
    }

    CargoType CargoType;
    CargoSize CargoSize;
    Freight Freight;

    public String BookingInformation() {
        return (this.bookingNumber + "\n" + this.BookingDate + "\n" + this.From + "\n" + this.ShipperName + "\n"
                + this.ShipperAddress + "\n" + this.ConsigneeName + "\n" + this.ConsigneeAddress);
    }

    public String toString() {
        return "Booking Number : " + this.bookingNumber + "\n" + "Booking Date : " + this.BookingDate + "\n" + "From : "
                + this.From + "\n" + "Shipper Name : " + this.ShipperName + "\n" + "Shipper Address : "
                + this.ShipperAddress + "\n" + "Consignee Name : " + this.ConsigneeName + "\n" + "Consignee Address : "
                + this.ConsigneeAddress + "\n" + "Place Of Receipt : " + this.PlaceOfReceipt + "\n"
                + "Port Of Loading : " + this.PortOfLoading + "\n" + "Vessel/Voyage : " + this.VesselOrVoyage + "\n"
                + "VesselETD : " + this.VesselETD + "\n" + "Vessel ETA : " + this.VesselETA + "\n"
                + "TransShipment Port : " + this.TransShipmentPort + "\n" + "Intended Vessel/Voyage : "
                + this.IntendedVesserlOrVoyage + "\n" + "Intended Vessel ETA : " + this.IVesselETA + "\n"
                + "Intended Vessel ETD : " + this.IVesselETD + "\n" + "Port Of Discharge : " + this.PortOfDischarge
                + "\n" + "Discharge ETA: " + this.DischargeETA + "\n" + "Final Destination : " + this.FinalDestination
                + "\n" + "Cargo Size : " + this.CargoSize + "\n" + "Cargo Type : " + this.CargoType + "\n"
                + "Volume Content : " + this.VolumeCont + "\n" + "Commodity : " + this.Commodity + "\n" + "Freight : "
                + this.Freight + "\n" + "Sruffing Place : " + this.StuffingPlace + "\n" + "StuffingDate : "
                + this.StuffingDate;
    }

    public static LocalDate parseDateString(String s) {
        final DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MMMMM/yyyy");
        final LocalDate dt = dtf.parseLocalDate(s);
        return dt;
    }

    public static LocalDate parseBasicDateString(String s) {
        final DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
        final LocalDate dt = dtf.parseLocalDate(s);
        return dt;
    }

    public static String DateString(LocalDate ld) {
        final DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MMMM/yyyy");
        final String s = ld.toString(dtf);
        return s;
    }

    public Shipment() {

    }

    public void viewPdf() {

    }

    public void printIText() {
        try {

            File temp = File.createTempFile("tester", ".pdf");
            temp.getParentFile().mkdirs();
            System.out.println(temp.getAbsolutePath());
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(temp.getAbsolutePath()));
            Document doc = new Document(pdfDoc);

            doc.setTextAlignment(TextAlignment.CENTER);
            doc.add(new Paragraph().add(new Text("Shipping Instruction").setFontSize(28)));
            doc.setTextAlignment(TextAlignment.JUSTIFIED);
            Table table = new Table(4);
            Cell cell = new Cell(2, 2).add("Shipper : \n\t" + ShipperName + "\n\t" + ShipperAddress);
            table.addCell(cell);
            cell = new Cell(1, 2).add("Booking Number : " + bookingNumber);
            table.addCell(cell);
            cell = new Cell(3, 2).add("Logo Here");
            table.addCell(cell);
            cell = new Cell(1, 2).add("Consignee : \n\t" + ConsigneeName + "\n\t" + ConsigneeAddress);
            table.addCell(cell);
            cell = new Cell(1, 2).add("Notify Party : \n\t" + From);
            table.addCell(cell);
            cell = new Cell().add("Vessel : \n\t" + VesselOrVoyage);
            table.addCell(cell);
            cell = new Cell().add("ETD : \n\t" + VesselETD);
            table.addCell(cell);
            cell = new Cell().add("Port Of Loading : \n\t" + PortOfLoading);
            table.addCell(cell);
            cell = new Cell().add("Place Of Receipt : \n\t" + PlaceOfReceipt);
            table.addCell(cell);
            cell = new Cell().add("Intended Vessel : \n\t" + IntendedVesserlOrVoyage);
            table.addCell(cell);
            cell = new Cell().add("ETA : \n\t" + VesselETA);
            table.addCell(cell);
            cell = new Cell().add("Port Of Discharge : \n\t" + PortOfDischarge);
            table.addCell(cell);
            cell = new Cell().add("Final Destination : \n\t" + FinalDestination);
            table.addCell(cell);
            cell = new Cell(1, 4).add("Commodity : \n\t" + Commodity + "\n" + VolumeCont + " x " + CargoSize + " "
                    + CargoType + "\n *** Freight " + Freight + " ***");
            table.addCell(cell);
            cell = new Cell(1, 4).add("Stuffing Place : \n\t" + StuffingPlace);
            table.addCell(cell);
            cell = new Cell(1, 4).add("Booking Date : \n\t" + BookingDate);
            table.addCell(cell);
            doc.add(table);
            doc.close();

        } catch (Exception e) {
            //TODO: handle exception
        }

    }

    public void insertDatabase() {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
            String sql = "INSERT INTO SHIPMENT "
                    + "VALUES (  '" + this.bookingNumber
                    + "' , '" + this.BookingDate
                    + "' , '"+this.From
                    + "' , '"+this.ShipperName
                    + "' , '"+this.ShipperAddress
                    + "' , '"+this.ConsigneeName
                    + "' , '"+this.ConsigneeAddress
                    + "' , '"+this.PlaceOfReceipt
                    + "' , '"+this.PortOfLoading
                    + "' , '"+this.VesselOrVoyage
                    + "' , '"+this.VesselETD
                    + "' , '"+this.VesselETA
                    + "' , '"+this.TransShipmentPort 
                    + "' , '"+this.IntendedVesserlOrVoyage
                    + "' , '"+this.IVesselETD
                    + "' , '"+this.IVesselETA
                    + "' , '"+this.PortOfDischarge
                    + "' , '"+this.DischargeETA
                    + "' , '"+this.FinalDestination
                    + "' , '"+this.CargoSize
                    + "' , '"+this.CargoType
                    + "' ,  "+this.VolumeCont
                    + "  , '"+this.Commodity
                    + "' , '"+this.Freight
                    + "' , '"+this.StuffingPlace
                    + "' , '"+this.StuffingDate+"');";
            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");
            
            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try {
                    if (stmt!=null)
                        conn.close();
                } catch (SQLException se) {
                } // do nothing
                try {
                    if (conn!=null)
                        conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                } //end finally try
            } //end try
            
    }

    public void previewFile(String path) {
        try {

            File pdfFile = new File(path);
            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }

            } else {
                System.out.println("File is not exists!");
            }

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}