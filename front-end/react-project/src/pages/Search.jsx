import { React, useEffect } from "react";
import { useParams } from "react-router-dom";
import Footer from "../components/Footer";
import NavigationBar from "../components/NavigationBar";
import SearchResultComp from "../components/SearchResultComp";
import { Grid, Container } from "@mui/material";
import restaurant_data from "../data/restaurant_name_list.json";
import useState from "react-hook-use-state";
import Axios from "axios";
import FilterBarSearch from "../components/FilterBarSearch";

export default function Search() {
    //hook from react router do
    const [data, setData] = useState([]);
    const [filters, setFilters] = useState({
        category: "",
        price: "",
        rating: "",
        time: "",
        persons: 2,
    });

    const handleFilterChange = (newFilters) => {
        setFilters(newFilters);
    };

    const createFilterSting = (filters) => {
        var filterTypes=[];
        for (let [key, value] of Object.entries(filters)) {
            switch (key) {
                case "category": {
                    if (value !== "" && value !== "ALL") {
                        filterTypes.push("T_"+value);
                    }
                    break;
                }
                case "price": {
                    if (value !== "" && value !== "0") {
                        filterTypes.push("P_"+value);
                    }
                    break;
                }
                case "rating": {
                    if (value !== "") {
                        filterTypes.push("A_"+value);
                    }
                    break;
                }
                case "time": {
                    if (value !== "") {
                        filterTypes.push("F_"+value + "_" + filters.persons);
                    }
                    break;
                }
            }
        }
        return filterTypes.length < 2 ? filterTypes[0] : filterTypes.join("@");
    }

    const { query } = useParams();
    useEffect(() => {
        if (query === "topten") {
            Axios.get(`http://localhost:8080/restaurants/getTopTen`)
                .then((res) => {
                    setData(res.data);
                })
                .catch((err) => console.log(err));
        } else {
            Axios.get(`http://localhost:8080/restaurants/search/${query}`)
                .then((res) => {
                    setData(res.data);
                })
                .catch((err) => console.log(err));
        }
    }, []);

    useEffect(() => {
        var filterTypes = createFilterSting(filters);

        Axios.get(`http://localhost:8080/restaurants/search/${query}/${filterTypes}/10`)
                .then((res) => {
                    setData(res.data);
                })
                .catch((err) => console.log(err));
    }, [filters]);

    const restaurants = data.map((restaurant) => {
        return (
            <Grid item xs={12} sm={6} md={4} lg={3}>
                <SearchResultComp restaurant={restaurant} />
            </Grid>
        );
    });

    return (
        <div>
            <NavigationBar />
            <div className="content">
                <Container maxWidth="xl">
                    <div className="title-wrapper">
                        {query === "topten" ? (
                            <div>
                                <h1 className="title">Top 10 restaurants</h1>
                            </div>
                        ) : (
                            <div>
                                <h1>
                                    Search results for <em>"{query}"</em>
                                </h1>
                            </div>
                        )}
                    </div>
                    <div>
                        <FilterBarSearch filter={filters} filterCallback={handleFilterChange} />
                    </div>

                    <div className="search-result-wrapper">
                        <Grid
                            container
                            alignItems="center"
                            rowSpacing={{ xs: 3, md: 4 }}
                            columnSpacing={{ xs: 3, md: 4 }}
                        >
                            {
                                // value: how many of the 12 available columns are occupied by the component
                            }
                            {restaurants}
                        </Grid>
                    </div>
                </Container>
            </div>
            <Footer />
        </div>
    );

}