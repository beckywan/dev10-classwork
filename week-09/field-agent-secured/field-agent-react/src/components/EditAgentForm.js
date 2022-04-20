import React, { useState } from "react";

const EditAgentForm = (props) => {
  const [agent, setAgent] = useState(props.currentAgent);

  const handleInputChange = (event) => {
    const newAgent = { ...agent };
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

      <button className="btn btn-warning ml-2" onClick={() => props.setEditing(false)}>
        Cancel
      </button>
    </form>
  );
};

export default EditAgentForm;