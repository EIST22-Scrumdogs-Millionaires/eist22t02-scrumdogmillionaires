import { useParams } from "react-router-dom";
import Footer from "../components/Footer";
import NavigationBar from "../components/NavigationBar";
import { Container, Grid, ImageList, ImageListItem } from "@mui/material";
import CommentSection from "../components/CommentSection";
import ReservateComponent from "../components/ReservateComponent";
import IconButton from "@mui/material/IconButton";
import { default as WebsiteIcon } from "@mui/icons-material/Language";
import useState from "react-hook-use-state";
import { useEffect, React } from "react";
import apikey from "../data/apikey";
import Axios from "axios";
export default function DetailSearchComponent() {
  const [data, setData] = useState(null);
  const { id } = useParams();

  useEffect(() => {
    Axios.get(`http://localhost:8080/restaurant/${id}`)
      .then((res) => {
        setData(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  if (data == null || data == undefined) {
    return <div>Loading...</div>;
  } else {
    for (let [key, value] of Object.entries(data)) {
      console.log(`${key}: ${value}`);
    }

    for (let [key, value] of Object.entries(data.reviews[0])) {
      console.log(`${key}: ${value}`);
    }
    console.log(data.reviews);
    return (
      <div>
        <NavigationBar />
        <div className="content">
          <Container maxWidth="xl">
            <div className="title-wrapper">
              <div className="search-wrapper">
                <h1>{data.name}</h1>
                {data.description}
                <p>
                  {data.priceCategory === undefined ? null : (
                    <span>
                      <em>Price Category: </em>
                      {data.priceCategory}
                    </span>
                  )}
                  {data.priceCategory === undefined ||
                  data.restaurantType === undefined ? null : (
                    <span>,</span>
                  )}
                  {data.restaurantType === undefined ? null : (
                    <span>
                      <em>Type: </em>
                      {data.restaurantType}
                    </span>
                  )}
                </p>
              </div>
            </div>
            <div className="restaurant-infos">
              <p>
                <h3>Address:</h3>
                {`${data.location.street} ${data.location.streetnumber}, ${data.location.city} ${data.location.plz}`}
              </p>
              {data.openingAndClosingTimesAsFancyString === undefined ? null : (
                <p>
                  <h3>Opening hours:</h3>
                  {data.openingAndClosingTimesAsFancyString}
                </p>
              )}
              {data.website === undefined ? null : (
                <p>
                  <h3>More:</h3> Website
                  <a href={`https://${data.website}`}>
                    <IconButton
                      aria-label="website"
                      style={{ margin: "0", padding: "0 0 0 10px " }}
                    >
                      <WebsiteIcon />
                    </IconButton>
                  </a>
                </p>
              )}
            </div>
            <div className="picture-wrapper">
            
              <ImageList
                sx={{
                  width: {
                    xs: 300,
                    sm: 500,
                    md: 800,     
                  },
                  height: 450,
                }}
                variant="woven"
                cols={3}
                gap={8}
                style={{margin: "auto"}}
              >
                {data.pictures.map((picture) => (
                  <ImageListItem>
                    <img
                      src={`${picture + apikey}`}
                      alt="restaurant picture"
                      className="picture"
                      loading="lazy"
                    />
                  </ImageListItem>
                ))}
              </ImageList>
            </div>
            <div>
              {data.reviews ? (
                <ReservateComponent restaurant={data}></ReservateComponent>
              ) : null}
            </div>
            <CommentSection restaurant={data} />
          </Container>
        </div>
        <Footer />
      </div>
    );
  }
}
