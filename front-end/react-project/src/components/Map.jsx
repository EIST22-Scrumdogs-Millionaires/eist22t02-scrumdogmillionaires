import apikey from "../data/apikey";
import React, {Component, useEffect} from "react";
import { GoogleApiWrapper, InfoWindow, Marker, Map, Circle } from 'google-maps-react';

const mapStyles = {
    width: '100%',
    height: '100%'
};

export function MapContainer(props) {
    const [state, setState] = React.useState({
        showingInfoWindow: false,
        activeMarker: {},
        selectedPlace: {},
        currentLocation: props.location,
        filters: props.filters,
        restaurants: props.restaurants,
    });

    useEffect(() => {
        setState({
            ...state,
            filters: props.filters,
            restaurants: props.restaurants,
        })
    }, [props])

    useEffect(() => {
        props.locationCallback(state.currentLocation);
    }, [state.currentLocation])

    const onMapClicked = (props2, e, coord) => {
        props.locationCallback({
                lat: coord.latLng.lat(),
                lng: coord.latLng.lng()
        })
        setState({
            ...state,
            currentLocation: {
                lat: coord.latLng.lat(),
                lng: coord.latLng.lng()
            }
        })
    }

    const onMouseoverMarker = (props, marker, e) =>
        setState({
            ...state,
            selectedPlace: props,
            activeMarker: marker,
            showingInfoWindow: true
        });

    const onMarkerClick = (props, marker, e) => {
        console.log(props);
        setState({
            ...state,
            selectedPlace: props,
            activeMarker: marker,
            showingInfoWindow: true
        });
    }



    const onInfoWindowClose = () => {
        setState({
            ...state,
            showingInfoWindow: false,
            activeMarker: null
        });
    }


    const getPosition = (restaurant) => {
        return {
            lat: restaurant.location.ycoordinate,
            lng: restaurant.location.xcoordinate
        }
    }

    const getTitle = (restaurant) => {
        return (
            <div>
                <a href={`http://localhost:3000/search/detail/${restaurant.id}`} target="_blank">{restaurant.name}</a>
            </div>
        )
    }

        if (typeof (state.restaurants) === "undefined" || typeof (state.filters) === "undefined") {
            return <div>Loading...</div>
        } else {
            var dis = state.filters.distance === ""? 2000 : state.filters.distance;
            return (
                <Map
                    google={props.google}
                    zoom={14}
                    style={mapStyles}
                    onClick={onMapClicked}
                    initialCenter={
                        {
                            lat: state.currentLocation.lat,
                            lng: state.currentLocation.lng
                        }
                    }>

                    <Marker onClick={onMarkerClick} title={"CurLoc"} name={"CurrentLocation"} position={state.currentLocation} />

                    {state.restaurants.map(restaurant => (
                        <Marker onClick={onMarkerClick} title={restaurant.name} name={getTitle(restaurant)} position={getPosition(restaurant)} />
                    ))}


                    <InfoWindow marker={state.activeMarker} visible={state.showingInfoWindow} onClose={onInfoWindowClose}>
                        <div>
                            <p>{state.selectedPlace.name}</p>
                        </div>
                    </InfoWindow>

                    <Circle radius={dis} center={state.currentLocation} onClick={onMapClicked}
                            fillOpacity={0.2} strokeOpacity={1} strokeWeight={3}/>

                </Map>
            );
        }

    }


export default GoogleApiWrapper({
    apiKey: apikey
})(MapContainer);

