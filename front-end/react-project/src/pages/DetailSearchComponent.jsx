import { useParams } from "react-router-dom";
import Footer from "../components/Footer";
import NavigationBar from "../components/NavigationBar";
import { Grid, Container } from "@mui/material";
import CommentSection from "../components/CommentSection";
import ReservateComponent from "../components/ReservateComponent";
import IconButton from "@mui/material/IconButton";
import {default as WebsiteIcon} from '@mui/icons-material/Language';
import useState from "react-hook-use-state";
import { useEffect, React } from "react";
import Axios from "axios";
import Map from "../components/Map";
export default function DetailSearchComponent() {
  const { id } = useParams();
  const [data, setData] = useState([]);
  useEffect(() => {
    Axios.get(`http://localhost:8080/restaurant/${id}`)
      .then((res) => {
        setData(res.data);
      })
      .catch((err) => console.log(err));
  }, []);
  console.log(data);
  return (
    <div>
      <NavigationBar />
      <div className="content">
      <Container maxWidth="xl" >
      <div className="title-wrapper">
        <div className="search-wrapper">
          <h1>{data.name}</h1>
          {data.description}
         <p>
         Price Category: {data.priceCategory}, Type: {data.restaurantType}
         </p>
          
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
            <h3>More:</h3> Website<a href={`https://${data.website}`}>
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
            <div className="picture-wrapper" style={{backgroundImage: `url(${data.pictures})`, margin: "auto"}}>
            {
              //<img src={`${(data.pictures)}`} alt="restaurant picture" className="picture" />
            }
            
            </div>
              
            </Grid>
            <Grid item xs={12} sm={12} md={6} lg={5}>
             {
              //TODO  <Map></Map>
             }
            </Grid>
          </Grid>
        </div>
        <div>
          <ReservateComponent></ReservateComponent>
        </div>
        <CommentSection/>
      </Container>
      </div>
      <Footer />
    </div>
  );
}
