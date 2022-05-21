import React from "react";
import Footer from "./components/Footer";
import NavigationBar from "./components/NavigationBar";
import { Button } from "@material-ui/core";
import { Link } from "react-router-dom";
import SearchBox from "./components/SearchBox";
function StartingPage() {
  return (
    <div>
      <NavigationBar></NavigationBar>
      <h1>Restaurant Reservation System</h1>
      <p>
        <SearchBox />
        <Button color="secondary" variant="contained" component={Link} to="/search">Search</Button>
      </p>
      <Footer />
    </div>
  );
}

export default StartingPage;
