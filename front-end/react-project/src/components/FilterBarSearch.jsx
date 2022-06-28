import {
    Select,
    MenuItem,
    Box,
    FormControl,
    InputLabel,
    Typography,
    Rating,
    Grid,
    Slider, TextField
} from "@mui/material";
import React, {useEffect} from "react";
import {DateTimePicker} from "@mui/x-date-pickers";
import {LocalizationProvider} from "@mui/x-date-pickers/LocalizationProvider";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";
import Axios from "axios";

function SelectPreisklasse(props) {
    const [preisklasse, setPreisklasse] = React.useState("");

    const handleChange = (event) => {
        setPreisklasse(event.target.value);
        props.callback(event.target.value);
    }

    return (
        <Box sx={{minWidth:100}}>
            <FormControl>
                <InputLabel >Price</InputLabel>
                <Select labelId="preisklasse" label="Preisklasse" value={preisklasse} onChange={handleChange} sx={{ width: 228 }}>
                    <MenuItem value={"0"}>All</MenuItem>
                    <MenuItem value={"1"}>€</MenuItem>
                    <MenuItem value={"2"}>€€</MenuItem>
                    <MenuItem value={"3"}>€€€</MenuItem>
                    <MenuItem value={"4"}>€€€€</MenuItem>
                </Select>
            </FormControl>
        </Box>
    );
}

function SelectCategory(props) {
    const [category, setCategory] = React.useState("");
    const [types, setTypes] = React.useState(() => {
        Axios.get(`http://localhost:8080/restaurants/getRestaurantTypes`)
            .then((res) => {
                setTypes(res.data);
            })
            .catch((err) => console.log(err));
    });

    const handleChange = (event) => {
        setCategory(event.target.value);
        props.callback(event.target.value);
    }

    const getCategory = (type) => {
        var typeStr = type.toLocaleString();
        typeStr = typeStr.replaceAll("_", " ");
        return typeStr[0] + typeStr.slice(1).toLowerCase();
    }

    if (types === undefined) {
        return (
            <Box sx={{minWidth:150}}>
                <FormControl>
                    <InputLabel>Category</InputLabel>
                    <Select labelId="category" label="Category" value={category} onChange={handleChange} sx={{ width: 228 }}>
                        <MenuItem value={"ALL"}>All</MenuItem>
                    </Select>
                </FormControl>
            </Box>
        );
    } else {
        return (
            <Box sx={{minWidth:150}}>
                <FormControl>
                    <InputLabel>Category</InputLabel>
                    <Select labelId="category" label="Category" value={category} onChange={handleChange} sx={{ width: 228 }}>
                        <MenuItem value={"ALL"}>All</MenuItem>
                        {types.map(type => (
                            <MenuItem value={type}>{getCategory(type)}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </Box>
        );
    }
}

function ReservationTimePicker(props) {
    const [value, setValue] = React.useState(null);

    const handleChange = (date) => {
        setValue(date);
        props.callback(date);
    }

    return (
        <Box sx={{minWidth:160}}>
            <LocalizationProvider dateAdapter={AdapterDateFns}>
                <DateTimePicker
                    ampm={false}
                    label="Date&Time picker"
                    value={value}
                    onChange={handleChange}
                    renderInput={(params) => <TextField {...params} sx={{ width: 228 }}/>}
                />
            </LocalizationProvider>
        </Box>

    )
}

export default function FilterBarSearch(props) {
        const [filters, setFilters] = React.useState(props.filter);

      useEffect(() => {
          props.filterCallback(filters);
      }, [filters])

    const handleCategoryChange = (newCategory) => {
        setFilters({
            ...filters,
            category: newCategory
        });
    }

    const handlePriceChange = (newPrice) => {
        setFilters({
            ...filters,
            price: newPrice
        });
    }

    const handleRatingChange = (_event, newRating) => {
        setFilters({
            ...filters,
            rating: newRating
        });
    }

    const handlePersonsChange = (_event, newPersons) => {
          setFilters({
            ...filters,
            persons: _event.target.value
        });
    }

    const handleTimeChange = (newTime) => {
          const d = new Date(newTime);
          var hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
          var minutes = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
          var month = (d.getMonth()+1) < 10 ? "0" + (d.getMonth()+1) : (d.getMonth()+1);
          var day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
          setFilters({
                ...filters,
                time: hours + ":" + minutes + "_" + d.getFullYear() + "-" + month + "-" + day
          })
    }

    return (
            <div>
                <Grid container spacing={3} justifyContent="space-around">
                    <Grid item xs={12} sm={6} md={4} lg={2}>
                        <SelectCategory callback={handleCategoryChange}/>
                    </Grid>
                    <Grid item xs={12} sm={6} md={4} lg={2}>
                        <SelectPreisklasse callback={handlePriceChange}/>
                    </Grid>
                    <Grid item xs={12} sm={6} md={4} lg={2}>
                        <Box>
                            <Typography>Rating (mind.)</Typography>
                            <Rating onChange={handleRatingChange} />
                        </Box>
                    </Grid>

                    <Grid item xs={12} sm={6} md={4} lg={2}>
                        <Box>
                            <ReservationTimePicker callback={handleTimeChange} />
                        </Box>
                    </Grid>

                    <Grid item xs={12} sm={6} md={4} lg={2}>
                        <Box>
                            <TextField id="persons" label="Persons" type="number" defaultValue={2} onChange={handlePersonsChange} sx={{width: 228}}
                                       InputLabelProps={{
                                           shrink: true,
                                       }} InputProps={{
                                inputProps:
                                    {min:0,max: 10}
                            }}
                            />
                        </Box>
                    </Grid>
                </Grid>
            </div>
        )
    }
