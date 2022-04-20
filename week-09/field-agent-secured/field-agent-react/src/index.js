import React from 'react';
import * as ReactDOMClient from 'react-dom/client';
import './index.css';
import App from './App';

const container = document.getElementById('root');

const root = ReactDOMClient.createRoot(container);

root.render(<App tab="home" />);


// ReactDOM.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>,
//   document.getElementById('root')
// );



// import * as ReactDOMClient from 'react-dom/client';
// import App from 'App';

// const container = document.getElementById('app');

// // Create a root.
// const root = ReactDOMClient.createRoot(container);

// // Initial render: Render an element to the root.
// root.render(<App tab="home" />);
