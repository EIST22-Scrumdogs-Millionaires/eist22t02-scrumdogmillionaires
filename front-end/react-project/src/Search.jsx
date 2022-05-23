import React from "react";
import Footer from "./components/Footer";
import NavigationBar from "./components/NavigationBar";
import SearchResultComp from "./components/SearchResultComp";

export default function Search(){
    return <div>
    <NavigationBar/>
       <h1>
       Hello, this will be the search page
       </h1> 
       <SearchResultComp></SearchResultComp>
       <SearchResultComp></SearchResultComp>
       <Footer/>
    </div>
    
}