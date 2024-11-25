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

        for (Flight flight : flights) {
            FlightsDTO flightDTO = new FlightsDTO();

            // Id
            flightDTO.setFlightId(flight.getId());

            // Unique itinerary (TODO: check)
            Itinerary firstItinerary = flight.getItineraries()[0];

            // Setting departure with first segment
            Segment firstSegment = firstItinerary.getSegments()[0];
            flightDTO.setDepartureTime(firstSegment.getDeparture().getAt());

            // Setting arrival with last segment
            Segment lastSegment = firstItinerary.getSegments()[firstItinerary.getSegments().length - 1];
            flightDTO.setArrivalTime(lastSegment.getArrival().getAt());

            // Setting Airports
            flightDTO.setDepartureAirportName(firstSegment.getDeparture().getIataCode());
            flightDTO.setDepartureAirportCode(firstSegment.getDeparture().getIataCode());
            flightDTO.setArrivalAirportName(lastSegment.getArrival().getIataCode());
            flightDTO.setArrivalAirportCode(lastSegment.getArrival().getIataCode());

            // Setting Airlines (main and operating if is the case)
            flightDTO.setAirlineName(DictionariesMapper.getCarrierName(myDict, firstSegment.getCarrierCode())); // O la aerolínea de algún atributo que tengas
            flightDTO.setAirlineCode(firstSegment.getCarrierCode());

            if (!firstSegment.getCarrierCode().equals(firstSegment.getOperating())) {
                flightDTO.setOperatingAirlineName(DictionariesMapper.getCarrierName(myDict, firstSegment.getOperating())); // O alguna lógica para obtener el nombre
                flightDTO.setOperatingAirlineCode(firstSegment.getOperating());
            }

            // Duration
            flightDTO.setDuration(firstItinerary.getDuration());

            // total price
            flightDTO.setTotalPrice(flight.getPrice().getTotal());
            
            // Price details
            flightDTO.setPrice(flight.getPrice());
            

            // hasStops
            flightDTO.setHasStops(firstItinerary.getSegments().length > 1);

            // Setting segments
            List<Itinerary> itineraries = new ArrayList<>();
            for (Itinerary myItinerary : flight.getItineraries()) {

                List<Segment> mySegments = new ArrayList<>();
                for (Segment segment : myItinerary.getSegments()) {

                    String aircraftName = DictionariesMapper.getAircraftName(myDict, segment.getAircraft());
                    // Setting aircraft name
                    segment.setAircraft(aircraftName);
                    mySegments.add(segment);

                }
                myItinerary.setSegments( mySegments.toArray(new Segment[mySegments.size()]));
                itineraries.add(myItinerary);

            }

            flightDTO.setItineraries(itineraries);

            // Prices per traveler
            List<TravelerPricing> travelerPricingDTOs = new ArrayList<>();
            for (TravelerPricing travelerPricing : flight.getTravelerPricings()) {
                TravelerPricing travelerPricingDTO = new TravelerPricing();
                travelerPricingDTO.setTravelerID(travelerPricing.getTravelerID());
                travelerPricingDTO.setFareOption(travelerPricing.getFareOption());
                travelerPricingDTO.setTravelerType(travelerPricing.getTravelerType());

                TravelerPricingPrice priceDTO = new TravelerPricingPrice();
                priceDTO.setCurrency(travelerPricing.getPrice().getCurrency());
                priceDTO.setTotal(travelerPricing.getPrice().getTotal());
                priceDTO.setBase(travelerPricing.getPrice().getBase());

//                //  tarifas adicionales (si las hay)
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
            
            if (!travelerPricingDTOs.isEmpty()){
                flightDTO.setCurrency(travelerPricingDTOs.get(0).getPrice().getCurrency());
            }

            // Agregar el DTO de vuelo a la lista
            flightDTOs.add(flightDTO);
        }

        return flightDTOs;
    }
}
