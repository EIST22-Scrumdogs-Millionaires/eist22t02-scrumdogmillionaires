import React from "react";
import Footer from "./components/Footer";
import NavigationBar from "./components/NavigationBar";
import { Button } from "@material-ui/core";
import { Link } from "react-router-dom";
import SearchBox from "./components/SearchBox";
import map from "./images/map-test.png";
function Home() {
  return (
    <div>
      <NavigationBar></NavigationBar>
      <div className="search-wrapper">
      <h1 className="title">Restaurant Reservation System</h1>
     
<h3 className="sub-title">Search, reservate, and cancel within a few clicks. A project created by five TUM-Students.</h3>
        <SearchBox/>
        <Button color="secondary" variant="contained" component={Link} to="/search">Search</Button>
      </div>
      <img src={map} alt="map" className="map"></img>
      <Footer />
    </div>
  );
}

export default Home;