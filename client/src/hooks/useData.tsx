import { createContext, useContext, useState } from "react";
import { ResponseFlights } from "../interfaces/ResponseFlights";

type DataContextProviderProps = {
  children: React.ReactNode;
};

type DataContextType = {
  response: ResponseFlights[];
  setResponse: (response: ResponseFlights[]) => void;
  myflight: ResponseFlights | null;
  setMyFlight: (myflight: ResponseFlights) => void;
};

export const DataContext = createContext({} as DataContextType);

export const useData = () => {
  const context = useContext(DataContext);
  return context;
};

export const DataContextProvider = ({ children }: DataContextProviderProps) => {
  const [response, setResponse] = useState<ResponseFlights[]>([]);
  const [myflight, setMyFlight] = useState<ResponseFlights | null>(null);
  return (
    <DataContext.Provider
      value={{
        response,
        setResponse,
        myflight,
        setMyFlight,
      }}
    >
      {children}
    </DataContext.Provider>
  );
};
