import React, { useState, useEffect } from "react";
import { AddAgent } from "./AddAgent";
import { EditAgent } from "./EditAgent";
import { TableFormat } from "./TableFormat";
import { Errors } from "./Errors";

function Agents() {
  const initialFormState = {
    firstName: "",
    middleName: "",
    lastName: "",
    dob: "",
    heightInInches: 0,
  }
  
  const [currentAgent, setCurrentAgent] = useState(initialFormState);
  const [agents, setAgents] = useState([]);
  const [editing, setEditing] = useState(false);
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
  });

  const handleAddSubmit = async (agent) => {
    const body = JSON.stringify(agent);

    const response = await fetch("http://localhost:8080/api/agent", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        // "Accept": "application/json",
        // "Access-Control-Allow-Origin" : "*",
      },
      body,
    });

    if (response.status !== 201) {
      throw new Error("Server Error: Something unexpected went wrong.");
    }

    setEditing(false);
  };


  const handleEdit = (agent) => {
    setEditing(true);
    setCurrentAgent({...agent});
  };

  const handleUpdateSubmit = async (agent) => {
    setEditing(false);

    const body = JSON.stringify(agent);

    const response = await fetch(
      `http://localhost:8080/api/agent/${agent.agentId}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body,
      }
    );

    if (response.status !== 204) {
      throw new Error("Server Error: Something unexpected went wrong.");
    }
  };



  const handleDelete = async (agentId) => {

      setEditing(false);

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



  return (
    <>
      <Errors errors={errors} />

      {!editing ? (
        <AddAgent
          handleAddSubmit={handleAddSubmit}
        />
      ) : (
        <EditAgent
          handleUpdateSubmit={handleUpdateSubmit}
          currentAgent={currentAgent}
          setEditing={setEditing}
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