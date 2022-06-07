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
          <Route path="/login" element={<Login />}></Route>
        </Routes>
        <Routes>
          <Route path="/search/:query" element={<Search />}></Route>
        </Routes>
        <Routes>
          <Route path="/search/detail/:id" element={<DetailSearchComponent />}></Route>
        </Routes>
      </ThemeProvider>
    </Router>
  </React.StrictMode>
);
