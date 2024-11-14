/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

/**
 *
 * @author luis.renteria
 */
public class Arrival {

    private String iataCode;
    private String at;  // Fecha y hora de llegada o salida

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    // Getters and Setters
    public Arrival(String iataCode, String at) {
        this.iataCode = iataCode;
        this.at = at;
    }
}
