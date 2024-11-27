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
import { useData } from "../hooks/useData";
interface FlightSummaryProps {
  flight: ResponseFlights;
}

const FlightSummary: React.FC<FlightSummaryProps> = ({ flight }) => {
  const [showDetails, setShowDetails] = useState(false);
  const { setMyFlight } = useData();

  return (
    <Paper
      elevation={3}
      sx={{ padding: 3, marginBottom: 2, minWidth: "800px" }}
    >
      <Box display={"inline-flex"} alignItems={"stretch"} width={"100%"}>
        <Box display={"inline-flex"} flex={1} flexDirection={"column"}>
          {flight.itineraries.map((itinerarie, idx) => (
            <>
              {idx !== 0 && <Divider />}

              {/* Timeline */}
              <Box flex={1}>
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
                            {itinerarie.segments.length > 1
                              ? `(${itinerarie.segments.length - 1} stops)`
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
                  <Divider
                    sx={{ flex: 1, ml: 2, mr: 2, visibility: "hidden" }}
                  />
                  <Box
                    sx={{
                      display: "flex",
                      alignItems: "end",
                      flexDirection: "column",
                    }}
                  >
                    <Typography variant="body1">
                      {
                        itinerarie.segments[itinerarie.segments.length - 1]
                          .arrival.iataCode
                      }{" "}
                      {
                        itinerarie.segments[itinerarie.segments.length - 1]
                          .arrival.iataCode
                      }
                    </Typography>
                    <Typography variant="body1">
                      {dayjs(
                        itinerarie.segments[itinerarie.segments.length - 1]
                          .arrival.at
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
                          {getLayoverTime(itinerarie.segments).map(
                            (stop, index) => (
                              <li key={index}>{stop}</li>
                            )
                          )}
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
                    ></Box>
                  </Grid>
                </Grid>
              </Box>
            </>
          ))}
        </Box>
        {flight.itineraries.length >= 1 && (
          <>
            <Box
              sx={{
                ml: "1rem",
                pl: "1rem",
                borderLeft: "solid  1px",
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
              }}
            >
              <Typography
                variant="h5"
                sx={{ fontWeight: "bold", color: green["A700"] }}
              >
                Total:
              </Typography>
              <Typography
                variant="h5"
                sx={{ fontWeight: "bold", color: green["A700"] }}
              >
                {flight.totalPrice} {flight.currency}
              </Typography>
              <Typography variant="body1">Price per Traveler:</Typography>
              <Typography variant="body1">
                {flight.travelerPricings[0]?.price.total}{" "}
                {flight.travelerPricings[0]?.price.currency}
              </Typography>
              <Button
                variant="contained"
                size="small"
                onClick={() => {
                  setShowDetails(!showDetails);
                  setMyFlight(flight);
                }}
              >
                {showDetails ? "Hide Details" : "Show Details"}
              </Button>
            </Box>
          </>
        )}
      </Box>

      {/* Mostrar detalles del vuelo si el usuario lo solicita */}
      {/* {showDetails && (
        <>
          <Divider sx={{ my: 2 }} />

          <FlightDetails flight={flight} />
        </>
      )} */}
    </Paper>
  );
};

export default FlightSummary;
