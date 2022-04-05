import React, { useState } from "react";

export const AddAgent = (props) => {
  const initialFormState = {
    firstName: "",
    middleName: "",
    lastName: "",
    dob: "",
    heightInInches: 0,
  }

  const [agent, setAgent] = useState(initialFormState);

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
    props.handleAddSubmit(agent);

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
        Add Agent
      </button>

      {agent.firstName || agent.lastName || agent.heightInInches || props.errors.length > 0 ? (
        <button
          className="btn btn-warning ml-2"
          type="button"
          onClick={props.handleUpdateCancel}
        >
          Cancel
        </button>
      ) : null}
    </form>
  );
};