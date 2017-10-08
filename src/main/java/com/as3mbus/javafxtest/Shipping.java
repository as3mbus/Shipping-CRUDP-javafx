package com.as3mbus.javafxtest;

import java.lang.String;
import java.lang.Thread.State;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Shipping
 */
public class Shipping {
    public String bookingNumber, From, ShipperName, ShipperAddress, ConsigneeName, ConsigneeAddress, PlaceOfReceipt,
            PortOfLoading, VesselOrVoyage, TransShipmentPort, IntendedVesserlOrVoyage, PortOfDischarge,
            FinalDestination, VolumeCount, Commodity, StuffingPlace;

    public LocalDate BookingDate, VesselETD, VesselETA, IVesselETD, IVesselETA, DischargeETA, StuffingDate;

    public enum CargoSize {
        Twenty, Forty
    };

    public enum CargoType {
        GP, HC
    };

    public enum Freight {
        Collect, Prepaid
    }

    CargoType CargoType;
    CargoSize CargoSize;
    Freight Freight;

    public String BookingInformation() {
        return (this.bookingNumber + "\n" + this.BookingDate + "\n" + this.From + "\n" + this.ShipperName + "\n"
                + this.ShipperAddress + "\n" + this.ConsigneeName + "\n" + this.ConsigneeAddress);
    }
    public static LocalDate parseDateString(String s){
        final DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MMMMM/yyyy");
        final LocalDate dt = dtf.parseLocalDate(s);
        return dt;
    }
    public static LocalDate parseBasicDateString(String s){
        final DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
        final LocalDate dt = dtf.parseLocalDate(s);
        return dt;
    }
    public static String DateString(LocalDate ld){
        final DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MMMM/yyyy");
        final String s = ld.toString(dtf);
        return s;
    }

    public Shipping() {

    }
}