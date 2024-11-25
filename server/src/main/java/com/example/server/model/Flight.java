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
public class Flight {

    public Flight() {
    }

    private String id;

    private Price price;

    private Itinerary[] itineraries;

    private TravelerPricing[] travelerPricings;

    public Flight(String id, Itinerary[] itineraries, Price price, TravelerPricing[] travelerPricings) {
        this.id = id;
        this.itineraries = itineraries;
        this.price = price;
        this.travelerPricings = travelerPricings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Itinerary[] getItineraries() {
        return itineraries;
    }

    public void setItineraries(Itinerary[] itineraries) {
        this.itineraries = itineraries;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public TravelerPricing[] getTravelerPricings() {
        return travelerPricings;
    }

    public void setTravelerPricings(TravelerPricing[] travelerPricings) {
        this.travelerPricings = travelerPricings;
    }

    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", price=" + price + ", itineraries=" + itineraries + ", travelerPricings=" + travelerPricings + '}';
    }

}
