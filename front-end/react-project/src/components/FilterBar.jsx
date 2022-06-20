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
import React, {createContext, useEffect} from "react";
import {DateTimePicker} from "@mui/x-date-pickers";
import {LocalizationProvider} from "@mui/x-date-pickers/LocalizationProvider";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";

const marks = [
    {
        value: 1,
        label: "1km",
    },
    {
        value: 10,
        label: "10km",
    },
    {
        value: 20,
        label: "20km",
    },
    {
        value: 30,
        label: "30km",
    },
];

function SelectPreisklasse() {
    const [preisklasse, setPreisklasse] = React.useState("");

    const handleChange = (event) => {
        setPreisklasse(event.target.value);
    }

    return (
        <Box sx={{minWidth:100}}>
            <FormControl fullWidth>
                <InputLabel>Preisklasse</InputLabel>
                <Select labelId="preisklasse" label="Preisklasse" value={preisklasse} onChange={handleChange}>
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

    const handleChange = (event) => {
        setCategory(event.target.value);
        props.callback(event.target.value);
    }

    return (
        <Box sx={{minWidth:150}}>
            <FormControl fullWidth>
                <InputLabel>Kategorie</InputLabel>
                <Select labelId="category" label="Category" value={category} onChange={handleChange}>
                    <MenuItem value={"1"}>Italienisch</MenuItem>
                    <MenuItem value={"2"}>Amerikanisch</MenuItem>
                    <MenuItem value={"3"}>Deutsch</MenuItem>
                    <MenuItem value={"4"}>Indisch</MenuItem>
                </Select>
            </FormControl>
        </Box>
    );
}

function ReservationTimePicker() {
    const [value, setValue] = React.useState(null);

    const handleChange = (date) => {
        setValue(date);
    }

    return (
        <Box sx={{minWidth:160}}>
            <LocalizationProvider dateAdapter={AdapterDateFns}>
                <DateTimePicker
                    label="Date&Time picker"
                    value={value}
                    onChange={handleChange}
                    renderInput={(params) => <TextField {...params} />}
                />
            </LocalizationProvider>
        </Box>

    )
}

export default function FilterBar(props) {
        const [filters, setFilters] = React.useState({
            category: "",
            price: "",
            rating: "",
            distance: "",
            time: ""
        });

      useEffect(() => {
          props.filterCallback(filters);
      }, [filters])

    const handleSliderChange = (event, value) => {
        setFilters({
            ...filters,
            distance: value*1000
            }
        )
        props.filterCallback(filters);
    }

    const handleCategoryChange = (newCategory) => {
        setFilters({
            ...filters,
            category: newCategory
        });
    }

    return (
            <div>
                <Grid container spacing={3} justifyContent="space-around">
                    <Grid item xs={2}>
                        <SelectCategory callback={handleCategoryChange}/>
                    </Grid>
                    <Grid item xs={2}>
                        <SelectPreisklasse/>
                    </Grid>
                    <Grid item xs={2}>
                        <Box>
                            <Typography>Rating (mind.)</Typography>
                            <Rating/>
                        </Box>
                    </Grid>
                    <Grid item xs={2}>
                        <Box>
                            <Typography>Distanz</Typography>
                            <Slider
                                defaultValue={5} min={1} max={30} marks={marks} onChange={handleSliderChange}/>
                        </Box>
                    </Grid>

                    <Grid item xs={4}>
                        <Box>
                            <ReservationTimePicker/>
                        </Box>
                    </Grid>
                </Grid>
            </div>
        )
    }
