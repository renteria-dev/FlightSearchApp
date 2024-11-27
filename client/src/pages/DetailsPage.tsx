import { Button } from "@mui/material";
import FlightDetails from "../components/FlightDetails";
import { useData } from "../hooks/useData";
import { useNavigate } from "react-router-dom";

const DetailsPage = () => {
  const { myflight } = useData();
  const navigate = useNavigate();
  return (
    <>
      <Button
        variant="contained"
        onClick={() => {
          navigate("/resultsPage");
        }}
        sx={{
          position: "fixed", // Fix the button position on the screen
          top: "20px", // 20px from the bottom of the screen
          left: "20px", // 20px from the right side of the screen
          zIndex: 1000, // Ensure the button is on top of other elements
          padding: "10px 20px", // Adjust padding for better appearance
        }}
      >
        Back to Results
      </Button>
      {myflight && <FlightDetails flight={myflight} />}
    </>
  );
};

export default DetailsPage;
