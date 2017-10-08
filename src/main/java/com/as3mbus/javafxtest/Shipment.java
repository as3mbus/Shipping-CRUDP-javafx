package com.as3mbus.javafxtest;

import java.lang.String;
import java.lang.Thread.State;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Shipment
 */
public class Shipment {
    public String bookingNumber, From, ShipperName, ShipperAddress, ConsigneeName, ConsigneeAddress, PlaceOfReceipt,
            PortOfLoading, VesselOrVoyage, TransShipmentPort, IntendedVesserlOrVoyage, PortOfDischarge,
            FinalDestination, Commodity, StuffingPlace;
    public int VolumeCont;
    public LocalDate BookingDate, VesselETD, VesselETA, IVesselETD, IVesselETA, DischargeETA, StuffingDate;

    public enum CargoSize {
        Twenty("20'"), Forty("40'");
        private final String name;

        private CargoSize(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            // (otherName == null) check is not needed because name.equals(null) returns false 
            return name.equals(otherName);
        }

        public String toString() {
            return this.name;
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
                + "Intended Vessel ETD : " + this.IVesselETD + "\n" + "Intended Vessel ETA : "
                + this.IVesselETA + "\n" + "Port Of Discharge : " + this.PortOfDischarge + "\n" + "Discharge ETA: "
                + this.DischargeETA + "\n" + "Final Destination : " + this.FinalDestination + "\n" + "Cargo Size : "
                + this.CargoSize + "\n" + "Cargo Type : " + this.CargoType + "\n" + "Volume Content : "
                + this.VolumeCont + "\n" + "Commodity : " + this.Commodity + "\n" + "Freight : " + this.Freight
                + "\n" + "Sruffing Place : " + this.StuffingPlace + "\n" + "StuffingDate : "
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
}