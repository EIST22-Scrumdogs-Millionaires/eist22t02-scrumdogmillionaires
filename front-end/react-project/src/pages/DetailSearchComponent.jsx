import React from "react";
import { useParams } from "react-router-dom";
import Footer from "../components/Footer";
import NavigationBar from "../components/NavigationBar";

export default function DetailSearchComponent() {
  const { id } = useParams();
  return (
    <div>
      <NavigationBar />
      <div className="search-wrapper">
        <h1 className="title">{id}</h1>
      </div>

      <Footer />
    </div>
  );
}
