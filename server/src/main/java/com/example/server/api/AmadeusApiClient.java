/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.api;

/**
 *
 * @author luis.renteria
 */
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
public class AmadeusApiClient {

    @Value("${amadeus.url}")
    private String amadeusUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public AmadeusApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String buildLocationSearchUrl(String subtype, String keyword) {
        return UriComponentsBuilder.fromHttpUrl(amadeusUrl)
                .path("/v1/reference-data/locations")
                .queryParam("subType", subtype)
                .queryParam("keyword", keyword)
                .queryParam("sort", "analytics.travelers.score")
                .queryParam("view", "LIGHT")
                .toUriString();
    }

    public String buildAirportDetailsUrl(String airportCode) {
        return UriComponentsBuilder.fromHttpUrl(amadeusUrl)
                .path("/v1/reference-data/locations/{airportCode}")
                .buildAndExpand(airportCode)
                .toUriString();
    }

    public JsonNode request(String accessToken, String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                JsonNode.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode body = response.getBody();
            if (body != null) {
                return body;
            } else {
                throw new IllegalStateException("No data found from Amadeus API.");
            }
        } else {
            throw new IllegalStateException("Failed to retrieve airport data from Amadeus API. Status code: " + response.getStatusCode());
        }
    }

    public String buildFlightSearchUrl(String departureAitaCode, String arrivalAitaCode, String departureDate, String arrivalDate, String returnDate, int numberAdults, String currencyCode, Boolean nonStop, String sortByPrice, String sortByDate) {
        return UriComponentsBuilder.fromHttpUrl(amadeusUrl)
                .path("/v2/shopping/flight-offers")
                .queryParam("originLocationCode", departureAitaCode)
                .queryParam("destinationLocationCode", arrivalAitaCode)
                .queryParam("departureDate", departureDate)
                .queryParam("adults", numberAdults)
                .queryParam("currencyCode", currencyCode)
                .queryParam("nonStop", nonStop)
                .queryParam("returnDate", returnDate)
                .toUriString();
    }

}
