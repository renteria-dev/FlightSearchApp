import {
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Typography,
} from "@mui/material";
import { useState } from "react";

interface DropdownProps {
  options: string[];
  label: string;
}

const DropBox = ({ options, label }: DropdownProps) => {
  const [selectedOption, setSelectedOption] = useState<string>();

  return (
    <div
      style={{
        display: "flex",
        alignItems: "baseline"
      }}
    >
      <Typography variant="h6"  align="right" width={"33%"} padding={"1rem"}>
        {label}
      </Typography>
      <FormControl style={{width:"66%"}}>
        <InputLabel >{label}</InputLabel>
        <Select
          labelId="dropdown-label"
          value={selectedOption}
          onChange={(e) => setSelectedOption(e.target.value)}
          label={label}
          

        >
          {options.map((option) => (
            <MenuItem key={option} value={option}>
              {option}
            </MenuItem>
          ))}
        </Select>
        <Typography variant="body1" sx={{ marginTop: 2 }}>
          You selected: {selectedOption}
        </Typography>
      </FormControl>
    </div>
  );
};
export default DropBox;
