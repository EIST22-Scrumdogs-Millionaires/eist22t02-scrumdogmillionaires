import React from "react";
import Footer from "../components/Footer";
import NavigationBar from "../components/NavigationBar";
import { Button } from "@material-ui/core";
import { Link } from "react-router-dom";
import SearchBox from "../components/SearchBox";
import Map from "../components/Map";
import { Grid, Box, Item, Container } from "@mui/material";

const Home = () => {
  const query = "lol";
  return (
    <div>
      <NavigationBar></NavigationBar>

      <Container maxWidth="lg">
        <div className="title-wrapper">
          <h1 className="title">Restaurant Reservation System</h1>
          <h3 className="sub-title">
            Search, reservate, and cancel within a few clicks. A project created
            by five TUM-Students.
          </h3>
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

        <div className="map">
          <Map />
        </div>
      </Container>
      <Footer />
    </div>
  );
};

export default Home;
