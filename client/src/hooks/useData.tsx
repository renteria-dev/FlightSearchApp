import { createContext, useContext, useState } from "react";
import { ResponseFlights } from "../interfaces/ResponseFlights";

type DataContextProviderProps = {
  children: React.ReactNode;
};

type DataContextType = {
  response: ResponseFlights[] | undefined;
  setResponse: (response: ResponseFlights[] | undefined) => void;
  myflight: ResponseFlights | null;
  setMyFlight: (myflight: ResponseFlights) => void;
};

export const DataContext = createContext({} as DataContextType);

export const useData = () => {
  const context = useContext(DataContext);
  return context;
};

export const DataContextProvider = ({ children }: DataContextProviderProps) => {
  const [response, setResponse] = useState<ResponseFlights[] | undefined>();
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
