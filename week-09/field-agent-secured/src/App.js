import NotFound from "./components/NotFound";
import NavBar from "./components/NavBar";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";



function App() {
  return (
<Router>
  <NavBar />
        <Switch>
            <Route exact path="/">
                <Home />
            </Route>;
            <Route exact path="/">
                <Register />
            </Route>;
            <Route exact path="/">
                <Login />
            </Route>;
            <Route exact path="/">
                <Agents />
            </Route>;
            <Route exact path="/">
                <AddAgent />
            </Route>;
            <Route exact path="/">
                <EditAgent />
            </Route>;
            <Route exact path="/">
                <DeleteAgent />
            </Route>;
            <Route>
                <NotFound />
            </Route>;
        </Switch>
      </Router>
  );
}
