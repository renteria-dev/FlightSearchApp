export interface ResponseAirport{
    locations: Airport[];
}

export interface Airport {
    iataCode:    string;
    name:        string;
    cityName:    string;
    countryName: string;
}
