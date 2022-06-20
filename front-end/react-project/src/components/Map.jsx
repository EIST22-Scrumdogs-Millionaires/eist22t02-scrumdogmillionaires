import apikey from "../data/apikey";
import React, { Component } from "react";
import { GoogleApiWrapper, InfoWindow, Marker, Map, Circle } from 'google-maps-react';

const mapStyles = {
    width: '100%',
    height: '100%'
};

export class MapContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showingInfoWindow: false,
            activeMarker: {},
            selectedPlace: {},
            currentLocation: {
                lat: 48.142166098,
                lng: 11.56745
            },
            filters: props.filters,
            restaurants: props.restaurants,
        }
    }

    onMapClicked = (props, e, coord) => {
        this.setState({
            currentLocation: {
                lat: coord.latLng.lat(),
                lng: coord.latLng.lng()
            }
        })
    }

    onMouseoverMarker = (props, marker, e) =>
        this.setState({
            selectedPlace: props,
            activeMarker: marker,
            showingInfoWindow: true
        });

    onMarkerClick = (props, marker, e) =>
        this.setState({
            selectedPlace: props,
            activeMarker: marker,
            showingInfoWindow: true
        });


    onInfoWindowClose = () => {
        this.setState({
            showingInfoWindow: false,
            activeMarker: null
        });
    }

    componentDidUpdate(prevProps, prevState) {
        if (prevProps.filters !== this.props.filters) {
            this.setState({
                filters: this.props.filters
            })
        }
    }

    getPosition (restaurant) {
        return {
            lat: restaurant.location.Xcoordinate,
            lng: restaurant.location.Ycoordinate
        }
    }

    render() {
        return (
            <Map
                google={this.props.google}
                zoom={14}
                style={mapStyles}
                onClick={this.onMapClicked}
                initialCenter={
                    {
                        lat: this.state.currentLocation.lat,
                        lng: this.state.currentLocation.lng
                    }
                }>

                <Marker onClick={this.onMarkerClick} title={"CurLoc"} name={"CurrentLocation"} position={this.state.currentLocation} />

                {this.state.restaurants.map(restaurant => (
                    <Marker onClick={this.onMarkerClick} title={restaurant.name} name={restaurant.name} position={this.getPosition(restaurant)} />
                ))}

                <InfoWindow marker={this.state.activeMarker} visible={this.state.showingInfoWindow} onClose={this.onInfoWindowClose}>
                    <div>
                        <p>{this.state.selectedPlace.name}</p>
                    </div>
                </InfoWindow>

                <Circle radius={this.state.filters.distance} center={this.state.currentLocation} onClick={this.onMapClicked}
                        fillOpacity={0.2} strokeOpacity={1} strokeWeight={3}/>

            </Map>
        );
    }
}


export default GoogleApiWrapper({
    apiKey: apikey
})(MapContainer);

