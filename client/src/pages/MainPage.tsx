import { SnackbarProvider } from "notistack";
import ResultsPage from "./ResultsPage";
import SearchPage from "./SearchPage";

const MainPage = () => {
  return (
    <>
      <SnackbarProvider>
        <SearchPage />
        <ResultsPage/>
      </SnackbarProvider>
    </>
  );
};

export default MainPage;
