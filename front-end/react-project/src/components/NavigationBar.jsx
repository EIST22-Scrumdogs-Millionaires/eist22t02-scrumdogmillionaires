import React from "react";
import { AppBar, Toolbar, Typography } from "@material-ui/core";
import SearchBox from "./SearchBox";
export default function NavigationBar(){
    return <AppBar position="static" color="primary">
    <Toolbar>
<Typography variant="h6" component="div">Restaurant Reservation App</Typography>

    </Toolbar>
  </AppBar>
}