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
import Alert from '@mui/material/Alert';
import IconButton from '@mui/material/IconButton';
import Collapse from '@mui/material/Collapse';
import CloseIcon from '@mui/icons-material/Close';
//contains much code from https://mui.com/material-ui/react-stepper/
const steps = ["Your Data", "Select", "Confirm"];
const optionsDate = { year: "numeric", month: "2-digit", day: "2-digit" };
const optionsTime = { hour: "2-digit", minute: "2-digit" };

const formatDate = (inputDate) => {
  return inputDate
  .toLocaleDateString("de-De", optionsDate)
  .replaceAll(".", "-");
}

const formatTime = (inputTime) => {
  return inputTime.toLocaleTimeString("de-De", optionsTime);
}

const ReservateComponent = (props) => {
  const [name, handleNameChange] = useState("");
  const [email, handleEmailChange] = useState("");
  const [date, handleDateChange] = useState(new Date());
  const [time, handleChangeTime] = useState(new Date());
  const [userTable, setUserTable] = useState("");
  const [numberPersons, handleNumberPersonsChange] = useState(1);
  const [availableTables, setAvailableTables] = useState([]);
  const [openSuccessMessage, setOpenSuccessMessage] = React.useState(false);
  const [openErrorMessage, setOpenErrorMessage] = React.useState(false);

  const successMessage = (
    <Collapse in={openSuccessMessage}>
        <Alert
         severity="success"
          action={
            <IconButton
              aria-label="close"
              color="inherit"
              size="small"
              onClick={() => {
                setOpenSuccessMessage(false);
              }}
            >
              <CloseIcon fontSize="inherit" />
            </IconButton>
          }
          sx={{ m: 4 }}
        >
          Success!
        </Alert>
      </Collapse>);

const errorMessage = (
  <Collapse in={openErrorMessage}>
      <Alert
        severity="error"
        action={
          <IconButton
            aria-label="close"
            color="inherit"
            size="small"
            onClick={() => {
              setOpenErrorMessage(false);
            }}
          >
            <CloseIcon fontSize="inherit" />
          </IconButton>
        }
        sx={{ m: 2 }}
      >
        An error occured :/
      </Alert>
    </Collapse>);

  useEffect(() => {
    const inputDate = formatDate(date);
    const inputTime = formatTime(time);

    Axios.get(
      `http://localhost:8080/reservations/getAvailableTables/${props.restaurant.id}/${inputDate}/${inputTime}/${numberPersons}`
    )
      .then((res) => {
        setAvailableTables(res.data);
      })
      .catch((err) => console.log(err));
  }, [date, time, numberPersons]);

  const makeReservation = () => {
    const user = {
      username: name,
      email: email
    }
    const reservation = {
      time: time.toLocaleTimeString(),
      date: date,
      table:  {
        id:userTable,
        available:true,
        seats:numberPersons
      },
      user: user,
      id: 0, //serverseitig gesetzt
      restaurant_id: props.restaurant.id,
    };
    Axios.post(
      `http://localhost:8080/reservations`,
      reservation, user
    )
      .then((res) => {
        setOpenSuccessMessage(true);
        console.log(res);
      })
      .catch((error) => {
        setOpenErrorMessage(true);
        console.error("There was an error!", error);
      });
  }

  const handleTableChange = (event) => {
    if (event === "") {
      setUserTable("");
    } else {
       setUserTable(event.target.value);
    }
  }

  const classNameAvailable = "tableStyleAvailable";
  const classNameNonavailable = "tableStyleNonavailable";
  //props.restaurant.tables.map ...
  const tables = availableTables.map((currentTable) => {
    // id, seats
    return (
      <Grid item xs={4} sm={4} md={4} lg={4}>
        <div
          className={
            currentTable.available === true
              ? classNameAvailable
              : classNameNonavailable
          }
        >
         Nr.{currentTable.id}: <strong>{currentTable.seats}</strong>
        </div>
      </Grid>
    );
  });
  const tableIdsFree = availableTables
    .filter((currentTable) => currentTable.available)
    .map((currentTable) => {
      console.log(currentTable.id);
      console.log(typeof(currentTable.id));
      return <MenuItem value={currentTable.id}>{currentTable.id}</MenuItem>;
    });

  const chooseTable = (
    <div>
      <FormControl sx={{ m: 1, width: 266 }}>
        <InputLabel>Table</InputLabel>
        <Select
          value={userTable}
          onChange={handleTableChange}
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
    handleChangeTime(newTime);
  };

  const handleDatePickerChange = (newDate) => {
    handleDateChange(newDate);
  };

  
  const [activeStep, setActiveStep] = useState(0);
  const [skipped, setSkipped] = useState(new Set());
  
  const isStepSkipped = (step) => {
    return skipped.has(step);
  };

  const handleNext = () => {
    if (activeStep === steps.length - 1) {
      makeReservation();
    }
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

  const handleReset = () => {
    handleEmailChange("");
    handleNameChange("");
    handleTableChange("");
    handleNumberPersonsChange(0);
    setActiveStep(0);
  };

  var content;
  if (activeStep === steps.length) {
    content = (
      <Typography sx={{ mt: 2, mb: 1 }}>
        <p>All steps completed!</p>
        <p>You will recieve a confirmation E-Mail.</p>
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
              inputFormat="dd/MM/yyyy"
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
            onChange={(e) => {
              handleNumberPersonsChange(e.target.value);
            }}
            sx={{ width: 266 }}
          />
        </div>
        <div>Tables:</div>

        <div className="tables-wrapper">
          <Grid
            container
            alignItems="center"
            rowSpacing={{ xs: 1 }}
            columnSpacing={{ xs: 1 }}
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
        <br />
        <strong>Number Persons: </strong>
        {numberPersons}
        <br />
        <strong>Table Number: </strong>
        {userTable}
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
            

              <Button
                onClick={handleNext}
                color="secondary"
                disabled={
                  (activeStep === 0 && (name === "" || email === "")) ||
                  (activeStep === 1 && (userTable === "" || numberPersons === 0))
                }
              >
                {activeStep === steps.length - 1 ? "Finish" : "Next"}
              </Button>
            </Box>
            
          </React.Fragment>
        )}
      </Box>
      {successMessage}
        {errorMessage}
    </div>
  );
};

export default ReservateComponent;
