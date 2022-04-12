import React, { useState } from "react";
import { Redirect } from "react-router-dom";
import { BrowserRouter as Router, Route, Switch, Redirect } from "react-router-dom";
import jwt_decode from 'jwt-decode';
import { useEffect, useState } from "react";
import UserContext from "./context/AuthContext.js";
import AuthContext from "./context/AuthContext";
import Register from "./components/Register";
import NotFound from "./components/NotFound";
import Home from "./components/Home";
import Login from "./components/Login";
import NavBar from "./components/NavBar";
import AddAgent from "./components/AddAgent";
import EditAgent from "./components/EditAgent";
import Agents from "./components/Agents";



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
        <Router>
            <AuthContext.Provider value={auth}>
                <NavBar />
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
          <Route path="/agent/edit/:id">
            {auth.user ? <EditAgent /> : <Redirect to="/login" />}
          </Route>
          <Route path="/agent/delete/:id">
            {auth.user ? <Agents /> : <Redirect to="/login" />}
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="*">
            <NotFound />
          </Route>
                </Switch>
            </AuthContext.Provider>
        </Router >
    );
}

export default App;