import React, { useState } from "react";
import { Redirect } from "react-router-dom";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import AuthContext from "./AuthContext";
import AddAgent from "./components/AddAgent";
import EditAgent from "./components/EditAgent";
import Header from "./components/Header";
import Home from "./components/Home";
import Login from "./components/Login";
import NotFound from "./components/NotFound";
import Agents from "./components/Agents";
import jwt_decode from 'jwt-decode';
import Register from "./components/Register";

const TOKEN_KEY = "user-api-token";

function App() {
  const [user, setUser] = useState(null);

  const login = (token) => {
    console.log(token);

    localStorage.setItem(TOKEN_KEY, token);

    const tokenObj = jwt_decode(token);
    console.log(tokenObj);

    const { sub: username, authorities: roleString } = tokenObj;

    const roles = roleString.split(',');

    const user = {
      username,
      roles,
      token,
      hasRole(role) {
        return roles.includes(role);
      }
    }
    console.log(user);

    setUser(user);

    return user;
  };

  const logout = () => {
    localStorage.removeItem(TOKEN_KEY);
    setUser(null);
  };

  const auth = {
    user: user ? { ...user } : null,
    login,
    logout,
  };
  return (
    <AuthContext.Provider value={auth}>
      <Router>
        <Header />
        <Switch>
          <Route exact path="/">
            <Home />
          </Route>
          <Route exact path="/agent">
            {auth.user ? <Agents /> : <Redirect to="/login" />}
          </Route>
          <Route path="/agent/add">
            {auth.user ? <AddAgent /> : <Redirect to="/login" />}
          </Route>
          <Route path="/agent/edit/:agentId">
            {auth.user ? <EditAgent /> : <Redirect to="/login" />}
          </Route>
          <Route path="/agent/delete/:agentId">
            {auth.user ? <Agents /> : <Redirect to="/login" />}
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          {/* <Route path="/register">
            <Register />
          </Route> */}
          <Route path="*">
            <NotFound />
          </Route>
        </Switch>
      </Router>
    </AuthContext.Provider>
  );
}

export default App;