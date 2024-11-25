import axios from "axios";
import { RequestFlights } from "../interfaces/RequestFlights";
import { baseUrl, port } from "./config";
import { ResponseFlights } from "../interfaces/ResponseFlights";
export const getFlights = async (request: RequestFlights) => {
  const apiUrlWithPort = `${baseUrl}:${port}`; 

  const url = "/api/v1/flights";

  let urlParams = new URLSearchParams();
  let response: ResponseFlights[];
  try {
    urlParams.append("departureAitaCode", request.departureAitaCode);
    urlParams.append("arrivalAitaCode", request.arrivalAitaCode);
    urlParams.append("departureDate", request.departureDate);
    urlParams.append("numberAdults", request.numberAdults.toString());
    urlParams.append("currencyCode", request.currencyCode);
    urlParams.append("nonStop", request.nonStop.toString());
    if (request.returnDate) urlParams.append("returnDate", request.returnDate);

    const { data } = await axios.get(apiUrlWithPort + url, {
      params: urlParams,
    });
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
