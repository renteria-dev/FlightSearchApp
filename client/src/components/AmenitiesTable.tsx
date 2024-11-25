import {
  Box,
  Typography,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
} from "@mui/material";
import { Amenity } from "../interfaces/ResponseFlights";

const AmenitiesTable = ({ amenities }: { amenities: Amenity[] }) => {
  return (
    amenities &&
    amenities.length > 0 && (
      <Box sx={{ marginTop: 1 }}>
        <Typography variant="body2" sx={{ marginBottom: 1 }}>
          Amenities:
        </Typography>
        <TableContainer component={Paper}>
          <Table size="small">
            <TableHead>
              <TableRow>
                <TableCell>Amenity</TableCell>
                <TableCell align="left">Chargeable</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {amenities.map((amenity: Amenity, index: number) => (
                <TableRow key={index}>
                  <TableCell>
                    {amenity.amenityType}
                    <br />
                    {amenity.description}
                  </TableCell>
                  <TableCell>{amenity.isChargeable ? "Yes" : "No"}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Box>
    )
  );
};

export default AmenitiesTable;
