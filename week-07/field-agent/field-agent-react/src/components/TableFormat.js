import React from "react";

export const TableFormat = (props) => (
  <table className="table">
    <thead>
      <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Options</th>
      </tr>
    </thead>
    <tbody>
      {props.agents.length > 0 ? (
        props.agents.map((agent) => (
          <tr key={agent.agentId}>
            <td>{agent.firstName}</td>
            <td>{agent.lastName}</td>
            <td>
              <div>
                <button
                  className="btn btn-primary btn-sm"
                  onClick={() => props.handleEdit(agent)}
                >
                  Edit
                </button>
                <button
                  onClick={() => props.handleDelete(agent.agentId)}
                  className="btn btn-danger btn-sm ml-2"
                >
                  Delete
                </button>
              </div>
            </td>
          </tr>
        ))
      ) : (
        <tr>
          <td colSpan={3}>No agents</td>
        </tr>
      )}
    </tbody>
  </table>
);