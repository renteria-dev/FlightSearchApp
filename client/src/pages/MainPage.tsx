import { SnackbarProvider } from "notistack";
import SearchPage from "./SearchPage";
import { useData } from "../hooks/useData";
import ResultsPage from "./ResultsPage";
import DetailsPage from "./DetailsPage";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";

const MainPage = () => {
  const { response, myflight } = useData();
  return (
    <Router>
      <SnackbarProvider>
        <Routes>
          <Route path="/" element={<SearchPage />} />
          <Route
            path="/resultsPage"
            element={response ? <ResultsPage /> : <Navigate to="/" />}
          />

          <Route
            path="/resultsPage/details"
            element={
              myflight ? <DetailsPage /> : <Navigate to="/resultsPage" />
            }
          />
        </Routes>
      </SnackbarProvider>
    </Router>
  );
};

export default MainPage;
