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
public class Flight {

    private List<Itinerary> itineraries;
    private String id;
    private Price price;
    private Dictionaries dictionaries;
    private List<TravelerPricing> travelerPricing;

    public Flight(List<Itinerary> itineraries, String id, Price price, Dictionaries dictionaries, List<TravelerPricing> travelerPricing) {
        this.itineraries = itineraries;
        this.id = id;
        this.price = price;
        this.dictionaries = dictionaries;
        this.travelerPricing = travelerPricing;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Dictionaries getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(Dictionaries dictionaries) {
        this.dictionaries = dictionaries;
    }

    public List<TravelerPricing> getTravelerPricing() {
        return travelerPricing;
    }

    public void setTravelerPricing(List<TravelerPricing> travelerPricing) {
        this.travelerPricing = travelerPricing;
    }

}
