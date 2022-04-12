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

                {role && <Link to={`/edit/${sighting.sightingId}`}>Edit</Link>}

                {role === "ROLE_ADMIN" && (
                  <button onClick={handleDelete}>
                    Delete
                  </button>
                )}

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