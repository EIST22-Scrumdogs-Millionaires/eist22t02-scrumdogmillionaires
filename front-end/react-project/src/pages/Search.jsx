import { React, useEffect } from "react";
import { useParams } from "react-router-dom";
import Footer from "../components/Footer";
import NavigationBar from "../components/NavigationBar";
import SearchResultComp from "../components/SearchResultComp";
import { Grid, Container } from "@mui/material";
import restaurant_data from "../data/restaurant_name_list.json";
import useState from "react-hook-use-state";
import Axios from "axios";

export default function Search() {
  //hook from react router do
  const [data, setData] = useState([]);
  const { query } = useParams();
  useEffect(() => {
    Axios.get(`http://localhost:8080/restaurants/search/hello`)
      .then((res) => {
        console.log(res.data);
        setData(res.data);
      })
      .catch((err) => console.log(err));
  }, []);
  const restaurants = data.map((restaurant) => {
    return (
      <Grid item xs={12} sm={6} md={4} lg={3}>
        <SearchResultComp restaurant={restaurant}></SearchResultComp>
      </Grid>
    );
  });
  const restaurants_dummy_local = restaurant_data["restaurants"].map(
    (restaurant) => {
      return (
        <Grid item xs={12} sm={6} md={4} lg={3}>
          <SearchResultComp restaurant={restaurant}></SearchResultComp>
        </Grid>
      );
    }
  );

  return (
    <div>
      <NavigationBar />
      <div className="content">
        <Container maxWidth="xl">
          <div className="title-wrapper">
            <h1 className="title">
              Search results for <em>"{query}"</em>
            </h1>
          </div>

          <div className="search-result-wrapper">
            <Grid
              container
              alignItems="center"
              rowSpacing={{ xs: 3, md: 4 }}
              columnSpacing={{ xs: 3, md: 4 }}
            >
              {
                // value: how many of the 12 available columns are occupied by the component
              }
              {restaurants}
            </Grid>
          </div>
        </Container>
      </div>
      <Footer />
    </div>
  );
}
