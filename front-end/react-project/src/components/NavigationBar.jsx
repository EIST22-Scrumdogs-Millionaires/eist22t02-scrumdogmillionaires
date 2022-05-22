import React from "react";
import DarkModeIcon from "@mui/icons-material/DarkMode";
import HomeIcon from "@mui/icons-material/Home";
import theme2 from "../style/colorTheme2";
import { Link } from "react-router-dom";
import { ThemeProvider } from "@mui/private-theming";
import {
  AppBar,
  Toolbar,
  Stack,
  Button,
  IconButton,
} from "@material-ui/core";

export default function NavigationBar() {
  return (
    <ThemeProvider theme={theme2}>
    <AppBar position="static" color="inherit" className="navigation-bar">
      <Toolbar>
        <IconButton component={Link} to="/" size="small" color="inherit">
          <HomeIcon />
        </IconButton>

        <div style={{ flex: 1 }} />

        <div>
          <Stack direction="row" spacing={2}>
            <IconButton size="small" color="inherit">
              <DarkModeIcon />
            </IconButton>
            <Button component={Link} to="/login" color="inherit">
              Login
            </Button>
          </Stack>
        </div>
      </Toolbar>
    </AppBar>
    </ThemeProvider>
  );
}
