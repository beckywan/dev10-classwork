import { Link } from "react-router-dom";
import AuthContext from "../context/AuthContext";
import { useContext } from "react";


useEffect(() => {
  const token = localStorage.getItem("token");
  if (token) {
    setUserStatus({ user: jwtDecode(token) });
  }
}, [setUserStatus]);


function NavBar() {
  const [userStatus, setUserStatus] = useContext(AuthContext);
  return (
    <nav>
      <ul>
        <li>
          <Link to="/add">Add</Link>
        </li>
        <li>
          <Link to="/login">Login</Link>
          <Link to="/login">
            {localStorage.getItem("token") ? "Logout" : "Login"}
          </Link>
        </li>
        {userStatus?.user && (
          <li>
            <Link to="/add">Add</Link>
          </li>
        )}
        {userStatus?.user ? (
          <li>
            <button
              onClick={() => {
                setUserStatus(null);
                localStorage.removeItem("token");
              }}
            >
              {/* `sub` is the property from the decoded token */}
              Logout {userStatus.user.sub}
            </button>
          </li>
        ) : (
          <li>
            <Link to="/login">Login</Link>
          </li>
        )}
      </ul>
    </nav>
  );
}
export default NavBar;