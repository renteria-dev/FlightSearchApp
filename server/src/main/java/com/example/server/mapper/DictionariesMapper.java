/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.mapper;

/**
 *
 * @author luis.renteria
 */
import com.example.server.model.Dictionaries;
import com.example.server.model.Location;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class DictionariesMapper {

    public static Dictionaries convert(JsonNode jsonData) {
        Dictionaries dictionaries = new Dictionaries();

        JsonNode dictionariesNode = jsonData;

        if (dictionariesNode != null) {
            // Process locations
            JsonNode locationsNode = dictionariesNode.get("locations");
            if (locationsNode != null) {
                Map<String, Location> locationsMap = new HashMap<>();
                Iterator<Map.Entry<String, JsonNode>> locationsIterator = locationsNode.fields();
                while (locationsIterator.hasNext()) {
                    Map.Entry<String, JsonNode> entry = locationsIterator.next();
                    String locationCode = entry.getKey();
                    JsonNode locationData = entry.getValue();

                    Location location = new Location(locationData.get("cityCode").asText(), locationData.get("countryCode").asText());

                    locationsMap.put(locationCode, location);
                }
                dictionaries.setLocations(locationsMap);
            }

            // Process aircraft
            JsonNode aircraftsNode = dictionariesNode.get("aircraft");
            if (aircraftsNode != null) {
                Map<String, String> aircraftsMap = new HashMap<>();
                Iterator<Map.Entry<String, JsonNode>> aircraftsIterator = aircraftsNode.fields();
                while (aircraftsIterator.hasNext()) {
                    Map.Entry<String, JsonNode> entry = aircraftsIterator.next();
                    String aircraftCode = entry.getKey();
                    String aircraftName = entry.getValue().asText();
                    aircraftsMap.put(aircraftCode, aircraftName);
                }
                dictionaries.setAircrafts(aircraftsMap);
            }

            // Process carriers
            JsonNode carriersNode = dictionariesNode.get("carriers");
            if (carriersNode != null) {
                Map<String, String> carriersMap = new HashMap<>();
                Iterator<Map.Entry<String, JsonNode>> carriersIterator = carriersNode.fields();
                while (carriersIterator.hasNext()) {
                    Map.Entry<String, JsonNode> entry = carriersIterator.next();
                    String carrierCode = entry.getKey();
                    String carrierName = entry.getValue().asText();
                    carriersMap.put(carrierCode, carrierName);
                }
                dictionaries.setCarriers(carriersMap);
            }
        }

        return dictionaries;
    }

    public static String getCarrierName(Dictionaries dictionaries, String carrierCode) {
        Map<String, String> carriersMap = dictionaries.getCarriers();
        if (carriersMap != null && carriersMap.containsKey(carrierCode)) {
            return carriersMap.get(carrierCode);
        }
        return null;
    }

    public static String getAircraftName(Dictionaries dictionaries, String aircraftCode) {
        Map<String, String> aircraftsMap = dictionaries.getAircrafts();
        if (aircraftsMap != null && aircraftsMap.containsKey(aircraftCode)) {
            return aircraftsMap.get(aircraftCode);
        }
        return null;
    }
}
