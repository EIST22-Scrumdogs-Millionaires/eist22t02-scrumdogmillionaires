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
        currentLocation: {
            lat: 48.142166098,
            lng: 11.56745
        },
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

    const onMapClicked = (props, e, coord) => {
        setState({
            currentLocation: {
                lat: coord.latLng.lat(),
                lng: coord.latLng.lng()
            }
        })
    }

    const onMouseoverMarker = (props, marker, e) =>
        setState({
            selectedPlace: props,
            activeMarker: marker,
            showingInfoWindow: true
        });

    const onMarkerClick = (props, marker, e) =>
        setState({
            selectedPlace: props,
            activeMarker: marker,
            showingInfoWindow: true
        });


    const onInfoWindowClose = () => {
        setState({
            showingInfoWindow: false,
            activeMarker: null
        });
    }

    /*
    const componentDidUpdate(prevProps, prevState) {

        if (prevProps.filters !== this.props.filters) {
            this.setState({
                filters: this.props.filters
            })
        }
    }
     */

    const getPosition = (restaurant) => {
        return {
            lat: restaurant.location.Xcoordinate,
            lng: restaurant.location.Ycoordinate
        }
    }
        var dis = state.filters.distance === ""? 2000 : state.filters.distance;

        if (typeof (state.restaurants) === "undefined") {
            return <div>Loading...</div>
        } else {
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
                        <Marker onClick={onMarkerClick} title={restaurant.name} name={restaurant.name} position={getPosition(restaurant)} />
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

