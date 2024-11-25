import { useEffect, useMemo, useState } from "react";
import {
  Autocomplete,
  CircularProgress,
  debounce,
  IconButton,
  InputAdornment,
  TextField,
} from "@mui/material";
import { ClearIcon } from "@mui/x-date-pickers";

interface CustomAutocompleteProps<T> {
  value?: T | null;
  label?: string;
  placeholder?: string;
  onChange?: (input: T | null) => void; 
  fetchingFunction: (input: string) => Promise<T[]>; // busqueda personalizada
  dbTime?: number; // debounce time
  renderOptionLabel: (option: T) => string; // how to render options
}

export const CustomAutocomplete = <T extends unknown>({
  value,
  label,
  placeholder,
  onChange,
  fetchingFunction,
  dbTime = 500,
  renderOptionLabel,
}: CustomAutocompleteProps<T>) => {
  const [inputValue, setInputValue] = useState<string>("");
  const [options, setOptions] = useState<T[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [isOptionSelected, setIsOptionSelected] = useState(false);

  const fetchOptions = useMemo(
    () =>
      debounce(async (input: string) => {
        if (input.length >= 3) {
          try {
            setLoading(true);
            await fetchingFunction(input).then((data) => {
              setOptions(data);
            });
          } catch (error) {
            console.error("Error fetching data:", error);
          } finally {
            setLoading(false);
          }
        }
      }, dbTime),
    [fetchingFunction, dbTime]
  );

  useEffect(() => {
    if (!isOptionSelected) fetchOptions(inputValue);
  }, [inputValue]);

  const handleClear = () => {
    if (onChange) {
      onChange(null); // Clear the value when the clear button is clicked
      setIsOptionSelected(false);
    }
  };

  return (
    <Autocomplete
      fullWidth
      readOnly={isOptionSelected}
      filterOptions={(x) => x} // No filter
      options={options}
      value={value} // Selected value
      getOptionLabel={renderOptionLabel} // render options
      onChange={(_e, newValue) => {
        setIsOptionSelected(true);
        if (onChange) {
          onChange(newValue); // Value back to parent
        }
      }}
      noOptionsText="No options found"
      loading={loading}
      onInputChange={(_e, newInputValue) => setInputValue(newInputValue)} // input value update
      renderInput={(params) => (
        <TextField
          {...params}
          label={label}
          placeholder={placeholder}
          variant="outlined"
          slotProps={{
            input: {
              ...params.InputProps,

              endAdornment: (
                <>
                  {loading ? (
                    <CircularProgress color="inherit" size={20} />
                  ) : null}
                  {params.InputProps.endAdornment}

                  <InputAdornment position="end">
                    {value && (
                      <IconButton onClick={handleClear}>
                        <ClearIcon />
                      </IconButton>
                    )}
                  </InputAdornment>
                </>
              ),
            },
          }}
        />
      )}
    />
  );
};
