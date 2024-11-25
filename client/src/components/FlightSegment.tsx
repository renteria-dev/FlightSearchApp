import React from "react";
import { Typography, Grid, Paper, alpha } from "@mui/material";
import { Segment } from "../interfaces/ResponseFlights";
import { parseISO8601Duration } from "../utils/dates";
import { grey } from "@mui/material/colors";

interface FlightSegmentProps {
  segment: Segment;
  index: number;
}

const FlightSegment: React.FC<FlightSegmentProps> = ({ segment, index }) => {
  return (
    <Paper sx={{ padding: 2 , backgroundColor: alpha(grey["300"],0.3) }}>
      <Grid container spacing={2}>
        <Grid item>
          <Typography variant="subtitle1">Segment {index + 1}</Typography>
          <Typography variant="body1">
            Departure: {new Date(segment.departure.at).toLocaleString()}
          </Typography>
          <Typography variant="body1">
            Arrival: {new Date(segment.arrival.at).toLocaleString()}
          </Typography>
          <Typography variant="body1">
            Airline: {segment.carrierCode}
          </Typography>
          <Typography variant="body1">
            Flight Number: {segment.number}
          </Typography>
          {segment.operating && (
            <Typography variant="body1">
              Operating Airline: {segment.operating}
            </Typography>
          )}
          <Typography variant="body1">Aircraft: {segment.aircraft}</Typography>
          {/* <Typography variant="body1">Number of Stops: {segment.numberOfStops}</Typography> */}
          <Typography variant="body1">
            Duration: {parseISO8601Duration(segment.duration)}
          </Typography>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default FlightSegment;
