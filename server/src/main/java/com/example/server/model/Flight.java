/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

import com.example.server.mapper.SafeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 *
 * @author luis.renteria
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {

    public Flight() {
    }

    private String id;
    //@JsonDeserialize(using = SafeDeserializer.class)
    private Price price;
    @JsonDeserialize(using = SafeDeserializer.class)
    private Itinerary[] itineraries;
    @JsonDeserialize(using = SafeDeserializer.class)
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
