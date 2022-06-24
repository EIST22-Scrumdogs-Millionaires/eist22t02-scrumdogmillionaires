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

function SelectPreisklasse(props) {
    const [preisklasse, setPreisklasse] = React.useState("");

    const handleChange = (event) => {
        setPreisklasse(event.target.value);
        props.callback(event.target.value);
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
                    <MenuItem value={"1"}>Chinese</MenuItem>
                    <MenuItem value={"2"}>German</MenuItem>
                    <MenuItem value={"3"}>Bavarian</MenuItem>
                    <MenuItem value={"4"}>Taiwanese</MenuItem>
                    <MenuItem value={"5"}>Italian</MenuItem>
                    <MenuItem value={"6"}>Fast Food</MenuItem>
                    <MenuItem value={"7"}>Pizza</MenuItem>
                    <MenuItem value={"8"}>Kebab</MenuItem>
                    <MenuItem value={"9"}>Gourmet</MenuItem>
                </Select>
            </FormControl>
        </Box>
    );
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
            category: props.filters.category,
            price: props.filters.price,
            rating: props.filters.rating,
            distance: props.filters.distance,
            time: props.filters.time,
            persons: props.filters.persons,
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
    }

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

    const handleRatingChange = (event, newRating) => {
        setFilters({
            ...filters,
            rating: newRating
        });
    }

    const handlePersonsChange = (event, newPersons) => {
        setFilters({
            ...filters,
            persons: newPersons
        });
    }

    const handleTimeChange = (newTime) => {
          const d = new Date(newTime);
          var persons = filters.persons;
          var hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
          var minutes = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
          var month = (d.getMonth()+1) < 10 ? "0" + (d.getMonth()+1) : (d.getMonth()+1);
          var day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
          setFilters({
                ...filters,
                time: hours + ":" + minutes + "_" + d.getFullYear() + "-" + month + "-" + day+"_"+persons
          })
    }

    return (
            <div>
                <Grid container spacing={3} justifyContent="space-around">
                    <Grid item xs={2}>
                        <SelectCategory callback={handleCategoryChange}/>
                    </Grid>
                    <Grid item xs={2}>
                        <SelectPreisklasse callback={handlePriceChange}/>
                    </Grid>
                    <Grid item xs={2}>
                        <Box>
                            <Typography>Rating (mind.)</Typography>
                            <Rating onChange={handleRatingChange} />
                        </Box>
                    </Grid>
                    <Grid item xs={2}>
                        <Box>
                            <Typography>Distanz</Typography>
                            <Slider
                                defaultValue={5} min={1} max={30} marks={marks} onChange={handleSliderChange}/>
                        </Box>
                    </Grid>

                    <Grid item xs={3}>
                        <Box>
                            <ReservationTimePicker callback={handleTimeChange} />
                        </Box>
                    </Grid>

                    <Grid item xs={1}>
                        <Box>
                            <TextField id="persons" label="Personen" type="number" value={filters.persons}
                                       InputLabelProps={{
                                           shrink: true,
                                       }} InputProps={{
                                inputProps:
                                    {min:0,max: 10}
                            }}
                                       onChange={handlePersonsChange} />
                        </Box>
                    </Grid>
                </Grid>
            </div>
        )
    }
