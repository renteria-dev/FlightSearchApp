export interface ResponseFlights {
  flightId: string;
  departureTime: Date;
  arrivalTime: Date;
  departureAirportName: string;
  departureAirportCode: string;
  arrivalAirportName: string;
  arrivalAirportCode: string;
  airlineName: string;
  airlineCode: string;
  operatingAirlineName: null | string;
  operatingAirlineCode: null | string;
  duration: string;
  totalPrice: string;
  hasStops: boolean;
  itineraries: Itinerary[];
  travelerPricings: TravelerPricing[];
  currency: string;
  price: Price;
}

export interface Itinerary {
  segments: Segment[];
  duration: string;
}

export interface Segment {
  departure: Arrival;
  arrival: Arrival;
  carrierCode: string;
  number: string;
  duration: string;
  id: string;
  numberOfStops: number;
  aircraft: string;
  operating: null | string;
}

export interface Arrival {
  iataCode: string;
  terminal: null | string;
  at: Date;
}

export interface TravelerPricing {
  fareOption: string;
  travelerType: string;
  price: Price;
  fareDetailsBySegment: FareDetailsBySegment[];
  travelerId: string;
}

export interface FareDetailsBySegment {
  cabin: string;
  fareBasis: string;
  brandedFare: null | string;
  brandedFareLabel: null | string;
  amenities: Amenity[] | null;
  segmentId: string;
  class: string;
}

export interface Amenity {
  amenityType: string;
  description: string;
  isChargeable: boolean;
}

export interface Fee {
  amount: string;
  type: string;
}

export interface Price {
  currency: string;
  total: string;
  base: string;
  fees: Fee[];
  grandTotal: string;
  additionalServices?: Fee[];
}