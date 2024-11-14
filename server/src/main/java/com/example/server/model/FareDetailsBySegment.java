/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

/**
 *
 * @author luis.renteria
 */
public class FareDetailsBySegment {
    private String segmentID;
    private String cabin;
    private String classe;
    private Amenity[] amenities;

    public FareDetailsBySegment(String segmentID, String cabin, String classe, Amenity[] amenities) {
        this.segmentID = segmentID;
        this.cabin = cabin;
        this.classe = classe;
        this.amenities = amenities;
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Amenity[] getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenity[] amenities) {
        this.amenities = amenities;
    }
    
}
