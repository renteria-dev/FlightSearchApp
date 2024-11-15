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
public class Itinerary {

    public Itinerary() {
    }

    private String duration;

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
