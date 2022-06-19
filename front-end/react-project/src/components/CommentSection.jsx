import React from "react";
import Divider from "@mui/material/Divider";
import ProfilPictureComments from "./ProfilPictureComments";
import Stack from "@mui/material/Stack";
import { Typography, TextField, Button, Link, Box } from "@mui/material";
import Rating from "@mui/material/Rating";
export default function CommentSection(props) {
  const [value, setValue] = React.useState(0);
  const comments = props.reviews.reviews.map((review) => {
    return (
      <div className="comment">
        <Stack spacing={1}>
          <Stack direction="row" spacing={1}>
            <ProfilPictureComments name={review.username} />
            <strong style={{ margin: "auto 0 auto 15px" }}>
              {review.username}:
            </strong>
            <div style={{ flex: 1 }} />
            <div style={{ margin: "auto 5px auto 0" }}>
              <Rating name="read-only" value={review.rating} readOnly/>
            </div>
          </Stack>
          <div style={{ textAlign: "left", margin: "15px 0 0 0" }}>
            <Typography>{review.comment}</Typography>
          </div>
        </Stack>
      </div>
    );
  });
  return (
    <div className="comment-section">
      <div style={{ margin: "15px 0px" }}>
        <Stack direction="row" spacing={3}>
          <div style={{ flex: 1 }} />
          <h3>Total:</h3>
          <div style={{ margin: "auto 15px" }}>
            <Rating name="read-only" value={props.reviews.averageRating} readOnly precision={0.1} />
          </div>
        </Stack>
      </div>

      <Stack
        spacing={2}
        divider={<Divider orientation="horizontal" flexItem />}
      >
        {comments}
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
          <div style={{ textAlign: "left", margin: "auto 10px", flex: 1 }}>
            <Box
              sx={{
                width: "100%",
              }}
            >
              <TextField
                color="secondary"
                required
                id="standard-basic"
                label="Name"
                variant="standard"
              />

              <p />
              <TextField
                fullWidth
                required
                label="Comment"
                variant="standard"
                color="secondary"
              />
            </Box>
          </div>
        </Stack>
        <div style={{ textAlign: "left", margin: "30px 0 5px 10px" }}>
          <Rating
            name="simple-controlled"
            value={value}
            onChange={(event, newValue) => {
              setValue(newValue);
            }}
          />
        </div>
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
