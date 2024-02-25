/*
import React, { useState, useEffect } from 'react';

function App() {
  // this state to hold the dog information, set to null init
  const [dog, setDog] = useState(null);

  // after the component mounts, this fetches the dog data
  useEffect(() => {
    const breedName = "golden retriever"; // for temporary example
    fetch(`/api/dogs/dog?breedName=${encodeURIComponent(breedName)}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log(data);
        setDog(data);
      })
      .catch(error => console.error('There was an error!', error));
  }, []);



   return (
     <div className="App">
       <header className="App-header">
         {dog ? (
           <div>
             <h1>{dog.name}</h1>
             <img src={dog.imageLink} alt={dog.name} style={{ maxWidth: "200px", maxHeight: "200px" }} />
             <p>Grooming: {dog.grooming}</p>
             <p>Good with children: {dog.goodWithChildren}</p>
             <p>Good with other dogs: {dog.goodWithOtherDogs}</p>
             <p>Shedding level: {dog.shedding}</p>
             <p>Energy level: {dog.energy}</p>
             <p>Trainability: {dog.trainability}</p>
             <p>Minimum life expectancy: {dog.minLifeExpectancy} years</p>
             <p>Maximum life expectancy: {dog.maxLifeExpectancy} years</p>
           </div>
         ) : (
           <p>Loading...</p>
         )}
       </header>
     </div>
   );
 }

export default App;
*/
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import DogFacts from './DogFacts.js';

function App() {
  return (
    <Router>
      <div className="App">
        <header className="App-header">
          <h1>Welcome to the Boredom Buster App</h1>
          <nav>
            <ul>
              <li>
                <Link to="/dog-facts">Go to Dog Facts</Link>
              </li>
              {/* Add more navigation links eventually */}
            </ul>
          </nav>
        </header>
        <Routes>
          <Route path="/dog-facts" element={<DogFacts />} />
          {/* I can add more Route components for other paths as needed */}
          <Route path="/" element={<h2>Click on the links above to navigate through the app.</h2>} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
