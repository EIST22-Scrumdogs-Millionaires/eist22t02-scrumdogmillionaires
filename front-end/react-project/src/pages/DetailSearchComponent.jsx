import React from "react";
import { useParams } from "react-router-dom";
import Footer from "../components/Footer";
import NavigationBar from "../components/NavigationBar";
import restaurant from "../data/restaurant_detail_data.json";
import { Grid, Container } from "@mui/material";
import Map from "../images/map-test.png";
import CommentSection from "../components/CommentSection";
import TUMMensa from "../images/TUM-Mensa.jpg";
import ReservateComponent from "../components/ReservateComponent";
import IconButton from "@mui/material/IconButton";
import {default as WebsiteIcon} from '@mui/icons-material/Language';
export default function DetailSearchComponent() {
  const { id } = useParams();
  return (
    <div>
      <NavigationBar />
      
      <Container maxWidth="lg">
      <div className="title-wrapper">
        <div className="search-wrapper">
          <h1>{id}</h1>
          {restaurant.description}
        </div>
      </div>
        <div className="restaurant-infos">
          <p>
            <h3>Address:</h3>Boltzmannstraße 19, 85748 Garching bei München
          </p>
          <p>
            <h3>Opening hours:</h3>Monday - Friday: 11 - 14 Uhr
          </p>
          <p >
            <h3>More:</h3> Website<a href="https://www.studentenwerk-muenchen.de/mensa/standorte-und-oeffnungszeiten/garching/">
          <IconButton aria-label="website" style={{margin: "0", padding: "0 0 0 10px "}}>
          <WebsiteIcon />
        </IconButton>
        </a>
          </p>
        </div>
        <div className="picture-map-wrapper">
          <Grid
            container
            alignItems="center"
            rowSpacing={{ xs: 3, md: 4 }}
            columnSpacing={{ xs: 3, md: 4 }}
          >
            <Grid item xs={12} sm={12} md={6} lg={7}>
              <img src={TUMMensa} alt="restaurant picture" className="map" />
            </Grid>
            <Grid item xs={12} sm={12} md={6} lg={5}>
              <img src={Map} alt="Map" className="map"></img>
            </Grid>
          </Grid>
        </div>
        <div>
          <ReservateComponent></ReservateComponent>
        </div>
        <CommentSection />
      </Container>
      <Footer />
    </div>
  );
}
