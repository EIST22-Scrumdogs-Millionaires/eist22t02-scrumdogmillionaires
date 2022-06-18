import React from "react";
import Footer from "../components/Footer";
import NavigationBar from "../components/NavigationBar";
import { Button } from "@material-ui/core";
import { Link } from "react-router-dom";
import Map from "../components/Map";
import { Container, TextField } from "@mui/material";
import FilterBar from "../components/FilterBar";
export default function Home() {
   
  var filters= {
      category: "",
      price: "",
      rating: "",
      distance: 1000,
      time: "",
    
  };

  const [query, handleSearchBox] = React.useState("");

  const handleFilterChange = (newFilters) => {
    filters = newFilters ;
  };

  return (
    <div>
      <NavigationBar></NavigationBar>
      <div className="content">
        <Container maxWidth="xl">
          <div className="title-wrapper">
            <h1 className="title">Restaurant Reservation System</h1>
            <div className="sub-title">
              Search, reservate, and cancel within a few clicks. A project
              created by five TUM-Students.
            </div>
            <div className="search-box">
              <div>
                {" "}
                <TextField
                  sx={{ width: 300 }}
                  required
                  value={query} onChange={(e) => handleSearchBox(e.target.value)} 
                  label="Restaurant"
                  variant="outlined"
                  color="secondary"
                />
              </div>

              <div className="search-button">
                <Button
                  color="secondary"
                  variant="contained"
                  component={Link}
                  to={`/search/${query}`}
                >
                  Search
                </Button>
              </div>
            </div>
          </div>

          <div className="filterbar">
            <FilterBar filterCallback={handleFilterChange} />
          </div>

          <div className="map">
            <Map filters={filters} />
          </div>
        </Container>
      </div>
      <Footer />
    </div>
  );
}
