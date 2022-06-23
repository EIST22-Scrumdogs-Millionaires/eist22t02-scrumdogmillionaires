import React, { useEffect } from "react";
import TextField from "@mui/material/TextField";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { TimePicker } from "@mui/x-date-pickers/TimePicker";
import { DesktopDatePicker } from "@mui/x-date-pickers/DesktopDatePicker";
import { Box, Typography, Button, Grid } from "@mui/material";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import useState from "react-hook-use-state";
import Axios from "axios";
//contains much code from https://mui.com/material-ui/react-stepper/
const steps = ["Your Data", "Select", "Confirm"];
const optionsDate = { year: "numeric", month: "2-digit", day: "2-digit" };
const optionsTime = { hour: "2-digit", minute: "2-digit" };

const DetailRestaurantInfos = (props) => {
  const [name, handleNameChange] = useState("");
  const [email, handleEmailChange] = useState("");
  const [date, setDate] = useState(new Date());
  const [time, setTime] = useState(new Date());
  const [table, setTable] = useState(null);
  const [numberPersons, setNumberPersons] = useState(0);
  const [availableTables, setAvailableTables] = useState([]);
  useEffect(() => {
    const dummyDate = new Date();
    var inputDate = dummyDate
      .toLocaleDateString("de-De", optionsDate)
      .replaceAll(".", "-");
    var inputTime = dummyDate.toLocaleTimeString("de-De", optionsTime);
    
    //TODO 5 ändern
    Axios.get(
      `http://localhost:8080/reservations/getAvailableTables/${props.restaurant.id}/${inputDate}/${inputTime}/${numberPersons}`
    )
      .then((res) => {
        setAvailableTables(res.data);
      })
      .catch((err) => console.log(err));
  }, []);
  console.log("Available Tables: " + availableTables);

  const tablesDummy = [
    { id: 0, seats: 5, available: true },
    { id: 1, seats: 2, available: false },
    { id: 0, seats: 5, available: true },
    { id: 0, seats: 5, available: false },
    { id: 0, seats: 5, available: false },
    { id: 0, seats: 5, available: true },
    { id: 0, seats: 5, available: true },
    { id: 0, seats: 5, available: true },
    { id: 0, seats: 5, available: true },
    { id: 0, seats: 5, available: true },
  ];
  console.log(props.restaurant.tables);

  for (let [key, value1] of Object.entries(props.restaurant.tables)) {
    console.log(`${key}: ${value1}`);
  }

  const classNameAvailable = "tableStyleAvailable";
  const classNameNonavailable = "tableStyleNonavailable";
  //props.restaurant.tables.map ...
  const tables = tablesDummy.map((table) => {
    // id, seats
    return (
      <Grid item xs={3} sm={3} md={3} lg={3}>
        <div
          className={
            table.available === true
              ? classNameAvailable
              : classNameNonavailable
          }
        >
          {table.id}
        </div>
      </Grid>
    );
  });
  const tableIdsFree = tablesDummy
    .filter((table) => table.available)
    .map((table) => {
      return <MenuItem value={table.id}>{table.id}</MenuItem>;
    });

  const chooseTable = (
    <div>
      <FormControl sx={{ m: 1, width: 266 }}>
        <InputLabel>Table</InputLabel>
        <Select
          value={table}
          onChange={setTable}
          autoWidth
          label="Table"
          color="secondary"
          defaultValue=""
        >
          <MenuItem value="">
            <em>None</em>
          </MenuItem>
          {tableIdsFree}
        </Select>
      </FormControl>
    </div>
  );

  const handleTimePickerChange = (newTime) => {
    setTime(newTime);
  };

  const handleDatePickerChange = (newDate) => {
    setDate(newDate);
  };

  const [activeStep, setActiveStep] = useState(0);
  const [skipped, setSkipped] = useState(new Set());
  const isStepOptional = (step) => {
    //return step === 1;
    return false;
  };

  const isStepSkipped = (step) => {
    return skipped.has(step);
  };

  const handleNext = () => {
    let newSkipped = skipped;
    if (isStepSkipped(activeStep)) {
      newSkipped = new Set(newSkipped.values());
      newSkipped.delete(activeStep);
    }

    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setSkipped(newSkipped);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleSkip = () => {
    if (!isStepOptional(activeStep)) {
      // You probably want to guard against something like this,
      // it should never occur unless someone's actively trying to break something.
      throw new Error("You can't skip a step that isn't optional.");
    }

    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setSkipped((prevSkipped) => {
      const newSkipped = new Set(prevSkipped.values());
      newSkipped.add(activeStep);
      return newSkipped;
    });
  };

  const handleReset = () => {
    setActiveStep(0);
  };

  var content;
  if (activeStep === steps.length) {
    content = (
      <Typography sx={{ mt: 2, mb: 1 }}>
        <p>All steps completed!</p>
        <p>You will recieve a comfimation E-Mail.</p>
      </Typography>
    );
  } else if (activeStep === 0) {
    content = (
      <div>
        <div className="input-reservate">
          <TextField
            color="secondary"
            required
            id="standard-basic"
            label="Name"
            variant="outlined"
            onChange={(e) => handleNameChange(e.target.value)}
            sx={{ width: 266 }}
          />
        </div>
        <div className="input-reservate">
          <TextField
            color="secondary"
            required
            id="standard-basic"
            label="E-Mail"
            variant="outlined"
            onChange={(e) => handleEmailChange(e.target.value)}
            sx={{ width: 266 }}
          />
        </div>
      </div>
    );
  } else if (activeStep === 1) {
    content = (
      <div>
        <LocalizationProvider dateAdapter={AdapterDateFns}>
          <div className="input-reservate">
            <DesktopDatePicker
              color="secondary"
              label="Date"
              inputFormat="MM/dd/yyyy"
              value={date}
              onChange={handleDatePickerChange}
              renderInput={(params) => (
                <TextField {...params} sx={{ width: 266 }} />
              )}
            >
              {" "}
            </DesktopDatePicker>
          </div>
          <div className="input-reservate">
            <TimePicker
              sx={{ m: 1, width: 806 }}
              ampm={false}
              label="Time"
              value={time}
              onChange={handleTimePickerChange}
              renderInput={(params) => (
                <TextField {...params} sx={{ width: 266 }} />
              )}
            />
          </div>
        </LocalizationProvider>
        <div className="input-reservate">
          <TextField
            label="Personen"
            type="number"
            value={numberPersons}
            InputProps={{
              inputProps: { min: 0, max: 10 },
            }}
            onChange={(e) => setNumberPersons(e.target.value)}
            sx={{ width: 266 }}
          />
        </div>
        <div>Tables:</div>

        <div className="tables-wrapper">
          <Grid
            container
            alignItems="center"
            rowSpacing={{ xs: 2 }}
            columnSpacing={{ xs: 2 }}
          >
            {tables}
          </Grid>
        </div>
        <div className="input-reservate">{chooseTable}</div>
      </div>
    );
  } else if (activeStep === 2) {
    content = (
      <div style={{ textAlign: "left", width: "300px", margin: "auto" }}>
        <h3>Confirm:</h3>
        <strong>Name: </strong>
        {name}
        <br />
        <strong>Email: </strong>
        {email}
        <br />
        <strong>Date: </strong>
        {date.toLocaleDateString("de-De", optionsDate).replaceAll(".", "-")}
        <br />
        <strong>Time: </strong>
        {time.toLocaleTimeString("de-De", optionsTime)}
      </div>
    );
  }
  return (
    <div className="reservation-comp">
      <Box sx={{ width: "100%" }}>
        <div style={{ margin: "10px", textAlign: "left" }}>
          <h3>Reservate:</h3>
        </div>
        <Stepper activeStep={activeStep}>
          {steps.map((label, index) => {
            const stepProps = {};
            const labelProps = {};
            if (isStepOptional(index)) {
              labelProps.optional = (
                <Typography variant="caption">Optional</Typography>
              );
            }
            if (isStepSkipped(index)) {
              stepProps.completed = false;
            }
            return (
              <Step key={label} {...stepProps}>
                <StepLabel {...labelProps}>{label}</StepLabel>
              </Step>
            );
          })}
        </Stepper>

        {activeStep === steps.length ? (
          <React.Fragment>
            {content}
            <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
              <Box sx={{ flex: "1 1 auto" }} />
              <Button onClick={handleReset} color="secondary">
                Reset
              </Button>
            </Box>
          </React.Fragment>
        ) : (
          <React.Fragment>
            <Typography sx={{ mt: 2, mb: 1 }}>{content}</Typography>
            <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
              <Button
                color="secondary"
                disabled={activeStep === 0}
                onClick={handleBack}
                sx={{ mr: 1 }}
              >
                Back
              </Button>
              <Box sx={{ flex: "1 1 auto" }} />
              {isStepOptional(activeStep) && (
                <Button color="secondary" onClick={handleSkip} sx={{ mr: 1 }}>
                  Skip
                </Button>
              )}

              <Button
                onClick={handleNext}
                color="secondary"
                disabled={
                  (activeStep === 0 && (name === "" || email === "")) ||
                  (activeStep === 1 && (table === null || numberPersons === 0))
                }
              >
                {activeStep === steps.length - 1 ? "Finish" : "Next"}
              </Button>
            </Box>
          </React.Fragment>
        )}
      </Box>
    </div>
  );
};

export default DetailRestaurantInfos;
