import React, { useState, useContext } from "react";
import { Link } from "react-router-dom";
import { useHistory } from "react-router-dom";
import AuthContext from "../AuthContext";
import Errors from "./Errors";

const AddAgent = () => {
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

    const history = useHistory();

    const inputChangeHandler = (event) => {
        const newAgent = { ...agent };
        let value = event.target.value;
        if (event.target.type === "number") {
            value = parseInt(value);
        }
        newAgent[event.target.name] = value;
        setAgent(newAgent);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        const init = {
            method: "POST", // GET by default
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${auth.user.token}`, // NEW
            },
            body: JSON.stringify(agent),
        };

        fetch("http://localhost:8080/api/agent", init)
            .then(response => {
                if (response.status === 201 || response.status === 400) {
                    return response.json();
                }
                return Promise.reject("Server Error: Something unexpected went wrong.");
            })
            .then(data => {
                // if (data.id) {
                if (!data) {
                    history.push('/agent');
                } else {
                    setErrors(data);
                }
            })
            .catch(error => console.log(error));

        setEditing(false);
    }

    return (
        <>
            <h2 className="my-4">Add Agent</h2>
            <Errors errors={errors} />
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="firstName">First Name: </label>
                    <input
                        className="form-control"
                        type="text"
                        id="firstName"
                        name="firstName"
                        value={agent.firstName}
                        onChange={inputChangeHandler}
                    />

                    <label htmlFor="middleName">Middle Name: </label>
                    <input
                        className="form-control"
                        type="text"
                        id="middleName"
                        name="middleName"
                        value={agent.middleName}
                        onChange={inputChangeHandler}
                    />

                    <label htmlFor="lastName">Last Name: </label>
                    <input
                        className="form-control"
                        type="text"
                        id="lastName"
                        name="lastName"
                        value={agent.lastName}
                        onChange={inputChangeHandler}
                    />

                    <label htmlFor="dob">Date of Birth: yyyy-mm-dd </label>
                    <input
                        className="form-control"
                        type="text"
                        id="dob"
                        name="dob"
                        value={agent.dob}
                        onChange={inputChangeHandler}
                    />

                    <label htmlFor="heightInInches">Height: </label>
                    <input
                        className="form-control"
                        type="number"
                        id="heightInInches"
                        name="heightInInches"
                        value={agent.heightInInches}
                        onChange={inputChangeHandler}
                    />
                </div>
                <div className="mt-5">
                    <button className="btn btn-success" type="submit">
                        <i className="bi bi-plus-circle-fill"></i> Add Agent
                    </button>
                    <Link to="/agent" className="btn btn-warning ml-2">
                        <i className="bi bi-x"></i> Cancel
                    </Link>
                </div>
            </form>
        </>
    );
};

export default AddAgent;