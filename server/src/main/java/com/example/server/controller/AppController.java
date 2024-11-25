/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.controller;

import com.example.server.model.AirportsDTO;
import com.example.server.model.FlightsDTO;
import com.example.server.service.AppService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author luis.renteria
 */
@RestController
@CrossOrigin(origins = "*")
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

    @GetMapping("/airports")
    public AirportsDTO getAirportCode(
            @RequestParam(defaultValue = "AIRPORT") String subtype,
            @RequestParam String keyword) {
        return appService.getAirportCode(subtype, keyword);
    }

    @GetMapping("/flights")
    public List<FlightsDTO> getFlights(
            @RequestParam String departureAitaCode,
            @RequestParam String arrivalAitaCode,
            @RequestParam String departureDate,
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
                returnDate,
                numberAdults,
                currencyCode,
                nonStop,
                sortByPrice,
                sortByDate
        );
    }
}
