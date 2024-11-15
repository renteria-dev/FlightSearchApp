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
import com.example.server.model.Flight;
import com.example.server.model.FlightsDTO;
import com.example.server.model.Itinerary;
import com.example.server.model.Segment;
import com.example.server.model.TravelerPricing;
import com.example.server.model.TravelerPricingPrice;

import java.util.ArrayList;
import java.util.List;

public class FlightsMapper {

    public static List<FlightsDTO> transformFlightsToDTO(Flight[] flights, Dictionaries myDict) {
        List<FlightsDTO> flightDTOs = new ArrayList<>();

        // Iterar sobre el arreglo de vuelos
        for (Flight flight : flights) {
            FlightsDTO flightDTO = new FlightsDTO();

            // Información básica del vuelo
            flightDTO.setFlightId(flight.getId());

            // Asumimos que todos los itinerarios son iguales, obtenemos la primera segmentación
            Itinerary firstItinerary = flight.getItineraries()[0];

            // Para el primer segmento (salida)
            Segment firstSegment = firstItinerary.getSegments()[0];
            flightDTO.setDepartureTime(firstSegment.getDeparture().getAt());

            // Para el último segmento (llegada)
            Segment lastSegment = firstItinerary.getSegments()[firstItinerary.getSegments().length - 1];
            flightDTO.setArrivalTime(lastSegment.getArrival().getAt());

            // Aeropuertos de salida y llegada
            flightDTO.setDepartureAirportName(firstSegment.getDeparture().getIataCode());
            flightDTO.setDepartureAirportCode(firstSegment.getDeparture().getIataCode());
            flightDTO.setArrivalAirportName(lastSegment.getArrival().getIataCode());
            flightDTO.setArrivalAirportCode(lastSegment.getArrival().getIataCode());

            // Aerolínea principal y operativa
            flightDTO.setAirlineName( DictionariesMapper.getCarrierName(myDict, firstSegment.getCarrierCode())); // O la aerolínea de algún atributo que tengas
            flightDTO.setAirlineCode(firstSegment.getCarrierCode());

            // Si la aerolínea operativa es diferente, la incluimos
            if (!firstSegment.getCarrierCode().equals(firstSegment.getOperating())) {
                flightDTO.setOperatingAirlineName( DictionariesMapper.getCarrierName(myDict, firstSegment.getOperating())); // O alguna lógica para obtener el nombre
                flightDTO.setOperatingAirlineCode(firstSegment.getOperating());
            }
            
           

            // Duración del vuelo
            flightDTO.setDuration(firstItinerary.getDuration());  // Asumiendo que la duración se encuentra aquí

            // Precio total
            flightDTO.setTotalPrice(flight.getPrice().getTotal());

            // Indicar si tiene paradas (más de 1 segmento indica paradas)
            flightDTO.setHasStops(firstItinerary.getSegments().length > 1);

            // Establecer segmentos del vuelo
            List<Segment> segments = new ArrayList<>();
            for (Segment segment : firstItinerary.getSegments()) {
                String aircraftName=DictionariesMapper.getAircraftName(myDict, segment.getAircraft());
                segment.setAircraft(aircraftName);
                segments.add(segment);
            }
            flightDTO.setSegments(segments);

            // Precios por viajero (si están presentes)
            List<TravelerPricing> travelerPricingDTOs = new ArrayList<>();
            for (TravelerPricing travelerPricing : flight.getTravelerPricings()) {
                TravelerPricing travelerPricingDTO = new TravelerPricing();
                travelerPricingDTO.setTravelerID(travelerPricing.getTravelerID());
                travelerPricingDTO.setFareOption(travelerPricing.getFareOption());
                travelerPricingDTO.setTravelerType(travelerPricing.getTravelerType());

                // Mapear el precio del viajero
                TravelerPricingPrice priceDTO = new TravelerPricingPrice();
                priceDTO.setCurrency(travelerPricing.getPrice().getCurrency());
                priceDTO.setTotal(travelerPricing.getPrice().getTotal());
                priceDTO.setBase(travelerPricing.getPrice().getBase());

//                // Establecer tarifas adicionales (si las hay)
//                List<Fee> feeDTOs = new ArrayList<>();
//                for (Fee fee : travelerPricing) {
//                    Fee feeDTO = new Fee();
//                    feeDTO.setAmount(fee.getAmount());
//                    feeDTO.setFeeType(fee.getFeeType());
//                    feeDTOs.add(feeDTO);
//                }
//                priceDTO.setFees(feeDTOs);
                travelerPricingDTO.setPrice(priceDTO);
                
                travelerPricingDTO.setFareDetailsBySegment(travelerPricing.getFareDetailsBySegment());

                travelerPricingDTOs.add(travelerPricingDTO);
            }

            flightDTO.setTravelerPricings(travelerPricingDTOs);

            // Agregar el DTO de vuelo a la lista
            flightDTOs.add(flightDTO);
        }

        return flightDTOs;
    }
}
