import { useEffect, useState } from "react";
import axios from "axios";
import { ResponseFlights } from "../interfaces/ResponseFlights";
import {
  Box,
  Typography,
  Button,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Grid,
  Pagination,
  SelectChangeEvent,
} from "@mui/material";
import FlightSummary from "../components/FlightSummary";
import dayjs from "dayjs";

interface ResultsPageProps {
  data?: ResponseFlights[];
}

const ResultsPage: React.FC<ResultsPageProps> = ({ data }) => {
  const [response, setResponse] = useState<ResponseFlights[]>([]);
  const [sortedFlights, setSortedFlights] = useState<ResponseFlights[]>([]);
  const [page, setPage] = useState(1);
  const [rowsPerPage] = useState(3);
  const [durationSortOrder, setDurationSortOrder] = useState<"asc" | "desc">(
    "asc"
  );
  const [priceSortOrder, setPriceSortOrder] = useState<"asc" | "desc">("asc");
  const [sortBy, setSortBy] = useState<"duration" | "price">("duration");

  useEffect(() => {
    if (data) {
      setResponse(data);
    } else {
      axios
        .get('example.json')
        .then((response) => {
          setResponse(response.data);
        })
        .catch((error) => {
          console.error('Error fetching flights:', error);
        });
    }
  }, [data]); 

  // Get flight duration in minutes using dayjs
  const getFlightDuration = (flight: ResponseFlights): number => {
    const departure = dayjs(flight.departureTime);
    const arrival = dayjs(flight.arrivalTime);
    const duration = arrival.diff(departure, "minute"); // Diff in minutes
    return duration;
  };

  // Sort flights based on selected criteria and order
  const sortFlights = (flights: ResponseFlights[]) => {
    const sorted = [...flights].sort((a, b) => {
      const aDuration = getFlightDuration(a);
      const bDuration = getFlightDuration(b);
      const aPrice = parseFloat(a.totalPrice);
      const bPrice = parseFloat(b.totalPrice);

      // First sort by the selected primary criterion (duration or price)
      let comparisonResult = 0;
      if (sortBy === "duration") {
        comparisonResult =
          durationSortOrder === "asc"
            ? aDuration - bDuration
            : bDuration - aDuration;
      } else if (sortBy === "price") {
        comparisonResult =
          priceSortOrder === "asc" ? aPrice - bPrice : bPrice - aPrice;
      }

      // If primary criteria comparison is equal, apply the secondary sort (duration or price)
      if (comparisonResult === 0) {
        if (sortBy === "duration") {
          comparisonResult =
            priceSortOrder === "asc" ? aPrice - bPrice : bPrice - aPrice;
        } else if (sortBy === "price") {
          comparisonResult =
            durationSortOrder === "asc"
              ? aDuration - bDuration
              : bDuration - aDuration;
        }
      }

      return comparisonResult;
    });

    setSortedFlights(sorted);
  };

  // Handle sort order change for duration
  const toggleDurationSortOrder = () => {
    setDurationSortOrder(durationSortOrder === "asc" ? "desc" : "asc");
  };

  // Handle sort order change for price
  const togglePriceSortOrder = () => {
    setPriceSortOrder(priceSortOrder === "asc" ? "desc" : "asc");
  };

  // Handle priority change (Duration or Price)
  const handlePriorityChange = (
    event: SelectChangeEvent<"duration" | "price">
  ) => {
    setSortBy(event.target.value as "duration" | "price");
  };

  useEffect(() => {
    if (response) {
      sortFlights(response);
    }
  }, [response, sortBy, durationSortOrder, priceSortOrder]);

  return (
    <>
      <Box sx={{ padding: 3 }}>
        <Typography variant="h4" gutterBottom>
          Available Flights
        </Typography>

        {/* Sorting and Priority Selection */}
        <Grid container spacing={2} sx={{ marginBottom: 2 }}>
          <Grid item xs={12} sm={4}>
            <Button
              variant="outlined"
              onClick={toggleDurationSortOrder}
              endIcon={durationSortOrder === "asc" ? "ASC" : "DESC"}
              fullWidth
            >
              Sort by Duration
            </Button>
          </Grid>
          <Grid item xs={12} sm={4}>
            <Button
              variant="outlined"
              onClick={togglePriceSortOrder}
              endIcon={priceSortOrder === "asc" ? "ASC" : "DESC"}
              fullWidth
            >
              Sort by Price
            </Button>
          </Grid>
          <Grid item xs={12} sm={4}>
            <FormControl fullWidth>
            <InputLabel id="label">Priority</InputLabel>
              <Select
              label="Priority"
                size="small"
                value={sortBy}
                onChange={handlePriorityChange}
              >
                <MenuItem value="duration">Duration</MenuItem>
                <MenuItem value="price">Price</MenuItem>
              </Select>
            </FormControl>
          </Grid>
        </Grid>

        {/* Display Flights */}
        {sortedFlights
          .slice((page - 1) * rowsPerPage, page * rowsPerPage)
          .map((flight) => (
            <FlightSummary key={flight.flightId} flight={flight} />
          ))}

        {/* Pagination */}
        <Pagination
          count={Math.ceil(sortedFlights.length / rowsPerPage)}
          page={page}
          onChange={(_e, value) => {
            setPage(value);
          }}
          sx={{ marginTop: 2, display: "flex", justifyContent: "center" }}
        />
      </Box>

      
    </>
  );
};

export default ResultsPage;
