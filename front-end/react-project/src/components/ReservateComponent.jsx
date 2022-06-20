import React from "react";
import TextField from "@mui/material/TextField";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { TimePicker } from "@mui/x-date-pickers/TimePicker";
import { DesktopDatePicker } from "@mui/x-date-pickers/DesktopDatePicker";
import { Box, Typography, Button, Grid } from "@mui/material";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
//contains much code from https://mui.com/material-ui/react-stepper/

const steps = ["Your Data", "Select", "Confirm"];
const DetailRestaurantInfos = (props) => {
  const [value, setValue] = React.useState(new Date());
  const [table, setTable] = React.useState("");
  const tablesDummy = [{id: 0, seats: 5, available: true}, {id: 1, seats: 2, available: false}, {id: 0, seats: 5, available: true}, {id: 0, seats: 5, available: false}, {id: 0, seats: 5, available: false}, {id: 0, seats: 5, available: true}, {id: 0, seats: 5, available: true}, {id: 0, seats: 5, available: true}, {id: 0, seats: 5, available: true}, {id: 0, seats: 5, available: true}]
  


  const classNameAvailable = "tableStyleAvailable";
  const classNameNonavailable = "tableStyleNonavailable"
  //props.restaurant.tables.map ... 
  const tables = tablesDummy.map((table) => {
    // id, seats
    return (
      <Grid item xs={3} sm={3} md={3} lg={3}>
        <div className={table.available === true ? classNameAvailable : classNameNonavailable}> 
        {table.id}
        </div>
      </Grid>
    );
  });
  const tableIdsFree = tablesDummy.filter ((table) => table.available).map((table) => {
    return <MenuItem value={table.id}>{table.id}</MenuItem>;
  });

  const chooseTable = (
    <div>
      <FormControl sx={{ m: 1, minWidth: 80 }}>
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

  const handleChange = (newValue) => {
    setValue(newValue);
  };

  const [activeStep, setActiveStep] = React.useState(0);
  const [skipped, setSkipped] = React.useState(new Set());
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
          />
        </div>
        <div className="input-reservate">
          <TextField
            color="secondary"
            required
            id="standard-basic"
            label="E-Mail"
            variant="outlined"
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
              value={value}
              onChange={handleChange}
              renderInput={(params) => <TextField {...params} />}
            />
          </div>
          <div className="input-reservate">
            <TimePicker
              ampm={false}
              label="Time"
              value={value}
              onChange={handleChange}
              renderInput={(params) => <TextField {...params} />}
            />
          </div>
        </LocalizationProvider>
        
        Tables:
        
        <div className="tables-wrapper">
          <Grid
              container
              alignItems="center"
              rowSpacing={{ xs: 2}}
              columnSpacing={{ xs: 2}}
            >
        {tables}
       </Grid>
        </div>
        
        <div className="input-reservate">
        {chooseTable}
        </div>
      </div>
    );
  } else if (activeStep === 2) {
    content = (
      <div style={{ textAlign: "left", width: "300px", margin: "auto" }}>
        <h3>Confirm:</h3>
        <strong>Name: </strong>Max Mustermann
        <br />
        <strong>Email: </strong>max_mustermann@gmail.com
        <br />
        <strong>Date: </strong>xx/xx/20222
        <br />
        <strong>Time: </strong>12:00
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

              <Button onClick={handleNext} color="secondary">
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
