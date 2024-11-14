/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

/**
 *
 * @author luis.renteria
 */
public class TravelerPricing {

    private String travelerId;
    private Price price;
    private FareDetailsBySegment fareDetailsBySegment;

    public TravelerPricing(String travelerId, Price price, FareDetailsBySegment fareDetailsBySegment) {
        this.travelerId = travelerId;
        this.price = price;
        this.fareDetailsBySegment = fareDetailsBySegment;
    }

    public String getTravelerId() {
        return travelerId;
    }

    public void setTravelerId(String travelerId) {
        this.travelerId = travelerId;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public FareDetailsBySegment getFareDetailsBySegment() {
        return fareDetailsBySegment;
    }

    public void setFareDetailsBySegment(FareDetailsBySegment fareDetailsBySegment) {
        this.fareDetailsBySegment = fareDetailsBySegment;
    }
    
    

}
