/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

/**
 *
 * @author luis.renteria
 */
public class Segment {

    private Location departure;
    private Location arrival;
    private String carrierCode;
    private String number;
    private String aircraftCode;
    private String duration;
    private String numberOfStops;

    private String operatingCarrierCode;

    public Segment(Location departure, Location arrival, String carrierCode, String number, String aircraftCode, String duration, String numberOfStops, String operatingCarrierCode) {
        this.departure = departure;
        this.arrival = arrival;
        this.carrierCode = carrierCode;
        this.number = number;
        this.aircraftCode = aircraftCode;
        this.duration = duration;
        this.numberOfStops = numberOfStops;
        this.operatingCarrierCode = operatingCarrierCode;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getArrival() {
        return arrival;
    }

    public void setArrival(Location arrival) {
        this.arrival = arrival;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(String numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public String getOperatingCarrierCode() {
        return operatingCarrierCode;
    }

    public void setOperatingCarrierCode(String operatingCarrierCode) {
        this.operatingCarrierCode = operatingCarrierCode;
    }

}
