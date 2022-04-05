import React, { useState, useEffect } from "react";
import { AddAgent } from "./AddAgent";
import { EditAgent } from "./EditAgent";
import { TableFormat } from "./TableFormat";
import { Errors } from "./Errors";

function Agents() {
  
  const [agents, setAgents] = useState([]);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [middleName, setMiddleName] = useState("");
  const [dob, setDob] = useState("");
  const [heightInInches, setHeightInInches] = useState(0);
  const [editAgentId, setEditAgentId] = useState(0);
  const [errors, setErrors] = useState([]);


  useEffect(() => {
    const getData = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/agent");
        const data = await response.json();
        setAgents(data);
      } catch (error) {
        console.log(error);
      }
    };
    getData();
  }, []);

  const handleAddSubmit = async (agent) => {
    

    const body = JSON.stringify(agent);


    try {
      const response = await fetch("http://localhost:8080/api/agent", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          // "Accept": "application/json",
          // "Access-Control-Allow-Origin" : "*",
        },
        body,
      });

      if (response.status === 201 || response.status === 400) {
        const data = await response.json();

        if (data.id) {
          setAgents([...agents, data]);

          setFirstName("");
          setLastName("");
          setMiddleName("");
          setDob("");
          setHeightInInches(0);

          setErrors([]);
        } else {
          setErrors(data);
        }
      } else {
        throw new Error("Server Error: Something unexpected went wrong.");
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleEdit = (agentId) => {
    const agentToEdit = agents.find((agent) => agent.agentId === agentId);

    setFirstName(agentToEdit.firstName);
    setMiddleName(agentToEdit.middleName);
    setLastName(agentToEdit.lastName);
    setDob(agentToEdit.dob);
    setHeightInInches(agentToEdit.heightInInches);

    setEditAgentId(agentToEdit.agentId);
  };

  const handleUpdateSubmit = async (agent) => {

    agent.agentId = editAgentId;

    const body = JSON.stringify(agent);


    try {
      const response = await fetch(
        `http://localhost:8080/api/agent/${editAgentId}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body,
        }
      );

      if (response.status === 204) {
        const newAgents = [...agents];

        const agentIndexToEdit = agents.findIndex(
          (agent) => agent.agentId === editAgentId
        );

        newAgents[agentIndexToEdit] = {
          agentId: editAgentId,
          firstName, 
          middleName,   
          lastName, 
          dob, 
          heightInInches,
        };

        setAgents(newAgents);
        setFirstName("");
        setLastName("");
        setMiddleName("");
        setDob("");
        setHeightInInches(0);
        setEditAgentId(0);
        setErrors([]);
      } else if (response.status === 400) {
        const data = await response.json();
        setErrors(data);
      } else {
        throw new Error("Server Error: Something unexpected went wrong.");
      }
    } catch (error) {
      console.log(error);
    }
  };


  

  const handleDelete = 

  async (agentId) => {

    var deleteConfirm = window.confirm("Want to delete?");

  if (deleteConfirm) {

    try {
      const response = await fetch(
        `http://localhost:8080/api/agent/${agentId}`,
        {
          method: "DELETE",
        }
      );

      if (response.status === 204) {
        const newAgents = agents.filter((agent) => agent.agentId !== agentId);
        setAgents(newAgents);
      } else {
        throw new Error("Server Error: Something unexpected went wrong.");
      }
    } catch (error) {
      console.log(error);
    }
  }
};


  const handleUpdateCancel = () => {
    setFirstName("");
    setLastName("");
    setMiddleName("");
    setDob("");
    setHeightInInches(0);
    setEditAgentId(0);
    setErrors([]);
  };

  return (
    <>
      <Errors errors={errors} />

      {editAgentId === 0 ? (
        <AddAgent
          handleAddSubmit={handleAddSubmit}
          errors={errors}
          handleUpdateCancel={handleUpdateCancel}
        />
      ) : (
        <EditAgent
          handleUpdateSubmit={handleUpdateSubmit}
          handleUpdateCancel={handleUpdateCancel}
        />
      )}
      <TableFormat
        agents={agents}
        handleEdit={handleEdit}
        handleDelete={handleDelete}
      />
    </>
  );
}

export default Agents;