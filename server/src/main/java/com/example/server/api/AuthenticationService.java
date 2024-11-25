/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

/**
 *
 * @author luis.renteria
 */
@Service
public class AuthenticationService {

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.secret}")
    private String apiSecret;

    @Value("${amadeus.url}")
    private String amadeusUrl;

    private String accessToken;

    private final RestTemplate restTemplate;

    @Autowired
    public AuthenticationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Scheduled(fixedRate = 20 * 60 * 1000)
    public void updateAccessToken() {

        String tokenUrl = "/v1/security/oauth2/token";
        String authData = "grant_type=client_credentials&client_id=" + apiKey + "&client_secret=" + apiSecret;

        try {
            // Create request headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // Create request entity with the data and headers
            HttpEntity<String> entity = new HttpEntity<>(authData, headers);

//            long startTime = System.nanoTime();

            // Send the POST request
            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    amadeusUrl + tokenUrl,
                    HttpMethod.POST,
                    entity,
                    JsonNode.class
            );

//            long endTime = System.nanoTime();
//
//            // Calculate elapsed time in milliseconds
//            long durationInMillis = (endTime - startTime) / 1000000;
//
//            // Print out the response time
//            System.out.println("Response time: " + durationInMillis + " ms");

            // Check for a successful response
            if (response.getStatusCode() == HttpStatus.OK) {
                JsonNode body = response.getBody();
                if (body != null && body.has("access_token")) {
                    accessToken = body.get("access_token").asText();

                    System.out.println("Access token updated: " + accessToken);
                } else {
                    System.err.println("Failed get access token.");
                }
            } else {
                System.err.println("Failed to get access token. Status code: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            System.err.println("Error occurred while fetching access token: " + e.getMessage());
        }

    }

    public String getAccessToken() {
        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available.");
        }
        return accessToken;
    }
}
