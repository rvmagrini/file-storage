import React, {useState, useEffect} from 'react';
import './App.css';
import axios from 'axios';

// Creating functional component (similar to ComponentDidMount)
const UserProfiles = () => {
  
  // Invoking axios 
  const fetchUserProfiles = () => {
      axios.get("http://localhost:8080/api/v1/user-profile").then( res => {
        console.log(res);
      });
  }

  useEffect(()=> {
    fetchUserProfiles();
  }, []);

  return <h1>FileStorage</h1>;

};

function App() {
  return (
    <div className="App">

      <UserProfiles />
      
    </div>
  );
}

export default App;
