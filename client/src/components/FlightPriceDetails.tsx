import React from "react";
import {
  Box,
  Typography,
  Grid,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import { ResponseFlights } from "../interfaces/ResponseFlights";
import { green } from "@mui/material/colors";

interface FlightPriceDetailsProps {
  flight: ResponseFlights;
}

const FlightPriceDetails: React.FC<FlightPriceDetailsProps> = ({ flight }) => {
  const { price } = flight;

  return (
    <Box sx={{ marginTop: 2 }}>
      <Typography variant="h6">Price Breakdown</Typography>
      <Paper sx={{ padding: 2, marginBottom: 1 }}>
        <Grid container spacing={1}>
          <Grid item xs={12}>
            <Typography variant="h6" sx={{ fontWeight: "bold" }}>
              Base Price: ${Number(price.base).toLocaleString()}{" "}
              {price.currency}
            </Typography>
            <Typography
              variant="h5"
              sx={{ fontWeight: "bold", color: green["A700"] }}
            >
              Total Price: ${Number(price.total).toLocaleString()}{" "}
              {price.currency}
            </Typography>
            <Typography variant="body1">Fees: </Typography>

            {/* Renderizar la tabla de Fees */}
            <TableContainer component={Paper} sx={{ marginTop: 2 }}>
              <Table size="small">
                <TableHead>
                  <TableRow>
                    <TableCell>Fee Type</TableCell>
                    <TableCell align="right">Amount</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {price.fees && price.fees.length > 0 ? (
                    price.fees.map((fee, index) => (
                      <TableRow key={index}>
                        <TableCell>{fee.type}</TableCell>
                        <TableCell align="right">{fee.amount}</TableCell>
                      </TableRow>
                    ))
                  ) : (
                    <TableRow>
                      <TableCell colSpan={2} align="center">
                        No fees available
                      </TableCell>
                    </TableRow>
                  )}
                </TableBody>
              </Table>
            </TableContainer>

            {/* Renderizar la tabla de Fees */}

            {price.additionalServices && (
              <TableContainer component={Paper} sx={{ marginTop: 2 }}>
                <Table size="small">
                  <TableHead>
                    <TableRow>
                      <TableCell>Additional Service Type</TableCell>
                      <TableCell align="right">Amount</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {price.additionalServices &&
                    price.additionalServices.length > 0 ? (
                      price.additionalServices.map((additional, index) => (
                        <TableRow key={index}>
                          <TableCell>{additional.type}</TableCell>
                          <TableCell align="right">
                            {additional.amount}
                          </TableCell>
                        </TableRow>
                      ))
                    ) : (
                      <TableRow>
                        <TableCell colSpan={2} align="center">
                          No fees available
                        </TableCell>
                      </TableRow>
                    )}
                  </TableBody>
                </Table>
              </TableContainer>
            )}

            <Typography variant="h6" sx={{ marginTop: 2 }}>
              Price per Traveler:{" "}
              {Number(flight.travelerPricings[0]?.price.total).toLocaleString()}{" "}
              {flight.travelerPricings[0]?.price.currency}
            </Typography>
          </Grid>
        </Grid>
      </Paper>
    </Box>
  );
};

export default FlightPriceDetails;
