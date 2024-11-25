import React from "react";
import { Box, Typography, Grid, alpha } from "@mui/material";
import {
  ResponseFlights,
  Segment,
  TravelerPricing,
  FareDetailsBySegment,
} from "../interfaces/ResponseFlights";
import FlightPriceDetails from "./FlightPriceDetails";
import FlightSegment from "./FlightSegment";
import { blue } from "@mui/material/colors";
import AmenitiesTable from "./AmenitiesTable";

interface FlightDetailsProps {
  flight: ResponseFlights;
}

const FlightDetails: React.FC<FlightDetailsProps> = ({ flight }) => {
  
  const renderSegments = (itineraryIndex: number) => {
    // segments of actual itierary
    const itinerary = flight.itineraries[itineraryIndex];
    
    return itinerary.segments.map((segment: Segment, segmentIndex: number) => (
      <Grid container spacing={2} key={segment.id} mb={4}>
        <Grid item xs={12} sm={4} sx={{ display: "flex" }}>
          <FlightSegment segment={segment} index={segmentIndex} />
        </Grid>
        <Grid item xs={12} sm={8}>
          {/* Travelers Fare Details Column */}
          <Box
            sx={{
              padding: 2,
              backgroundColor: alpha(blue["500"], 0.25),
              borderRadius: 2,
            }}
          >
            <Typography variant="h6">Travelers Fare Details</Typography>
            {flight.travelerPricings.slice(0, 1).map((traveler: TravelerPricing) => {
              // filter details of traveler by segmentid
              const fareDetails = traveler.fareDetailsBySegment.filter(
                (detail: FareDetailsBySegment) =>
                  detail.segmentId === segment.id
              );

              return fareDetails.length > 0 ? (
                <Box key={traveler.travelerId} sx={{ marginBottom: 2 }}>
                  <Typography variant="body1">
                    Fare Option: {traveler.fareOption}
                  </Typography>
                  <Typography variant="body1">
                    Traveler Type: {traveler.travelerType}
                  </Typography>
                  {fareDetails.map((detail) => (
                    <Box key={detail.segmentId} sx={{ marginTop: 1 }}>
                      <Typography variant="body2">
                        Cabin: {detail.cabin}
                      </Typography>
                      <Typography variant="body2">
                        Class: {detail.class}
                      </Typography>
                      <Typography variant="body2">
                        Price: {traveler.price.currency} {traveler.price.total}
                      </Typography>
                      {detail.amenities && detail.amenities.length > 0 && (
                        <AmenitiesTable amenities={detail.amenities} />
                      )}
                    </Box>
                  ))}
                </Box>
              ) : null;
            })}
          </Box>
        </Grid>
      </Grid>
    ));
  };

  return (
    <Box sx={{ marginTop: 2}}>
      <Typography variant="h6">Flight Details</Typography>
      {/* Recorrer todos los itinerarios */}
      {flight.itineraries.map((_itinerary, itineraryIndex) => (
        <Box key={itineraryIndex} sx={{ marginBottom: 4 }}>
          <Typography variant="h6" sx={{ marginBottom: 2 }}>
            Itinerary {itineraryIndex + 1}
          </Typography>
          {renderSegments(itineraryIndex)}
        </Box>
      ))}
      <FlightPriceDetails flight={flight} />
    </Box>
  );
};

export default FlightDetails;
