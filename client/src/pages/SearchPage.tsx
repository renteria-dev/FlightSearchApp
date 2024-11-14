import { alpha, Box } from "@mui/material";
import DropBox from "../components/DropBox";
import { lightBlue } from "@mui/material/colors";
import DateBox from "../components/DateBox";
const SearchPage = () => {
  const dropdownOptions = ["Option 1", "Option 2", "Option 3"];
  const dateboxOptions = ["Departure Date", "Arrival Date"];

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Box
        sx={{
          padding: 4,
          borderRadius: 2,
          backgroundColor: alpha(lightBlue["A700"], 0.3),
          boxShadow: 3,
          textAlign: "center",
          width: "700px",
        }}
      >
        <DropBox options={dropdownOptions} label="Departure Airport" />

        <DropBox options={dropdownOptions} label="Arrival Airport" />

        <DateBox options={dateboxOptions} />

        <DropBox options={dropdownOptions} label="Currency" />
      </Box>
    </Box>
  );
};

export default SearchPage;
