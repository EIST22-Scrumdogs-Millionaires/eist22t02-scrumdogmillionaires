import React, {useEffect} from "react";
import Footer from "../components/Footer";
import NavigationBar from "../components/NavigationBar";
import { Button } from "@material-ui/core";
import { Link } from "react-router-dom";
import Map from "../components/Map";
import { Container, TextField } from "@mui/material";
import FilterBar from "../components/FilterBar";
import Axios from "axios";

export default function Home() {

  const [query, handleSearchBox] = React.useState("");
  const [restaurants, setRestaurants] = React.useState( () => {
        Axios.get(`http://localhost:8080/restaurants/search/all`)
            .then((res) => {
              setRestaurants(res.data);
            })
            .catch((err) => console.log(err));
  }
  );

  const [location, setLocation] = React.useState({
    lat: 48.142166098,
    lng: 11.56745
  });

  const [filters, setFilters] = React.useState({
    category: "",
    price: "",
    rating: "",
    distance: 2000,
    time: "",
    persons: 2,
  });

  const handleLocationChange = (newLocation) => {
    setLocation(newLocation);
    var filterTypes = createFilterSting(filters)
    fetchRestaurants(filterTypes);
  }

  const handleFilterChange = (newFilters) => {
    setFilters(newFilters);
    var filterTypes = createFilterSting(newFilters)
    fetchRestaurants(filterTypes);
  };

  const createFilterSting = (filters) => {
    var filterTypes=[];
    for (let [key, value] of Object.entries(filters)) {
      switch (key) {
        case "category": {
          if (value !== "" && value !== "ALL") {
            filterTypes.push("T_"+value);
          }
          break;
        }
        case "price": {
            if (value !== "" && value !== "0") {
              filterTypes.push("P_"+value);
            }
          break;
        }
        case "rating": {
            if (value !== "") {
              filterTypes.push("A_"+value);
            }
          break;
        }
        case "distance": {
          filterTypes.push("D_" + location.lng + "_" + location.lat + "_" + (value / 1000));
          break;
        }
        case "time": {
          if (value !== "") {
            filterTypes.push("F_"+value + "_" + filters.persons);
          }
          break;
        }
      }
    }
    //console.log(filterTypes.join("@"));
    return filterTypes.length < 2 ? filterTypes[0] : filterTypes.join("@");
  }


  const fetchRestaurants = (filterString) => {
    Axios.get(`http://localhost:8080/restaurants/search/all/${filterString}/0`)
        .then((res) => {
          setRestaurants(res.data);
        })
        .catch((err) => console.log(err));
  }

  if (typeof (restaurants) !== "undefined" && typeof(filters) !== "undefined") {
    if (restaurants.length === 10) {
      fetchRestaurants(createFilterSting(filters));
    }
  }

  return (
    <div>
      <NavigationBar />
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
                  to={`/search/${!query ? "topten" : query}`}
                >
                  Search
                </Button>
              </div>
            </div>
          </div>

          <div className="filterbar">
            <FilterBar  filters={filters} filterCallback={handleFilterChange}/>
          </div>

          <div className="map">
            <Map filters={filters} restaurants={restaurants} location={location} locationCallback={handleLocationChange}/>
          </div>
        </Container>
      </div>
      <Footer />
    </div>
  );
}
