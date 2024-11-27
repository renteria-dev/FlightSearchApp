import { SnackbarProvider } from "notistack";
import SearchPage from "./SearchPage";
import { DataContextProvider } from "../hooks/useData";
import ResultsPage from "./ResultsPage";
import DetailsPage from "./DetailsPage";

const MainPage = () => {
  return (
    <>
      <DataContextProvider>
        <SnackbarProvider>
          <SearchPage />
        </SnackbarProvider>
        <ResultsPage />
        <DetailsPage />
      </DataContextProvider>
    </>
  );
};

export default MainPage;
