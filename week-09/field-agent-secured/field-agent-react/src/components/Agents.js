import { useState } from "react";
import { Link, useHistory } from "react-router-dom";
import Error from "./Error";

function Agents({ agent, role }) {
  const [errors, setErrors] = useState([]);

  const history = useHistory();

  const handleDelete = (e) => {
    fetch(`http://localhost:8080/agent/${agent.agentId}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    }).then((response) => {
      if (response.status >= 200 && response.status < 400) {
        history.push("/confirmation");
      } else {
        setErrors([response.statusText, "Unable to delete 🔥 🙅🏾‍♂️"]);
      }
    });
  };

  return (
    <div>
      {errors.map((error, i) => (
        <Error key={i} msg={error} />
      ))}{" "}
      <figure>
        {agent.imageUrl && (
          <img
            className="card-img-top"
            src={agent.imageUrl}
            alt={agent.bugType}
          />
        )}
        <figcaption>
          <h4>{agent.bugType}</h4>
          <p>
            <strong>Order:</strong> {agent.order.name}
          </p>
          <p>{agent.description}</p>
          <p>
            <time dateTime={agent.date}>{agent.date}</time>
          </p>
          <p>
            <strong>Interest:</strong> {agent.interest}
          </p>
        </figcaption>
      </figure>
      <footer>
        <div>
          {role && <Link to={`/edit/${agent.agentId}`}>Edit</Link>}

          {role === "ROLE_ADMIN" && (
            <button onClick={handleDelete}>
              Delete
            </button>
          )}
        </div>
      </footer>
    </div>
  );
}

export default Agents;
