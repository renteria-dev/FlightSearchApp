/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.controller;

import com.example.server.model.FlightsDTO;
import com.example.server.model.Location;
import com.example.server.service.AppService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author luis.renteria
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1")

public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping
    public String status() {
        return "Server Running";
    }

    @GetMapping("/airport")
    public List<Location> getAirportCode(
            @RequestParam(defaultValue = "CITY,AIRPORT") String subtype,
            @RequestParam String keyword) {
        return appService.getAirportCode(subtype, keyword);
    }

    @GetMapping("/flights")
    public List<FlightsDTO> getFlights(
            @RequestParam String departureAitaCode, 
            @RequestParam String arrivalAitaCode,
            @RequestParam String departureDate, 
            @RequestParam String arrivalDate,  
            @RequestParam(required = false) String returnDate,
            @RequestParam int numberAdults, 
            @RequestParam String currencyCode, 
            @RequestParam Boolean nonStop,
            @RequestParam(required = false, defaultValue = "ASC") String sortByPrice,
            @RequestParam(required = false) String sortByDate
            ) {
        return appService.getFlights(
                departureAitaCode, 
                arrivalAitaCode,
                departureDate, 
                arrivalDate,
                returnDate,
                numberAdults,
                currencyCode,
                nonStop,
                sortByPrice,
                sortByDate
                        
        );
    }
}
