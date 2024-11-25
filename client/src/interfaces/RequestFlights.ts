export interface RequestFlights {
  departureAitaCode: string;
  arrivalAitaCode: string;
  departureDate: string;
  returnDate?: string;
  numberAdults: number;
  currencyCode: string;
  nonStop: boolean;
}
