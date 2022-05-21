import React from "react";
import Footer from "../components/Footer";
import NavigationBar from "../components/NavigationBar";
import SearchBox from "../components/SearchBox";
function StartingPage(){
    return <div><NavigationBar></NavigationBar><h1>Restaurant Reservation System</h1><p><SearchBox/></p><Footer/></div>;
}

export default StartingPage;