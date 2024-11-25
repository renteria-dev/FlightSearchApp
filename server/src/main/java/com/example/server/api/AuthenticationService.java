/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.api;

import com.fasterxml.jackson.databind.JsonNode;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;



/**
 *
 * @author luis.renteria
 */
@Service
public final class AuthenticationService {

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.secret}")
    private String apiSecret;

    @Value("${amadeus.url}")
    private String amadeusUrl;

    private String accessToken;

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
    public AuthenticationService(RestTemplateBuilder restTemplateBuilder) throws NoSuchAlgorithmException, KeyManagementException {
        this.restTemplate = getRestTemplate();//restTemplateBuilder.build(); 
    }// en este sustituî el template por el getRestTemmplate de la funcion que le anadi


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


            // Send the POST request
            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    amadeusUrl + tokenUrl,
                    HttpMethod.POST,
                    entity,
                    JsonNode.class
            );


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
