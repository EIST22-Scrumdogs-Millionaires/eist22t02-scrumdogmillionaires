import React from "react";
import StarIcon from "@mui/icons-material/Star";
import StarOutlineIcon from "@mui/icons-material/StarOutline";

export default function Rating(props) {
  return (
    <div>
      {Array(parseInt(props.number)).fill(<StarIcon/>)}
      {Array(5 - parseInt(props.number)).fill(<StarOutlineIcon />)}
    </div>
  );
}
