import * as React from "react";
import { styled } from "@mui/material/styles";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import Collapse from "@mui/material/Collapse";
import Avatar from "@mui/material/Avatar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ShareIcon from "@mui/icons-material/Share";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import RestaurantMenuIcon from '@mui/icons-material/RestaurantMenu';
import { grey } from '@mui/material/colors';
import TUMMensa from "../images/TUM-Mensa.jpg";
import Rating from "./Rating";
import {default as WebsiteIcon} from '@mui/icons-material/Language';
import { Link } from 'react-router-dom'
import { Button } from "@mui/material";
const ExpandMore = styled((props) => {
  const { expand, ...other } = props;
  return <IconButton {...other} />;
})(({ theme, expand }) => ({
  transform: !expand ? "rotate(0deg)" : "rotate(180deg)",
  marginLeft: "auto",
  transition: theme.transitions.create("transform", {
    duration: theme.transitions.duration.shortest,
  }),
}));

export default function SearchResultComp(props) {
  const [expanded, setExpanded] = React.useState(false);

  const handleExpandClick = () => {
    setExpanded(!expanded);
  };
  

  return (
    
    <Card className="search-result-comp">
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: grey[500]}} >
            <RestaurantMenuIcon/>
          </Avatar>
        }
        action={
          <a href={props.restaurant.website}>
          <IconButton aria-label="website">
          <WebsiteIcon />
        </IconButton>
        </a>
        }
        
        title={props.restaurant.name}
        
        subheader={props.restaurant.address}
      />
      <CardMedia
        component="img"
        height="194"
        image={TUMMensa}
        alt="restaurant foo"
      />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
          {props.restaurant.description}
        </Typography>
        <Typography color="text.secondary">
            <Rating number={props.restaurant.stars}/>
        </Typography>
       
        <Button  color="secondary" variant="contained" component={Link} to={`/search/detail/${props.restaurant.name}`}>More</Button>
        
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <FavoriteIcon />
        </IconButton>
        <IconButton aria-label="share">
          <ShareIcon />
        </IconButton>
        
        <ExpandMore
          expand={expanded}
          onClick={handleExpandClick}
          aria-expanded={expanded}
          aria-label="show more"
        >
          <ExpandMoreIcon />
        </ExpandMore>
      </CardActions>
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        <CardContent>
          <Typography paragraph>Infos:</Typography>
          <Typography paragraph>More infos about the resaturant</Typography>
          <Typography paragraph>Comment section</Typography>
          <Typography>Comments:</Typography>
        </CardContent>
      </Collapse>
    </Card>
  
  );
}
