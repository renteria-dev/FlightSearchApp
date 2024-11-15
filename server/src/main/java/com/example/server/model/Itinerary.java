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
public class Itinerary {

    public Itinerary() {
    }

    private String duration;
    @JsonDeserialize(using = SafeDeserializer.class)
    private Segment[] segments;

    public Itinerary(String duration, Segment[] segments) {
        this.duration = duration;
        this.segments = segments;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Segment[] getSegments() {
        return segments;
    }

    public void setSegments(Segment[] segments) {
        this.segments = segments;
    }

    @Override
    public String toString() {
        return "Itinerary{" + "duration=" + duration + ", segments=" + segments + '}';
    }

}
