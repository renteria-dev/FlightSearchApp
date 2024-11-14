/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

/**
 *
 * @author luis.renteria
 */
public class Amenity {
    private String description;
    private boolean isChargeable;

    public Amenity(String description, boolean isChargeable) {
        this.description = description;
        this.isChargeable = isChargeable;
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
    
}
