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
public class Location {

    public Location() {
    }

    private String iataCode;
    private String name;

    private String cityName;
    private String countryName;

    private String terminal;
    private String at;

    public Location(String iataCode, String name, String cityName, String countryName) {
        this.iataCode = iataCode;
        this.name = name;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public Location(String iataCode, String terminal, String at) {
        this.iataCode = iataCode;
        this.terminal = terminal;
        this.at = at;
    }

    public Location(String asText, String asText0) {
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Location{" + "iataCode=" + iataCode + ", name=" + name + ", cityName=" + cityName + ", countryName=" + countryName + ", terminal=" + terminal + ", at=" + at + '}';
    }

}
