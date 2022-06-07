import apikey from "../data/apikey";
import React, { Component } from "react";
import { Map, GoogleApiWrapper, InfoWindow, Marker } from 'google-maps-react';

const mapStyles = {
    width: '50%',
    height: '50%'
};

function addMarker(props) {

}

export class MapContainer extends Component {
    state = {
        showingInfoWindow: false,
        activeMarker: {},
        selectedPlace: {},
    };

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
        })

    render() {
        return (
            <Map
                google={this.props.google}
                zoom={14}
                style={mapStyles}
                initialCenter={
                    {
                        lat: 48.142166098,
                        lng: 11.56745
                    }
                }>

                <Marker onMouseover={this.onMouseoverMarker} title={"TUM"} name={"Technische Universität München"} position={{ lat: 48.142166098, lng: 11.56745 }} />

                <InfoWindow marker={this.state.activeMarker} visible={this.state.showingInfoWindow} onClose={this.onInfoWindowClose}>
                    <div>
                        <p>
                            {this.state.selectedPlace.name}
                        </p>
                    </div>
                </InfoWindow>
            </Map>



        );
    }
}

export default GoogleApiWrapper({
    apiKey: apikey
})(MapContainer);

