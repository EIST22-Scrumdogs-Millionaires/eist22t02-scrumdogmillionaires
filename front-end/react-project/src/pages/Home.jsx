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
        Axios.get(`http://localhost:8080/restaurants/getTopTen`)
            .then((res) => {
              setRestaurants(res.data);
            })
            .catch((err) => console.log(err));
  }
  );
  const [filters, setFilters] = React.useState({
    category: "",
    price: "",
    rating: "",
    distance: 2000,
    time: "",
    persons: 2,
  });

  const handleFilterChange = (newFilters) => {
    setFilters(newFilters);
    var filterTypes = [];
    for (let [key, value] of Object.entries(newFilters)) {
      switch (key) {
        case "category": {
          filterTypes.push(`T_${value}`);
          break;
        }
        case "price": {
          filterTypes.push(`P_${value}`);
          break;
        }
        case "rating": {
          filterTypes.push(`A_${value}`);
          break;
        }
        case "distance": {
          filterTypes.push(`D_${value}`);
          break;
        }
        case "time": {
          filterTypes.push(`F_${value}`);
          break;
        }
      }
    }
  };

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
                  to={`/search/${query === "" ? "all" : query}`}
                >
                  Search
                </Button>
              </div>
            </div>
          </div>

          <div className="filterbar">
            <FilterBar  filterCallback={handleFilterChange}/>
          </div>

          <div className="map">
            <Map filters={filters} restaurants={restaurants}/>
          </div>
        </Container>
      </div>
      <Footer />
    </div>
  );
}
