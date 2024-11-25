/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author luis.renteria
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TravelerPricing {

    public TravelerPricing() {
    }

    @JsonProperty("travelerId")
    private String travelerID;
    private String fareOption;
    private String travelerType;

    private TravelerPricingPrice price;

    private FareDetailsBySegment[] fareDetailsBySegment;

    public TravelerPricing(String travelerID, String fareOption, String travelerType, TravelerPricingPrice price, FareDetailsBySegment[] fareDetailsBySegment) {
        this.travelerID = travelerID;
        this.fareOption = fareOption;
        this.travelerType = travelerType;
        this.price = price;
        this.fareDetailsBySegment = fareDetailsBySegment;
    }

    public String getTravelerID() {
        return travelerID;
    }

    public void setTravelerID(String travelerID) {
        this.travelerID = travelerID;
    }

    public String getFareOption() {
        return fareOption;
    }

    public void setFareOption(String fareOption) {
        this.fareOption = fareOption;
    }

    public String getTravelerType() {
        return travelerType;
    }

    public void setTravelerType(String travelerType) {
        this.travelerType = travelerType;
    }

    public TravelerPricingPrice getPrice() {
        return price;
    }

    public void setPrice(TravelerPricingPrice price) {
        this.price = price;
    }

    public FareDetailsBySegment[] getFareDetailsBySegment() {
        return fareDetailsBySegment;
    }

    public void setFareDetailsBySegment(FareDetailsBySegment[] fareDetailsBySegment) {
        this.fareDetailsBySegment = fareDetailsBySegment;
    }

    @Override
    public String toString() {
        return "TravelerPricing{" + "travelerID=" + travelerID + ", fareOption=" + fareOption + ", travelerType=" + travelerType + ", price=" + price + ", fareDetailsBySegment=" + fareDetailsBySegment + '}';
    }

}
