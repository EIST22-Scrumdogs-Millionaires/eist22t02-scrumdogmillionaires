import { useParams } from "react-router-dom";
import Footer from "../components/Footer";
import NavigationBar from "../components/NavigationBar";
import { Grid, Container } from "@mui/material";
import CommentSection from "../components/CommentSection";
import ReservateComponent from "../components/ReservateComponent";
import IconButton from "@mui/material/IconButton";
import { default as WebsiteIcon } from "@mui/icons-material/Language";
import useState from "react-hook-use-state";
import { useEffect, React } from "react";
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
  /*
  const fetchData = async () => {
    const response = await fetch(`http://localhost:8080/restaurant/${id}`);
    if (!response.ok) {
      throw new Error('Data could not be fetched!');
    } else {
      return response.json();
    }
  }
  useEffect(() => {
    fetchData()
      .then((res) => {
        setData(res);
      })
      .catch((e) => {
        console.log(e.message);
      })
  }, []);
  */

  if (data == null || data == undefined) {
    return <div>Loading...</div>;
  } else {
    console.log(data);

    for (let [key, value] of Object.entries(data)) {
      console.log(`${key}: ${value}`);
    }

    for (let [key, value] of Object.entries(data.reviews[0])) {
      console.log(`${key}: ${value}`);
    }
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
                  <em>Price Category: </em>
                  {data.priceCategory}, <em>Type: </em>
                  {data.restaurantType}
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
            <div className="picture-map-wrapper">
              <img
                src={`${data.pictures}`}
                alt="restaurant picture"
                className="picture"
              />
            </div>
            <div>
              <ReservateComponent></ReservateComponent>
            </div>
            <CommentSection restaurant={data} />
          </Container>
        </div>
        <Footer />
      </div>
    );
  }

  /*console.log(data);
  console.log(data.location.city);
  console.log((data.location));*/
}
