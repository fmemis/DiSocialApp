import '../static/App.css';
import Home from './components/home/home'
import Login from './components/login/login'
import SignUp from './components/login/signup'
import '../../../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link} from "react-router-dom";

function App() {
  return (
    <Router>

<div className="App">
      <nav className="navbar navbar-expand-lg navbar-light fixed-top">
        <div className="container">
          <Link className="navbar-brand" to={"/sign-in"}>DiS</Link>
          <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul className="navbar-nav ml-auto">
            <li className="nav-item">
                <Link className="nav-link" to={"/"}>Home</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to={"/sign-in"}>Login</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to={"/sign-up"}>Sign up</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>

    <div className="auth-wrapper">
    <div className="auth-inner">
      <Switch>
        <Route exact path='/home' component={Home} />
        <Route path="/api/auth/signin" component={Login} />
        <Route path="/api/auth/signup" component={SignUp} />
      </Switch>
    </div>
    </div>
    </div>
    </Router>
  );
}


export default App;