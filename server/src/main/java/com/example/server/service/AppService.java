/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.service;

import com.example.server.api.AuthenticationService;
import com.example.server.api.AmadeusApiClient;
import com.example.server.mapper.FlightsMapper;
import com.example.server.mapper.LocationsMapper;
import com.example.server.mapper.TreePrinter;
import com.example.server.model.Flight;
import com.example.server.model.FlightsDTO;
import com.example.server.model.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.*;
import static org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties.UiService.LOGGER;
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

        // Crear un ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserializar el JSON a la clase Flight
        String node = jsonResponse.get("data").toString();

        if (!node.isEmpty()) {
            try {
                Flight[] myFlights = objectMapper.readValue(node, Flight[].class);
                
                System.out.println(myFlights[0].toString());

                return FlightsMapper.transformFlightsToDTO(myFlights);

            } catch (JsonProcessingException ex) {
                Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;

    }

}
