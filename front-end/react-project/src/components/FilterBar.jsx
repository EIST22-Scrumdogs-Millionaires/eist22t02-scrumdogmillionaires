import {
    Select,
    MenuItem,
    Box,
    FormControl,
    InputLabel,
    Typography,
    Rating,
    Grid,
    FormControlLabel, Switch, Slider
} from "@mui/material";
import React from "react";

const marks = [
    {
        value: 5,
        label: "5km",
    },
    {
        value: 20,
        label: "20km",
    },
    {
        value: 35,
        label: "35km",
    },
];

function SelectPreisklasse() {
    const [preisklasse, setPreisklasse] = React.useState("");

    const handleChange = (event) => {
        setPreisklasse(event.target.value);
    }

    return (
        <Box sx={{minWidth:100, maxWidth:160}}>
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

function SelectCategory() {
    const [category, setCategory] = React.useState("");

    const handleChange = (event) => {
        setCategory(event.target.value);
    }

    return (
        <Box sx={{minWidth:150, maxWidth:160}}>
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

export default function FilterBar() {
        return (
            <div>
                <Grid container spacing={3} justifyContent="space-around">
                    <Grid item xs={2}>
                        <SelectCategory />
                    </Grid>
                    <Grid item xs={2}>
                        <SelectPreisklasse />
                    </Grid>
                    <Grid item xs={2}>
                        <Box>
                            <div className="rating-typography">
                                <Typography>Rating (mind.)</Typography>
                            <Rating />
                            </div>
                        </Box>
                    </Grid>
                    <Grid item xs={2}>
                        <FormControlLabel labelPlacement="top" control={<Switch />} label="Jetzt geöffnet" />
                    </Grid>
                    <Grid item xs={2}>
                        <Box>
                            <Typography>Distanz</Typography>
                            <Slider
                                defaultValue={20} min={5} max={35} marks={marks}/>
                        </Box>
                    </Grid>
                </Grid>
            </div>
        )
}

