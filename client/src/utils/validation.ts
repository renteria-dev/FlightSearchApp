import dayjs from "dayjs";
import { Airport } from "../interfaces/ResponseAirport";
import { object, string, number, date, ValidationError } from "yup";

export const validationSchema = object().shape({
  departureAirportCode: string()
    .length(3, "The departure IATA code must be exactly 3 characters.")
    .matches(/^[A-Z]{3}$/, "The departure IATA code must be in uppercase.")
    .required("The departure IATA code is required."),
  arrivalAirportCode: string()
    .length(3, "The arrival IATA code must be exactly 3 characters.")
    .matches(/^[A-Z]{3}$/, "The arrival IATA code must be in uppercase.")
    .required("The arrival IATA code is required."),
  numberAdults: number()
    .min(1, "The number of adults must be at least 1.")
    .max(9, "The number of adults must be at lees than 10.")
    .required("The number of adults is required."),
  departureDate: date().required("The departure date is required.").nullable(),
  returnDate: date().nullable(),
  selectedCurrency: string()
    .oneOf(["MXN", "EUR", "USD"], "The currency code must be MXN, USD, or EUR.")
    .required("The currency code is required."),
});

export const validateInputs = async (
  departureAirport: Airport | null,
  arrivalAirport: Airport | null,
  numberAdults: number,
  departureDate: dayjs.Dayjs | null,
  returnDate: dayjs.Dayjs | null,
  selectedCurrency: string,
  enqueueSnackbar: (message: string, options?: object) => void
): Promise<boolean> => {
  const data = {
    departureAirportCode: departureAirport?.iataCode || "",
    arrivalAirportCode: arrivalAirport?.iataCode || "",
    numberAdults,
    departureDate: departureDate?.toDate() || null,
    returnDate: returnDate?.toDate() || null,
    selectedCurrency,
  };

  try {
    await validationSchema.validate(data, { abortEarly: false });

    return true;
  } catch (err) {
    if (err instanceof ValidationError) {
      enqueueSnackbar(err.inner[0].message, { variant: "error" });
    }

    return false;
  }
};
