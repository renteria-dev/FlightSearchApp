/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

import java.util.Map;

/**
 *
 * @author luis.renteria
 */
public class Dictionaries {

    private Map<String, Location> locations;
    private Map<String, String> aircrafts;
    private Map<String, String> carriers;

    public Dictionaries(Map<String, Location> locations, Map<String, String> aircrafts, Map<String, String> carriers) {
        this.locations = locations;
        this.aircrafts = aircrafts;
        this.carriers = carriers;
    }

    public Dictionaries() {
    }

    public Map<String, Location> getLocations() {
        return locations;
    }

    public void setLocations(Map<String, Location> locations) {
        this.locations = locations;
    }

    public Map<String, String> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(Map<String, String> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public Map<String, String> getCarriers() {
        return carriers;
    }

    public void setCarriers(Map<String, String> carriers) {
        this.carriers = carriers;
    }

    @Override
    public String toString() {
        return "Dictionaries{" + "locations=" + locations + ", aircrafts=" + aircrafts + ", carriers=" + carriers + '}';
    }

}
