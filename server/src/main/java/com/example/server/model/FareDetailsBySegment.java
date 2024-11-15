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
public class FareDetailsBySegment {

    public FareDetailsBySegment() {
    }

    @JsonProperty("segmentId")
    private String segmentID;
    private String cabin;

    private String fareBasis;
    private String brandedFare;
    private String brandedFareLabel;
    @JsonProperty("class")
    private String fareDetailsBySegmentClass;

    private Amenity[] amenities;

    public FareDetailsBySegment(String segmentID, String cabin, Amenity[] amenities, String fareBasis, String brandedFare, String brandedFareLabel, String fareDetailsBySegmentClass) {
        this.segmentID = segmentID;
        this.cabin = cabin;
        this.amenities = amenities;
        this.fareBasis = fareBasis;
        this.brandedFare = brandedFare;
        this.brandedFareLabel = brandedFareLabel;
        this.fareDetailsBySegmentClass = fareDetailsBySegmentClass;
    }

    public String getSegmentID() {
        return segmentID;
    }

    public void setSegmentID(String segmentID) {
        this.segmentID = segmentID;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public Amenity[] getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenity[] amenities) {
        this.amenities = amenities;
    }

    public String getFareBasis() {
        return fareBasis;
    }

    public void setFareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
    }

    public String getBrandedFare() {
        return brandedFare;
    }

    public void setBrandedFare(String brandedFare) {
        this.brandedFare = brandedFare;
    }

    public String getBrandedFareLabel() {
        return brandedFareLabel;
    }

    public void setBrandedFareLabel(String brandedFareLabel) {
        this.brandedFareLabel = brandedFareLabel;
    }

    public String getFareDetailsBySegmentClass() {
        return fareDetailsBySegmentClass;
    }

    public void setFareDetailsBySegmentClass(String fareDetailsBySegmentClass) {
        this.fareDetailsBySegmentClass = fareDetailsBySegmentClass;
    }

    @Override
    public String toString() {
        return "FareDetailsBySegment{" + "segmentID=" + segmentID + ", cabin=" + cabin + ", fareBasis=" + fareBasis + ", brandedFare=" + brandedFare + ", brandedFareLabel=" + brandedFareLabel + ", fareDetailsBySegmentClass=" + fareDetailsBySegmentClass + ", amenities=" + amenities + '}';
    }

}
