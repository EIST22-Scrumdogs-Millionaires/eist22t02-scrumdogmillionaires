import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { ThemeProvider } from "@emotion/react";
import Home from "./pages/Home";
import colorTheme from "./style/colorTheme.jsx";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Search from "./pages/Search";
import DetailSearchComponent from "./pages/DetailSearchComponent";
const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <Router>
      <ThemeProvider theme={colorTheme}>
        <Routes>
          <Route path="/" element={<Home />}></Route>
        </Routes>
        <Routes>
          <Route path="/search/:query/:filter" element={<Search />}></Route>
        </Routes>
        <Routes>
          <Route path="/searchdetail/:id" element={<DetailSearchComponent />}></Route>
        </Routes>
      </ThemeProvider>
    </Router>
  </React.StrictMode>
);
