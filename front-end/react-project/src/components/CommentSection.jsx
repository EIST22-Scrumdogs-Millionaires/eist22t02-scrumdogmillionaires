import React from "react";
import Divider from "@mui/material/Divider";
import ProfilPictureComments from "./ProfilPictureComments";
import Stack from "@mui/material/Stack";
import { Typography, TextField, Button, Link, Box } from "@mui/material";
import Rating from "@mui/material/Rating";
export default function CommentSection(props) {
  const [value, setValue] = React.useState(0);
  return (
    <div className="comment-section">
      <Stack direction="row" spacing={3} style={{ margin: "15px 0px" }}>
        <h3 style={{ margin: "auto" }}>Comments:</h3>
        <div style={{ flex: 1 }} />
        <h3>Total:</h3>
        <div style={{ margin: "auto 15px" }}>
          <Rating name="read-only" value={3} readOnly />
        </div>
      </Stack>
      <Stack
        spacing={2}
        divider={<Divider orientation="horizontal" flexItem />}
      >
        <div className="comment">
          <Stack direction="row" spacing={3}>
            <ProfilPictureComments name="Max Mustermann" />

            <div style={{ textAlign: "left", margin: "auto 0px auto 15px" }}>
              <Typography>
                <strong>Max Mustermann:</strong><br/>It was good It was good It was good It was good
              </Typography>
            </div>
            <div style={{ flex: 1 }} />
            <div style={{ margin: "auto 5px" }}>
              <Rating name="read-only" value={3} readOnly />
            </div>
          </Stack>
        </div>

        <div className="comment">
          <Stack direction="row" spacing={3}>
            <ProfilPictureComments name="Test lol" />

            <div
              className="test"
              style={{ textAlign: "left", margin: "auto 10px" }}
            >
              <Typography>It was bad </Typography>
            </div>
            <div style={{ flex: 1 }} />
            <div style={{ margin: "auto" }}>
              <Rating name="read-only" value={1} readOnly />
            </div>
          </Stack>
        </div>
        <div className="comment">
          <Stack direction="row" spacing={3}>
            <ProfilPictureComments name="Cremers" />

            <div
              className="test"
              style={{ textAlign: "left", margin: "auto 10px" }}
            >
              <Typography>It was good</Typography>
            </div>
            <div style={{ flex: 1 }} />
            <div style={{ margin: "auto" }}>
              <Rating name="read-only" value={5} readOnly />
            </div>
          </Stack>
        </div>
      </Stack>
      <Stack
        direction="row"
        spacing={3}
        style={{ margin: "40px 0px 10px 0px" }}
      >
        <h3 style={{ margin: "auto" }}>Your comment:</h3>
        <div style={{ flex: 1 }} />
       
      </Stack>

      <div className="comment">
        <Stack direction="row" spacing={3}>
          <div style={{ textAlign: "left", margin: "auto 10px", flex:1 }}>
            <Box
              sx={{
                width: "100%",
              }}
            >
              <TextField
                fullWidth
                label="Comment..."
                variant="standard"
                color="secondary"
              />
            </Box>
          </div>
          <div style={{ margin: "auto" }}>
            <Rating
              name="simple-controlled"
              value={value}
              onChange={(event, newValue) => {
                setValue(newValue);
              }}
            />
          </div>
          <div style={{ margin: "auto 0 auto 15px" }}>
            
          </div>
        </Stack>
        
      </div>
      <Stack
        direction="row"
        spacing={3}
        style={{ margin: "10px 0px 10px 0px" }}
      >
      <div style={{ flex: 1 }} />
      <Button color="secondary" variant="contained" component={Link}>
              Post
            </Button>
      </Stack>
      
      </div>
   
  );
}
