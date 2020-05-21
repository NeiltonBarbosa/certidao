import React from 'react';
import { Router } from 'react-router-dom';
import { ToastContainer, Zoom } from 'react-toastify';
import Routes from '~/routes';
import history from './services/history';

import GlobalStyles from './styles/global';

import Header from './components/Header';
import Footer from './components/Footer';
import Label from './components/Label';

function App() {
  return (
    <Router history={history}>
      <Header />
      <Label />
      <Routes />
      <Footer />
      <GlobalStyles />
      <ToastContainer
        transition={Zoom}
        className="custom-toast-container"
        toastClassName="custom-toast"
        progressClassName="custom-toast-progress"
        autoClose={3000}
      />
    </Router>
  );
}

export default App;
