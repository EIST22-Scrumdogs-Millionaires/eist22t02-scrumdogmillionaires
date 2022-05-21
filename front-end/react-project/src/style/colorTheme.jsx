import { createTheme } from '@mui/material/styles';

 const theme = createTheme({
  palette: {
    primary: {
      light: '#f8ffd7',
      main: '#c5e1a5',
      dark: '#94af76',
      contrastText: '#000000',
    },
    secondary: {
      light: '#a98274',
      main: '#795548',
      dark: '#4b2c20',
      contrastText: '#ffffff',
    },
  },
});

export default theme;
