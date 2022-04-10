import React, { useState } from "react";

export const EditAgent = (props) => {
  // const [firstName, setFirstName] = useState(props.firstName);
  // const [middleName, setMiddleName] = useState(props.middleName);
  // const [lastName, setLastName] = useState(props.lastName);
  // const [dob, setDob] = useState(props.dob);
  // const [heightInInches, setHeightInInches] = useState(props.heightInInches);

  // const handleInputChange = event => {
  //   if (event.target.type === "number") {
  //           event.target.value = parseInt(event.target.value);
  //         }
    
  //   setFirstName(event.target.value);
  //   setMiddleName(event.target.value);
  //   setLastName(event.target.value);
  //   setDob(event.target.value);
  //   setHeightInInches(event.target.value);
  // }

  // const handleSubmit = event => {
  //     event.preventDefault();
  //     props.handleUpdateSubmit(firstName);
  //     props.handleUpdateSubmit(middleName);
  //     props.handleUpdateSubmit(lastName);
  //     props.handleUpdateSubmit(dob);
  //     props.handleUpdateSubmit(heightInInches);
  // }




    // const initialFormState = {
    //   firstName: useState(props.firstName),
    //   middleName: useState(props.middleName),
    //   lastName: useState(props.lastName),
    //   dob: useState(props.dob),
    //   heightInInches: useState(props.heightInInches),
    // }




    const [agent, setAgent] = useState(props.currentAgent);

    const handleInputChange = (event) => {
        const newAgent = {...agent};
        let value = event.target.value;
        if (event.target.type === "number") {
          value = parseInt(value);
        }
        newAgent[event.target.name] = value;
        setAgent(newAgent);
    };
  
    const handleSubmit = (event) => {
      event.preventDefault();
      props.handleUpdateSubmit(agent);
  
    };

  return (
    <form onSubmit={handleSubmit} className="form-inline mx-2 my-4">
        <input
        type="text"
        className="form-control col-6"
        id="firstName"
        name="firstName"
        placeholder="Please enter the first name:"
        value={agent.firstName}
        onChange={handleInputChange}
      />

    <input
        type="text"
        className="form-control col-6"
        id="middleName"
        name="middleName"
        placeholder="Please enter the middle name:"
        value={agent.middleName}
        onChange={handleInputChange}
      />

    <input
        type="text"
        className="form-control col-6"
        id="lastName"
        name="lastName"
        placeholder="Please enter the last name:"
        value={agent.lastName}
        onChange={handleInputChange}
      />

    <input
        type="text"
        className="form-control col-6"
        id="dob"
        name="dob"
        placeholder="Please enter the date of birth (yyyy-mm-dd):"
        value={agent.dob}
        onChange={handleInputChange}
      />

 <input
        type="number"
        className="form-control col-6"
        id="heightInInches"
        name="heightInInches"
        placeholder="Please enter the height in inches:"
        value={agent.heightInInches}
        onChange={handleInputChange}
      />
      


      <button type="submit" className="btn btn-success ml-2">
        Update Agent
      </button>
      
      <button className="btn btn-warning ml-2" onClick={()=> props.setEditing(false)}>
        Cancel
      </button>
    </form>
  );
};