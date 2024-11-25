import axios from "axios";
import { ResponseAirport } from "../interfaces/ResponseAirport";
import { RequestFlights } from "../interfaces/RequestFlights";
import { baseUrl } from "./config";
export const getFlights = async (request: RequestFlights) => {
  const url = "/api/v1/flights";

  let urlParams = new URLSearchParams();
  let response: ResponseAirport;
  try {
    urlParams.append("departureAitaCode", request.departureAitaCode);
    urlParams.append("arrivalAitaCode", request.arrivalAitaCode);
    urlParams.append("departureDate", request.departureDate.toISOString());
    urlParams.append("numberAdults", request.numberAdults.toString());
    urlParams.append("currencyCode", request.currencyCode);
    urlParams.append("nonStop", request.nonStop.toString());
    if (request.returnDate)
      urlParams.append("returnDate", request.returnDate.toISOString());
    if (request.sortByPrice)
      urlParams.append("sortByPrice", request.sortByPrice);
    if (request.sortByDate) urlParams.append("sortByDate", request.sortByDate);

    const { data } = await axios.get(baseUrl+url, { params: urlParams });
    console.log(data);
    response = data;
    return response;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      throw error;
    } else {
      throw new Error("different error than axios");
    }
  }
};
