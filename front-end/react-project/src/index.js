import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { ThemeProvider } from '@emotion/react';
import StartingPage from "./main/StartingPage";
import colorTheme from "./style/colorTheme.jsx";
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
  <ThemeProvider theme={colorTheme}>
    < StartingPage/>
    </ThemeProvider>
  </React.StrictMode>
);


