import { useState } from "react";
import {
  alpha,
  Box,
  Checkbox,
  CircularProgress,
  FormControl,
  FormControlLabel,
  MenuItem,
  Select,
  TextField,
  Typography,
  Divider,
} from "@mui/material";
import { lightBlue } from "@mui/material/colors";
import { LoadingButton } from "@mui/lab";

import { Airport } from "../interfaces/ResponseAirport";
import { CustomAutocomplete } from "../components/CustomAutocomplete";
import { getAirports } from "../api/getAirports";

import dayjs from "dayjs";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DatePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { ResponseFlights } from "../interfaces/ResponseFlights";
import { getFlights } from "../api/getFlights";
import { RequestFlights } from "../interfaces/RequestFlights";
import { useSnackbar } from "notistack";
import { validateInputs } from "../utils/validation";
import ResultsPage from "./ResultsPage";

const SearchPage = () => {
  const { enqueueSnackbar } = useSnackbar();
  const [data, setData] = useState<ResponseFlights[] | null>();

  const currencies = ["USD", "MXN", "EUR"];

  const [departureAirport, setDepartureAirport] = useState<Airport | null>(
    null
  );
  const [arrivalAirport, setArrivalAirport] = useState<Airport | null>(null);

  const [departureDate, setDepartureDate] = useState<dayjs.Dayjs | null>(null);
  const [returnDate, setReturnDate] = useState<dayjs.Dayjs | null>(null);

  const [selectedCurrency, setSelectedCurrency] = useState(currencies[0]);
  const [numberAdults, setNumberAdults] = useState<number>(1);
  const [nonStop, setNonStop] = useState<boolean>(false);

  const [loadingButton, setLoadingButton] = useState<boolean>(false);

  const fetchAirports = async (query: string): Promise<Airport[]> => {
    try {
      const data = await getAirports(query);
      return data.locations || [];
    } catch (error) {
      console.error("Error fetching airports:", error);
      return [];
    }
  };

  const fetchFlights = async (
    query: RequestFlights
  ): Promise<ResponseFlights[]> => {
    try {
      const data = await getFlights(query);
      return data;
    } catch (error) {
      console.error("Error fetching flights:", error);
      throw new Error("Failed to fetch flights");
    }
  };

  const renderAirportLabel = (option: Airport) => {
    return `${option.iataCode} (${option.name}, ${option.cityName})`;
  };

  const handleSearch = async () => {
    const isValid = await validateInputs(
      departureAirport,
      arrivalAirport,
      numberAdults,
      departureDate,
      returnDate,
      selectedCurrency,
      enqueueSnackbar
    );
    if (isValid) {
      console.log(departureDate?.toDate());

      const requestPayload: RequestFlights = {
        departureAitaCode: departureAirport?.iataCode || "",
        arrivalAitaCode: arrivalAirport?.iataCode || "",
        departureDate: departureDate?.format("YYYY-MM-DD") || "",
        returnDate: returnDate?.format("YYYY-MM-DD"),
        numberAdults,
        currencyCode: selectedCurrency,
        nonStop,
      };

      try {
        setData(null);
        setLoadingButton(true);
        await fetchFlights(requestPayload).then((response) => {
          setData(response);
          setLoadingButton(false);
        });
      } catch (error) {
        setLoadingButton(false);
        enqueueSnackbar("Error Ocurred", { variant: "error" });
      }
    }
  };

  return (
    <>
      <Box
        sx={{
          display: "flex",
          justifyContent: "center",
          alignItems: "flex-start",
          minHeight: "100vh",
        }}
      >
        <Box
          sx={{
            padding: 4,
            borderRadius: 2,
            backgroundColor: alpha(lightBlue["A700"], 0.3),
            boxShadow: 3,
            width: "700px",
            display: "flex",
            flexDirection: "column",
            gap: 3,
          }}
        >
          <Typography variant="h4"  fontWeight={800} letterSpacing={2} >
            FLIGTH SEARCH APP
          </Typography>
          <Divider variant="fullWidth" orientation="horizontal" />
          <Box
            sx={{ display: "flex", flexDirection: "row", alignItems: "center" }}
          >
            <Typography
              variant="subtitle1"
              sx={{ width: "200px", textAlign: "right", marginRight: 2 }}
            >
              Departure Airport
            </Typography>
            <CustomAutocomplete
              value={departureAirport}
              label="Select an airport"
              placeholder="Search for an airport"
              onChange={setDepartureAirport}
              fetchingFunction={fetchAirports}
              renderOptionLabel={renderAirportLabel}
              dbTime={500}
            />
          </Box>

          <Box
            sx={{ display: "flex", flexDirection: "row", alignItems: "center" }}
          >
            <Typography
              variant="subtitle1"
              sx={{ width: "200px", textAlign: "right", marginRight: 2 }}
            >
              Arrival Airport
            </Typography>
            <CustomAutocomplete
              value={arrivalAirport}
              label="Select an airport"
              placeholder="Search for an airport"
              onChange={setArrivalAirport}
              fetchingFunction={fetchAirports}
              renderOptionLabel={renderAirportLabel}
              dbTime={500}
            />
          </Box>

          <Box
            sx={{ display: "flex", flexDirection: "row", alignItems: "center" }}
          >
            <Typography
              variant="subtitle1"
              sx={{ width: "200px", textAlign: "right", marginRight: 2 }}
            >
              Departure Date
            </Typography>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker
                label="Departure Date"
                value={departureDate}
                onChange={(newDate) => setDepartureDate(newDate)}
                minDate={dayjs()}
                sx={{ flex: 1 }}
              />
            </LocalizationProvider>
          </Box>

          <Box
            sx={{ display: "flex", flexDirection: "row", alignItems: "center" }}
          >
            <Typography
              variant="subtitle1"
              sx={{ width: "200px", textAlign: "right", marginRight: 2 }}
            >
              Return Date
            </Typography>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker
                label="Return Date"
                value={returnDate}
                onChange={(newDate) => setReturnDate(newDate)}
                minDate={
                  departureDate != null && dayjs() < departureDate
                    ? departureDate
                    : dayjs()
                }
                sx={{ flex: 1 }}
              />
            </LocalizationProvider>
          </Box>

          <Box
            sx={{ display: "flex", flexDirection: "row", alignItems: "center" }}
          >
            <Typography
              variant="subtitle1"
              sx={{ width: "200px", textAlign: "right", marginRight: 2 }}
            >
              Currency
            </Typography>
            <FormControl>
              <Select
                value={selectedCurrency}
                onChange={(e) => setSelectedCurrency(e.target.value)}
              >
                {currencies.map((c) => (
                  <MenuItem key={c} value={c}>
                    {c}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          </Box>

          <Box
            sx={{ display: "flex", flexDirection: "row", alignItems: "center" }}
          >
            <Typography
              variant="subtitle1"
              sx={{ width: "200px", textAlign: "right", marginRight: 2 }}
            >
              Adults:
            </Typography>
            <TextField
              type="number"
              value={numberAdults}
              onChange={(e) => {
                setNumberAdults(Number(e.target.value));
              }}
              InputProps={{
                inputProps: { min: 1, max: 9 },
              }}
              variant="outlined"
            />
          </Box>

          <Box
            sx={{ display: "flex", flexDirection: "row", alignItems: "center" }}
          >
            <Typography
              variant="subtitle1"
              sx={{ width: "200px", textAlign: "right", marginRight: 2 }}
            ></Typography>
            <FormControlLabel
              label="Non-stop"
              control={
                <Checkbox
                  checked={nonStop}
                  onChange={(e) => setNonStop(e.target.checked)}
                  color="primary"
                />
              }
            />
          </Box>

          <Box sx={{ display: "flex", justifyContent: "flex-end" }}>
            <LoadingButton
              loading={loadingButton}
              loadingIndicator={<CircularProgress size={24} />}
              variant="contained"
              onClick={handleSearch}
            >
              Search
            </LoadingButton>
          </Box>
        </Box>
      </Box>

      {data !== null && data !== undefined && <ResultsPage data={data} />}
    </>
  );
};

export default SearchPage;
