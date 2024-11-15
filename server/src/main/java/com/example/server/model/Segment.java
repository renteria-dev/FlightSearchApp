/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

import com.example.server.mapper.SafeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 *
 * @author luis.renteria
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Segment {

    public Segment() {
    }

    @JsonDeserialize(using = SafeDeserializer.class)
    private Arrival departure;

    @JsonDeserialize(using = SafeDeserializer.class)
    private Arrival arrival;
    private String carrierCode;
    private String number;
    @JsonDeserialize(using = SafeDeserializer.class)
    private String aircraft;
    @JsonDeserialize(using = SafeDeserializer.class)
    private String operating;
    private String duration;
    private String id;
    private long numberOfStops;

    public Segment(Arrival departure, Arrival arrival, String carrierCode, String number, String aircraft, String operating, String duration, String id, long numberOfStops) {
        this.departure = departure;
        this.arrival = arrival;
        this.carrierCode = carrierCode;
        this.number = number;
        this.aircraft = aircraft;
        this.operating = operating;
        this.duration = duration;
        this.id = id;
        this.numberOfStops = numberOfStops;
    }

    @JsonSetter("aircraft")  // Se asignará a este setter cuando el campo "aircraft" esté presente en el JSON
    public void setAircraft(JsonNode node) {
        if (node != null && node.has("code")) {
            this.aircraft = node.get("code").asText();  // Extraemos "code" dentro de "aircraft" y lo asignamos a aircraft
        }
    }

    @JsonSetter("operating")  // Se asignará a este setter cuando el campo "aircraft" esté presente en el JSON
    public void setOperating(JsonNode node) {
        if (node != null && node.has("code")) {
            this.aircraft = node.get("code").asText();  // Extraemos "code" dentro de "aircraft" y lo asignamos a aircraft
        }
    }

    public Arrival getDeparture() {
        return departure;
    }

    public void setDeparture(Arrival departure) {
        this.departure = departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getOperating() {
        return operating;
    }

    public void setOperating(String operating) {
        this.operating = operating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(long numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    @Override
    public String toString() {
        return "Segment{" + "departure=" + departure + ", arrival=" + arrival + ", carrierCode=" + carrierCode + ", number=" + number + ", aircraft=" + aircraft + ", operating=" + operating + ", duration=" + duration + ", id=" + id + ", numberOfStops=" + numberOfStops + '}';
    }

}
