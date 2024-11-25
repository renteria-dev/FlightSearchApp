/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

import java.util.List;

/**
 *
 * @author luis.renteria
 */
public class FlightsDTO {

    private String flightId;
    private String departureTime;
    private String arrivalTime;
    private String departureAirportName;
    private String departureAirportCode;
    private String arrivalAirportName;
    private String arrivalAirportCode;
    private String airlineName;
    private String airlineCode;
    private String operatingAirlineName;
    private String operatingAirlineCode;
    private String duration;
    private String totalPrice;
    private String currency;
    private boolean hasStops;
    private Price price;
    private List<Itinerary> itineraries;
    private List<TravelerPricing> travelerPricings;

    public FlightsDTO(String flightId, String departureTime, String arrivalTime, String departureAirportName, String departureAirportCode, String arrivalAirportName, String arrivalAirportCode, String airlineName, String airlineCode, String operatingAirlineName, String operatingAirlineCode, String duration, String totalPrice, String currency, boolean hasStops, Price price, List<Itinerary> itineraries, List<TravelerPricing> travelerPricings) {
        this.flightId = flightId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureAirportName = departureAirportName;
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportName = arrivalAirportName;
        this.arrivalAirportCode = arrivalAirportCode;
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
        this.operatingAirlineName = operatingAirlineName;
        this.operatingAirlineCode = operatingAirlineCode;
        this.duration = duration;
        this.totalPrice = totalPrice;
        this.currency = currency;
        this.hasStops = hasStops;
        this.price = price;
        this.itineraries = itineraries;
        this.travelerPricings = travelerPricings;
    }

    

    

    public FlightsDTO() {
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getOperatingAirlineName() {
        return operatingAirlineName;
    }

    public void setOperatingAirlineName(String operatingAirlineName) {
        this.operatingAirlineName = operatingAirlineName;
    }

    public String getOperatingAirlineCode() {
        return operatingAirlineCode;
    }

    public void setOperatingAirlineCode(String operatingAirlineCode) {
        this.operatingAirlineCode = operatingAirlineCode;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isHasStops() {
        return hasStops;
    }

    public void setHasStops(boolean hasStops) {
        this.hasStops = hasStops;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public List<TravelerPricing> getTravelerPricings() {
        return travelerPricings;
    }

    public void setTravelerPricings(List<TravelerPricing> travelerPricings) {
        this.travelerPricings = travelerPricings;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
    
    
    

    @Override
    public String toString() {
        return "FlightsDTO{" + "flightId=" + flightId + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", departureAirportName=" + departureAirportName + ", departureAirportCode=" + departureAirportCode + ", arrivalAirportName=" + arrivalAirportName + ", arrivalAirportCode=" + arrivalAirportCode + ", airlineName=" + airlineName + ", airlineCode=" + airlineCode + ", operatingAirlineName=" + operatingAirlineName + ", operatingAirlineCode=" + operatingAirlineCode + ", duration=" + duration + ", totalPrice=" + totalPrice + ", hasStops=" + hasStops + ", itineraries=" + itineraries + ", travelerPricings=" + travelerPricings + '}';
    }

}
