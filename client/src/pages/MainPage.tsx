import { SnackbarProvider } from "notistack";
import SearchPage from "./SearchPage";

const MainPage = () => {
  return (
    <>
      <SnackbarProvider>
        <SearchPage />
        {/* <ResultsPage/> */}
      </SnackbarProvider>
    </>
  );
};

export default MainPage;
