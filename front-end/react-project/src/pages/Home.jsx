import React from "react";
import Footer from "../components/Footer";
import NavigationBar from "../components/NavigationBar";
import { Button } from "@material-ui/core";
import { Link } from "react-router-dom";
import SearchBox from "../components/SearchBox";
import Map from "../components/Map";
import {Container} from "@mui/material";
import FilterBar from "../components/FilterBar";

const Home = () => {
  const query = "lol";
  return (
    <div>
      <NavigationBar></NavigationBar>
      <div className="content">
      <Container maxWidth="xl">
        <div className="title-wrapper">
          <h1 className="title">Restaurant Reservation System</h1>
          <div className="sub-title">
            Search, reservate, and cancel within a few clicks. A project created
            by five TUM-Students.
          </div>
          <SearchBox />
          <Button
            color="secondary"
            variant="contained"
            component={Link}
            to={`/search/${query}`}
          >
            Search
          </Button>
        </div>

          <div className="filterbar">
            <FilterBar />
        </div>

        <div className="map">
          <Map />
        </div>
      </Container>
      </div>
      <Footer />
    </div>
  );
};

export default Home;
