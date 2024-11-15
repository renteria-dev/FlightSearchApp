/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.OffsetDateTime;

/**
 *
 * @author luis.renteria
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Arrival {

    private String iataCode;
    private String terminal;

    public Arrival() {
    }
    private OffsetDateTime at;

    public Arrival(String iataCode, String terminal, OffsetDateTime at) {
        this.iataCode = iataCode;
        this.terminal = terminal;
        this.at = at;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public OffsetDateTime getAt() {
        return at;
    }

    public void setAt(OffsetDateTime at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "Arrival{" + "iataCode=" + iataCode + ", terminal=" + terminal + ", at=" + at + '}';
    }

}
