import React from "react";
import DarkModeIcon from "@mui/icons-material/DarkMode";
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
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
         {
             //TODO add app name?
         }
        </Typography>
        <Stack direction="row" spacing={2}>
          <IconButton size="large" color="inherit">
            <DarkModeIcon />
          </IconButton>
          <Button color="inherit">Login</Button>
        </Stack>
      </Toolbar>
    </AppBar>
  );
}
