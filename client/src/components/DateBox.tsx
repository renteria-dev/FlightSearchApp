import { Typography } from "@mui/material";
import { LocalizationProvider, DatePicker } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { useState } from "react";
import dayjs from "dayjs";

interface DateBoxProps {
  options: string[];
}

const DateBox = ({ options }: DateBoxProps) => {
  const [departureDate, setDepartureDate] = useState<dayjs.Dayjs | null>(null);
  const [arrivalDate, setArrivalDate] = useState<dayjs.Dayjs | null>(null);

  return (
    <>
      <div
        style={{
          display: "flex",
          alignItems: "baseline",
        }}
      >
        <Typography variant="h6" align="right" width={"33%"} padding={"1rem"}>
          {options[0]}
        </Typography>
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DatePicker
            label="Departure Date"
            value={departureDate}
            onChange={(newDate) => setDepartureDate(newDate)}
          />
        </LocalizationProvider>
      </div>

      <div
        style={{
          display: "flex",
          alignItems: "baseline",
        }}
      >
        <Typography variant="h6" align="right" width={"33%"} padding={"1rem"}>
          {options[1]}
        </Typography>
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DatePicker
            label="Arrival Date"
            value={arrivalDate}
            onChange={(newDate) => setArrivalDate(newDate)}
            minDate={departureDate || dayjs()} 
          />
        </LocalizationProvider>
      </div>
    </>
  );
};

export default DateBox;
