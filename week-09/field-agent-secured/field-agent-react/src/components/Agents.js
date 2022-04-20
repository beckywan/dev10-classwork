import { useState, useEffect, useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../AuthContext";


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


  const auth = useContext(AuthContext);

  const getData = async () => {
    fetch("http://localhost:8080/api/agent")
      .then((response) => response.json())
      .then((data) => setAgents(data))
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    getData();
  }, []);


  const handleDelete = (agentId) => {

    setEditing(false);

    var deleteConfirm = window.confirm("Want to delete?");

    if (deleteConfirm) {

    const init = {
      method: 'DELETE',
      headers: {
        Authorization: `Bearer ${auth.user.token}`, // NEW
      }
    };

    fetch(`http://localhost:8080/api/todos/${agentId}`, init)
      .then((response) => {
        if (response.status === 204) {
          getData();
        } else if (response.status === 404) {
          Promise.reject(`Agent ID #${agentId} not found.`);
        } else {
          Promise.reject("Shoot! Something unexpected went wrong :(");
        }
      })
      .catch((error) => console.log(error));
    }
  };


  return (
    <div>
      <h2 className="my-4">Agents</h2>
      <Link to="/todos/add" className="btn btn-primary mb-4">
        <i className="bi bi-plus-circle-fill"></i> Add Agent
      </Link>
      <table className="table table-striped table-hover">
        <thead>
          <tr>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">&nbsp;</th>
          </tr>
        </thead>
        <tbody>
          {agents.map((agent) => (
            <tr key={agent.agentId}>
              <td>{agent.firstName}</td>
              <td>{agent.lastName}</td>
              <td>
                <div className="float-right">
                  <Link
                    to={`/agent/edit/${agent.agentId}`}
                    className="btn btn-primary btn-sm"
                  >
                    <i className="bi bi-pencil"></i> EditAgent
                  </Link>
                  <button
                    className="btn btn-danger btn-sm ml-2"
                    onClick={() => handleDelete(agent.agentId)}
                  >
                    <i className="bi bi-trash"></i>
                    Delete
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Agents;