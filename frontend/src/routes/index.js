import React from 'react';
import { Switch, BrowserRouter } from 'react-router-dom';

import Route from './Route';

import Home from '~/pages/Home';
import Error from '~/pages/Error';

export default function Routes() {
  return (
    <BrowserRouter basename={process.env.REACT_APP_ROUTER_BASE || ''}>
      <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/error" component={Error} />
      </Switch>
    </BrowserRouter>
  );
}
