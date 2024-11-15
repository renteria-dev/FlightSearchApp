/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author luis.renteria
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Amenity {

    private String amenityType;
    private String description;
    private boolean isChargeable;

    public Amenity() {
    }

    public Amenity(String amenityType, String description, boolean isChargeable) {
        this.amenityType = amenityType;
        this.description = description;
        this.isChargeable = isChargeable;
    }

    public String getAmenityType() {
        return amenityType;
    }

    public void setAmenityType(String amenityType) {
        this.amenityType = amenityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsChargeable() {
        return isChargeable;
    }

    public void setIsChargeable(boolean isChargeable) {
        this.isChargeable = isChargeable;
    }

    @Override
    public String toString() {
        return "Amenity{" + "amenityType=" + amenityType + ", description=" + description + ", isChargeable=" + isChargeable + '}';
    }

}
