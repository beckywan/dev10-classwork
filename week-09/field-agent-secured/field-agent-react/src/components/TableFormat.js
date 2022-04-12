import React from "react";

const TableFormat = (props) => {
  return (
    <table className="table table-striped table-dark table-hover">
    <thead>
      <tr>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Options</th>
      </tr>
    </thead>
    <tbody>
      {props.agents.map((agent) => (
          <tr key={agent.agentId}>
            <td>{agent.firstName}</td>
            <td>{agent.lastName}</td>
            <td>
              <div className="float-right">
                <button
                  className="btn btn-primary btn-sm"
                  onClick={() => props.handleEdit(agent.agentId)}
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
        ))}
      </tbody>
    </table>
  );
};

export default TableFormat;