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
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
public class AmadeusApiClient {

    @Value("${amadeus.url}")
    private String amadeusUrl;

    private final RestTemplate restTemplate;
    
    
    // Method to get a RestTemplate with SSL verification disabled
public RestTemplate getRestTemplate() throws NoSuchAlgorithmException, KeyManagementException {

    // Create SSL context to trust all certificates
    SSLContext sslContext = SSLContext.getInstance("TLS");
    
    // Define trust managers to accept all certificates
    TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
        // Method to check client's trust - accepting all certificates
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
        }

        // Method to check server's trust - accepting all certificates
        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
        }

        // Method to get accepted issuers - returning an empty array
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }};
    
    // Initialize SSL context with the defined trust managers
    sslContext.init(null, trustManagers, null);

    // Disable SSL verification for RestTemplate
    
    // Set the default SSL socket factory to use the custom SSL context
    HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    
    // Set the default hostname verifier to allow all hostnames
    HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

    // Create a RestTemplate with a custom request factory
    
    // Build RestTemplate with SimpleClientHttpRequestFactory
    RestTemplate restTemplate = new RestTemplateBuilder().requestFactory(SimpleClientHttpRequestFactory.class)
            .build();

    return restTemplate; // Return the configured RestTemplate
}

    @Autowired
    public AmadeusApiClient(RestTemplateBuilder restTemplateBuilder) throws NoSuchAlgorithmException, KeyManagementException {
        this.restTemplate = getRestTemplate();// restTemplateBuilder.build(); 
    }// en este sustituî el template por el getRestTemmplate de la funcion que le anadi


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

    public String buildFlightSearchUrl(String departureAitaCode, String arrivalAitaCode, String departureDate, String returnDate, int numberAdults, String currencyCode, Boolean nonStop, String sortByPrice, String sortByDate) {
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
