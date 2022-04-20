import { useState, useEffect, useContext } from 'react';
import { Link, useHistory, useParams } from 'react-router-dom';
import AuthContext from '../AuthContext';

import Errors from './Errors';

function EditAgent() {

    const initialFormState = {
        firstName: "",
        middleName: "",
        lastName: "",
        dob: "",
        heightInInches: 0,
    }

    const [editing, setEditing] = useState(false);

    const [agent, setAgent] = useState(initialFormState);

    const [errors, setErrors] = useState([]);

    const auth = useContext(AuthContext);

    const { agentId } = useParams();
    const history = useHistory();

    const OnChangeHandler = (event) => {
        const newAgent = { ...agent };
        let value = event.target.value;
        if (event.target.type === "number") {
            value = parseInt(value);
        }
        newAgent[event.target.name] = value;
        setAgent(newAgent);
    };

    useEffect(() => {

        const init = {
            headers: {
                Authorization: `Bearer ${auth.user.token}`, // NEW
            }
        };

        fetch(`http://localhost:8080/api/agent/${agentId}`, init)
            // Response object
            .then(response => {
                if (response.status === 404) {
                    return Promise.reject(`Received 404 Not Found for Agent ID: ${agentId}`);
                }
                return response.json();
            })
            .then(data => {
                setAgent(data.agent);
            })
            .catch(error => {
                console.log(error);
            });
    }, [agentId, auth.user.token]); // empty array... run once when the component is loading

    const editAgentFormSubmitHandler = (event) => {
        event.preventDefault();
        setEditing(false);

        const init = {
            method: 'PUT', // GET by default
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${auth.user.token}`, // NEW
            },
            body: JSON.stringify(agent)
        };

        fetch(`http://localhost:8080/api/todos/${agent.agentId}`, init)
            .then(response => {
                if (response.status === 204) {
                    return null;
                } else if (response.status === 400) {
                    return response.json();
                }
                return Promise.reject('Something unexpected went wrong :)');
            })
            .then(data => {
                if (!data) {
                    // redirect the user back to the /todos route
                    history.push('/agent');
                } else {
                    // we have errors to display
                    setErrors(data);
                }
            })
            .catch(error => console.log(error));


    };

    return (
        <>
            <h2 className="my-4">Edit Agent</h2>
            <Errors errors={errors} />
            <form onSubmit={editAgentFormSubmitHandler}>
                <div className="form-group">
                    <label htmlFor="firstName">First Name: </label>
                    <input className="form-control" type="text" id="firstName" name="firstName"
                        value={agent.firstName} onChange={OnChangeHandler} />

                    <label htmlFor="middleName">Middle Name: </label>
                    <input className="form-control" type="text" id="middleName" name="middleName"
                        value={agent.middleName} onChange={OnChangeHandler} />

                    <label htmlFor="lastName">Last Name: </label>
                    <input className="form-control" type="text" id="lastName" name="lastName"
                        value={agent.lastName} onChange={OnChangeHandler} />

                    <label htmlFor="dob">Date of Birth: </label>
                    <input className="form-control" type="text" id="dob" name="dob"
                        value={agent.dob} onChange={OnChangeHandler} />

                    <label htmlFor="heightInInches">Height: </label>
                    <input className="form-control" type="number" id="heightInInches" name="heightInInches"
                        value={agent.heightInInches} onChange={OnChangeHandler} />
                </div>
                <div className="mt-5">
                    <button className="btn btn-success" type="submit">
                        <i className="bi bi-save"></i> Update Agent</button>
                    <Link to="/todos" className="btn btn-warning ml-2">
                        <i className="bi bi-x"></i> Cancel
                    </Link>
                </div>
            </form>
        </>
    );
}

export default EditAgent;