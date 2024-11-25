import React, { useState } from "react";
import {
  Box,
  Typography,
  Button,
  Grid,
  Paper,
  Divider,
  Chip,
} from "@mui/material";
import { ResponseFlights } from "../interfaces/ResponseFlights";
import FlightDetails from "./FlightDetails";
import dayjs from "dayjs";
import { FlightLand, FlightTakeoff } from "@mui/icons-material";
import { green } from "@mui/material/colors";
import { getLayoverTime, parseISO8601Duration } from "../utils/dates";
interface FlightSummaryProps {
  flight: ResponseFlights;
}

const FlightSummary: React.FC<FlightSummaryProps> = ({ flight }) => {
  const [showDetails, setShowDetails] = useState(false);


  return (
    <Paper
      elevation={3}
      sx={{ padding: 3, marginBottom: 2, minWidth: "800px" }}
    >
      {flight.itineraries.map((itinerarie, idx) => (
        <Box key={idx} sx={{ marginBottom: 4 }}>
          {/* Timeline */}
          <Box sx={{ display: "flex", alignItems: "center" }}>
            <Box>
              <Typography variant="h6" gutterBottom>
                Departure
              </Typography>

              <FlightTakeoff sx={{ color: "gray", ml: 2, mr: 2 }} />
            </Box>
            <Divider sx={{ flex: 1, ml: 2, mr: 2 }}>
              <Chip
                size="medium"
                label={
                  <>
                    <Typography variant="body1">
                      {parseISO8601Duration(itinerarie.duration)}{" "}
                      {itinerarie.segments.length - 1 > 1
                        ? `${itinerarie.segments.length - 1} stops`
                        : "(Direct)"}
                    </Typography>
                  </>
                }
              />
            </Divider>
            <Box>
              <Typography variant="h6" gutterBottom>
                Arrival
              </Typography>

              <FlightLand sx={{ color: "gray", ml: 2, mr: 2 }} />
            </Box>
          </Box>

          <Box sx={{ display: "flex" }}>
            <Box
              sx={{
                display: "flex",
                alignItems: "start",
                flexDirection: "column",
              }}
            >
              <Typography variant="body1">
                {itinerarie.segments[0].departure.iataCode}{" "}
                {itinerarie.segments[0].departure.iataCode}
              </Typography>
              <Typography variant="body1">
                {dayjs(itinerarie.segments[0].departure.at).format(
                  "YYYY-MM-DD HH:mm"
                )}
              </Typography>
            </Box>
            <Divider sx={{ flex: 1, ml: 2, mr: 2, visibility: "hidden" }} />
            <Box
              sx={{
                display: "flex",
                alignItems: "end",
                flexDirection: "column",
              }}
            >
              <Typography variant="body1">
                {
                  itinerarie.segments[itinerarie.segments.length - 1].arrival
                    .iataCode
                }{" "}
                {
                  itinerarie.segments[itinerarie.segments.length - 1].arrival
                    .iataCode
                }
              </Typography>
              <Typography variant="body1">
                {dayjs(
                  itinerarie.segments[itinerarie.segments.length - 1].arrival.at
                ).format("YYYY-MM-DD HH:mm")}
              </Typography>
            </Box>
          </Box>

          <Grid container spacing={3} flexWrap={"nowrap"}>
            {/* Columna 1 */}
            <Grid item xs={12} md={4}>
              <br />

              <Typography variant="body1">
                Airline:
                <br />
                {flight.airlineName} ({flight.airlineCode})
              </Typography>
              {flight.operatingAirlineName && (
                <Typography variant="body1">
                  Operating Airline: {flight.operatingAirlineName} (
                  {flight.operatingAirlineCode})
                </Typography>
              )}
            </Grid>

            {/* Columna 2 */}
            <Grid item xs={12} md={4}>
              <br />

              <Typography variant="body1"></Typography>
              {itinerarie.segments.length - 1 > 0 && (
                <Box sx={{ marginTop: 2 }}>
                  <ul>
                    {getLayoverTime(itinerarie.segments).map((stop, index) => (
                      <li key={index}>{stop}</li>
                    ))}
                  </ul>
                </Box>
              )}
            </Grid>

            {/* Columna 3 */}
            <Grid item xs={12} md={4}>
              <br />

              <Box
                sx={{
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "center",
                }}
              >
                {idx === flight.itineraries.length - 1 && (
                  <>
                    <Typography
                      variant="h5"
                      sx={{ fontWeight: "bold", color: green["A700"] }}
                    >
                      Total: {flight.totalPrice} {flight.currency}
                    </Typography>
                    <Typography variant="body1">
                      Price per Traveler:{" "}
                      {flight.travelerPricings[0]?.price.total}{" "}
                      {flight.travelerPricings[0]?.price.currency}
                    </Typography>
                    <Button
                      variant="contained"
                      size="small"
                      onClick={() => setShowDetails(!showDetails)}
                    >
                      {showDetails ? "Hide Details" : "Show Details"}
                    </Button>
                  </>
                )}
              </Box>
            </Grid>
          </Grid>
        </Box>
      ))}

      {/* Mostrar detalles del vuelo si el usuario lo solicita */}
      {showDetails && (
        <>
          {/* Divider */}
          <Divider sx={{ my: 2 }} />

          <FlightDetails flight={flight} />
        </>
      )}
    </Paper>
  );
};

export default FlightSummary;
