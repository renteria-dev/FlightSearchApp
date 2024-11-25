import axios from "axios";
import { ResponseAirport } from "../interfaces/ResponseAirport";
import { baseUrl,port } from "./config";
export const getAirports = async (keyword: string) => {
  const apiUrlWithPort = `${baseUrl}:${port}`; 

  const url = "/api/v1/airports";

  let urlParams = new URLSearchParams();
  let response: ResponseAirport;
  try {
    urlParams.append("keyword", keyword);
    const { data } = await axios.get(apiUrlWithPort + url, { params: urlParams });
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
