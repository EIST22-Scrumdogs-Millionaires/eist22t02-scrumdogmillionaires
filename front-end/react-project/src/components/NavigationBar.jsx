import React from "react";
import DarkModeIcon from "@mui/icons-material/DarkMode";
import HomeIcon from "@mui/icons-material/Home";
import { Link } from "react-router-dom";
import {
  AppBar,
  Toolbar,
  Typography,
  Stack,
  Button,
  IconButton,
} from "@material-ui/core";

export default function NavigationBar() {
  return (
    <AppBar position="static" color="primary">
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
  );
}
