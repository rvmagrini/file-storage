import React, {useState, useEffect, useCallback} from 'react';
import './App.css';
import axios from 'axios';
import {useDropzone} from 'react-dropzone'


// Creating functional component (similar to ComponentDidMount)
const UserProfiles = () => {

  const [userProfiles, setUserProfiles] = useState([]);
  


  // Invoking axios 
  const fetchUserProfiles = () => {
      axios.get("http://localhost:8080/api/v1/user-profile").then( res => {
        console.log(res);
        setUserProfiles(res.data);
      });
  }

  useEffect(()=> {
    fetchUserProfiles();
  }, []);

  return userProfiles.map((userProfile, index) => {
    return (
      <div key={index} className="profile-box">
        <h1>{userProfile.username}</h1>
        <p>{userProfile.userProfileId}</p>
        <div className="drop-box">
        <Dropzone />
        </div>
        
      </div>
    )
  });

};

// Dropzone
function Dropzone() {
  const onDrop = useCallback(acceptedFiles => {
    const file = acceptedFiles[0];
    console.log(file);
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the files here ...</p> :
          <p>Drag 'n' drop some files here, or click to select files</p>
      }
    </div>
  )
}


function App() {
  return (
    <div className="App">

      <UserProfiles />
      
    </div>
  );
}

export default App;
