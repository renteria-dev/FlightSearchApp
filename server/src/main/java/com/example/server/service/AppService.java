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
import com.example.server.model.AirportsDTO;
import com.example.server.model.Dictionaries;
import com.example.server.model.Flight;
import com.example.server.model.FlightsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import java.util.ArrayList;
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

    public AirportsDTO getAirportCode(String subtype, String keyword) {

        String accessToken = authenticationService.getAccessToken();

        String url = amadeusApiClient.buildLocationSearchUrl(subtype, keyword);

        JsonNode response = amadeusApiClient.request(accessToken, url);

        AirportsDTO myAirports = new AirportsDTO();

        myAirports.setLocations(LocationsMapper.convert(response));

        return myAirports;

    }

    public List<FlightsDTO> getFlights(String departureAitaCode, String arrivalAitaCode, String departureDate, String returnDate, int numberAdults, String currencyCode, Boolean nonStop, String sortByPrice, String sortByDate) {

        String accessToken = authenticationService.getAccessToken();
        String url = amadeusApiClient.buildFlightSearchUrl(departureAitaCode, arrivalAitaCode, departureDate, returnDate, numberAdults, currencyCode, nonStop, sortByPrice, sortByDate);
        JsonNode jsonResponse = null;

        try {
            jsonResponse = amadeusApiClient.request(accessToken, url);
        } catch (Exception e) {
            Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, "Error al obtener o procesar la respuesta del API", e);
            List<FlightsDTO> myFDTO = new ArrayList<>();

            return myFDTO;
        }

//        Si AmadeusAPI esta caido usaremos un json. Solo Pruebas
//        if (jsonResponse==null) {
//
//            try {
//                jsonResponse = Handle500.loadJsonFromFile("static/StaticResponse.json");
//            } catch (IOException ex) {
//                Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
        JsonNode dicts = jsonResponse.get("dictionaries");
        Dictionaries myDict = new Dictionaries();

        if (dicts!= null && !dicts.isEmpty()) {
            myDict = DictionariesMapper.convert(dicts);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        String node = jsonResponse.get("data").toString();

        if (!node.isEmpty()) {
            try {
                Flight[] myFlights = objectMapper.readValue(node, Flight[].class);
                for (Flight f : myFlights) {
                    System.out.println(f.toString());
                }

                return FlightsMapper.transformFlightsToDTO(myFlights, myDict);

            } catch (JsonProcessingException ex) {
                Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        List<FlightsDTO> myFDTO = new ArrayList<>();
        myFDTO.add(new FlightsDTO());

        return myFDTO;

    }

}
