import React from "react";
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import theme2 from "../style/colorTheme2";
import { Link, useNavigate } from "react-router-dom";
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { ThemeProvider } from "@mui/private-theming";
import {
  AppBar,
  Toolbar,
  IconButton,
} from "@material-ui/core";

export default function NavigationBar() {
  const navigate = useNavigate();

  return (
    <ThemeProvider theme={theme2}>
    <AppBar position="static" color="inherit" className="navigation-bar">
      <Toolbar>
      <IconButton onClick={() => navigate(-1)} size="small" color="inherit">
          <ArrowBackIcon />
        </IconButton>
        <IconButton component={Link} to="/" size="small" color="inherit">
          <HomeOutlinedIcon />
        </IconButton>

        <div style={{ flex: 1 }} />
      </Toolbar>
    </AppBar>
    </ThemeProvider>
  );
}
