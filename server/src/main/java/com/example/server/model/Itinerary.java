/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

/**
 *
 * @author luis.renteria
 */
import java.util.List;

public class Itinerary {

    private List<Segment> segments;
    private String duration;

    public Itinerary(List<Segment> segments, String duration) {
        this.segments = segments;
        this.duration = duration;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
