import React from "react";
import Divider from "@mui/material/Divider";
import ProfilPictureComments from "./ProfilPictureComments";
import Stack from "@mui/material/Stack";
import { Typography, TextField, Button, Box } from "@mui/material";
import Rating from "@mui/material/Rating";
import Axios from "axios";
export default function CommentSection(props) {
  const [nameComment, handleNameChange] = React.useState("");
  const [ownComment, handleOwnCommentChange] = React.useState("");
  const [ownRating, handleOwnRating] = React.useState(3);
  function handlePostButton() {
    if (!nameComment){
      handleNameChange("Anonymous");
    }
    const review = {
      username: nameComment,
      comment: ownComment,
      rating: ownRating,
    };
    Axios.post(
      `http://localhost:8080/comment/${props.restaurant.id}`,
      review
    )
      .then((res) => {
        console.log(res);
      })
      .catch((error) => {
        console.error("There was an error!", error);
      });
    handleOwnRating(0);
    handleNameChange("");
    handleOwnCommentChange("");
    window.location.reload(false);
  }

  const comments = props.restaurant.reviews.map((review) => {
    if (!review.username) {
      review.username = "Anonymous";
    }
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
              <Rating name="read-only" value={review.rating} readOnly />
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
            <Rating
              name="read-only"
              value={props.restaurant.averageRating}
              readOnly
              precision={0.1}
            />
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
                value={nameComment}
                onChange={(e) => handleNameChange(e.target.value)}
              />

              <p />
              <TextField
                fullWidth
                required
                label="Comment"
                variant="standard"
                color="secondary"
                value={ownComment}
                onChange={(e) => handleOwnCommentChange(e.target.value)}
              />
            </Box>
          </div>
        </Stack>
        <div style={{ textAlign: "left", margin: "30px 0 5px 10px" }}>
          <Rating
            name="simple-controlled"
            value={ownRating}
            onChange={(_event, newValue) => {
              handleOwnRating(newValue);
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

        <Button
          color="secondary"
          variant="contained"
          onClick={handlePostButton}
        >
          Post
        </Button>
      </Stack>
    </div>
  );
}
