/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.mapper;

/**
 *
 * @author luis.renteria
 */
import com.example.server.model.Location;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

public class LocationsMapper {

    public static List<Location> convert(JsonNode jsonData) {
        long startTime = System.nanoTime();
        List<Location> locations = new ArrayList<>();
        Map<String, Location> locationMap = new HashMap<>();

        JsonNode dataNode = jsonData.get("data");
        if (dataNode != null && dataNode.isArray()) {
            for (JsonNode node : dataNode) {
                String iataCode = node.path("iataCode").asText();
                String name = node.path("name").asText();
                String cityName = node.path("address").path("cityName").asText();
                String countryName = node.path("address").path("countryName").asText();
                String type = node.path("type").asText();
                String subType = node.path("subType").asText();

                if (locationMap.containsKey(iataCode)) {

                    if ("CITY".equals(subType) && !locationMap.get(iataCode).getCityName().equals(cityName)) {
                        continue;
                    }
                }

                if ("AIRPORT".equals(type) || !locationMap.containsKey(iataCode)) {
                    Location location = new Location(iataCode, name, cityName, countryName);
                    locationMap.put(iataCode, location);
                }
            }
        }

        locations.addAll(locationMap.values());

        long endTime = System.nanoTime();

        // Calculate elapsed time in milliseconds
        long durationInMillis = (endTime - startTime) / 1000000;

        // Print out the response time
        System.out.println("LocationsMapper time: " + durationInMillis + " ms");
        return locations;
    }

}
