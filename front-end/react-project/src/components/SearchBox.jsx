import React from 'react';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import restaurant_list from '../data/restaurant_list'; 
export default function SearchBox() {
  return (
      <Autocomplete
      disablePortal
      id="combo-box-demo"
      options={restaurant_list}
      sx={{ width: 300 }}
     
      renderInput={(params) => <TextField {...params} label="Restaurant" />}
    />
    
  );
}

