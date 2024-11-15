/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.service;

import com.example.server.api.AuthenticationService;
import com.example.server.api.AmadeusApiClient;
import com.example.server.mapper.DictionariesMapper;
import com.example.server.mapper.FlightsMapper;
import com.example.server.mapper.LocationsMapper;
import com.example.server.model.Dictionaries;
import com.example.server.model.Flight;
import com.example.server.model.FlightsDTO;
import com.example.server.model.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

/**
 *
 * @author luis.renteria
 */
@Service
public class AppService {

    private final AuthenticationService authenticationService;
    private final AmadeusApiClient amadeusApiClient;

    @Autowired
    public AppService(AuthenticationService authenticationService, AmadeusApiClient amadeusApiClient) {
        this.authenticationService = authenticationService;
        this.amadeusApiClient = amadeusApiClient;
    }

    private static final Logger LOGGER = Logger.getLogger(AppService.class.getName());

    public List<Location> getAirportCode(String subtype, String keyword) {

        String accessToken = authenticationService.getAccessToken();

        String url = amadeusApiClient.buildLocationSearchUrl(subtype, keyword);

        JsonNode response = amadeusApiClient.request(accessToken, url);

        return LocationsMapper.convert(response);

    }

    public List<FlightsDTO> getFlights(String departureAitaCode, String arrivalAitaCode, String departureDate, String arrivalDate, String returnDate, int numberAdults, String currencyCode, Boolean nonStop, String sortByPrice, String sortByDate) {

        String accessToken = authenticationService.getAccessToken();
        String url = amadeusApiClient.buildFlightSearchUrl(departureAitaCode, arrivalAitaCode, departureDate, arrivalDate, returnDate, numberAdults, currencyCode, nonStop, sortByPrice, sortByDate);
        JsonNode jsonResponse = amadeusApiClient.request(accessToken, url);

        
        
        JsonNode dicts = jsonResponse.get("dictionaries");
        Dictionaries myDict = new Dictionaries();
        
        if (!dicts.isNull() && !dicts.isEmpty()) {
            myDict=DictionariesMapper.convert(dicts);
        }
        
        // Crear un ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        String node = jsonResponse.get("data").toString();
        

        if (!node.isEmpty()) {
            try {
                Flight[] myFlights = objectMapper.readValue(node, Flight[].class);
                for (Flight f : myFlights) {
                    System.out.println(f.toString());
                }

                return FlightsMapper.transformFlightsToDTO(myFlights,myDict);

            } catch (JsonProcessingException ex) {
                Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;

    }

}
