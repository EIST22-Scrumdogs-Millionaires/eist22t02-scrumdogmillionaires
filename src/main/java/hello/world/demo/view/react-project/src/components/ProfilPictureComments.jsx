import React from 'react';
import Avatar from '@mui/material/Avatar';

// Code from https://mui.com/material-ui/react-avatar/
function stringToColor(string) {
  if (!string) {
    string = "Anonymous";
  }
  let hash = 0;
  let i;

  for (i = 0; i < string.length; i += 1) {
    hash = string.charCodeAt(i) + ((hash << 5) - hash);
  }

  let color = '#';

  for (i = 0; i < 3; i += 1) {
    const value = (hash >> (i * 8)) & 0xff;
    color += `00${value.toString(16)}`.slice(-2);
  }

  return color;
}

export default function ProfilPictureComments(props){
    const color = stringToColor(props.name);
    return <Avatar sx={{bgcolor: color, width: 30, height: 30}}>{(props.name.split(' ')[0][0]).toUpperCase()}{ (props.name.split(' ').length >= 2) ? props.name.split(' ')[1][0].toUpperCase() : null}</Avatar>;
}
