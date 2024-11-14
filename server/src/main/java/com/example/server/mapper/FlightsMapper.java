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
import com.example.server.model.FlightsDTO;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;

public class FlightsMapper {

    public static List<FlightsDTO> convert(JsonNode jsonData) {

        long startTime = System.nanoTime();
        List<FlightsDTO> flightDTOList = new ArrayList<>();

        JsonNode dataNode = jsonData.get("data");
        Dictionaries dictMapper = DictionariesMapper.convert(jsonData);

        

        if (dataNode != null && dataNode.isArray()) {

            // Iterar sobre los datos de vuelo en lugar de usar stream
            for (JsonNode option : dataNode) {
                FlightsDTO summary = new FlightsDTO();

                // Agregar la información relevante de cada opción de vuelo
                JsonNode itinerariesNode = option.get("itineraries");
                if (itinerariesNode != null && itinerariesNode.isArray() && itinerariesNode.size() > 0) {
                    // Obtener el primer y último segmento de los itinerarios
                    JsonNode firstItinerary = itinerariesNode.get(0);
                    JsonNode lastItinerary = itinerariesNode.get(itinerariesNode.size() - 1);

                    JsonNode firstSegment = firstItinerary.get("segments").get(0); // Primer segmento
                    JsonNode lastSegment = lastItinerary.get("segments").get(lastItinerary.get("segments").size() - 1); // Último segmento
                    
                    //summary.setSegments(firstItinerary.get("segments"));

                    // Hora de salida y llegada (usamos el primer y último segmento para obtener esos datos)
                    summary.setDepartureTime(firstSegment.get("departure").get("at").asText());
                    summary.setArrivalTime(lastSegment.get("arrival").get("at").asText());

                    // Aeropuertos de salida y llegada (usamos el primer y último segmento)
                    summary.setDepartureAirport(firstSegment.get("departure").get("iataCode").asText());
                    summary.setArrivalAirport(lastSegment.get("arrival").get("iataCode").asText());

                    // Aerolínea principal
                    summary.setAirlineCode(firstSegment.get("carrierCode").asText());
                    summary.setAirlineName(getAirlineName(dictMapper, summary.getAirlineCode()));

                    // Aerolínea operadora (si es diferente)
                    JsonNode operatingCarrierNode = firstSegment.get("operating");
                    if (operatingCarrierNode != null) {
                        String operatingCarrierCode = operatingCarrierNode.get("carrierCode").asText();
                        summary.setOperatingAirlineCode(operatingCarrierCode);
                        summary.setOperatingAirlineName(getAirlineName(dictMapper, operatingCarrierCode));
                    }

                    // Duración total del vuelo (sumamos la duración de los segmentos)
                    summary.setTotalFlightDuration(firstItinerary.get("duration").asText());

                    // Precio
                    JsonNode priceNode = option.get("price");
                    if (priceNode != null) {
                        summary.setTotalPrice(priceNode.get("total").asText());
                    }

                    JsonNode travelerPricingNode = option.get("travelerPricings");
                    if (travelerPricingNode != null && travelerPricingNode.isArray() && travelerPricingNode.size() > 0) {
                        // Acceder al primer elemento del array de precios de viajero
                        JsonNode firstTravelerPricing = travelerPricingNode.get(0);

                        // Obtener el total del precio por viajero (campo "grandTotal")
                        if (firstTravelerPricing.has("price")) {
                            JsonNode pricingNode = firstTravelerPricing.get("price");
                            if (pricingNode.has("grandTotal")) {
                                String pricePerTraveler = pricingNode.get("grandTotal").asText();
                                summary.setPricePerTraveler(pricePerTraveler); // Asignar el valor a la propiedad del DTO
                            }
                        }
                    }

                    // Añadir el DTO de vuelo a la lista
                    flightDTOList.add(summary);
                }
            }
        }

        long endTime = System.nanoTime();

        // Calcular el tiempo transcurrido en milisegundos
        long durationInMillis = (endTime - startTime) / 1000000;

        // Imprimir el tiempo de respuesta
        System.out.println("LocationsMapper time: " + durationInMillis + " ms");

        return flightDTOList;
    }

    private static String getAirlineName(Dictionaries dct, String carrierCode) {
        // Aquí se podría usar un diccionario de aerolíneas para obtener el nombre
        return DictionariesMapper.getCarrierName(dct, carrierCode);
    }

    private static String getAircraftName(Dictionaries dct, String carrierCode) {
        // Aquí se podría usar un diccionario de aerolíneas operadoras
        return DictionariesMapper.getAircraftName(dct, carrierCode);
    }
}
