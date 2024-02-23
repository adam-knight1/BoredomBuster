import React, { useState, useEffect } from 'react';

function App() {
  // this state to hold the dog information, set to null init
  const [dog, setDog] = useState(null);

  // after the component mounts, this fetches the dog data
  useEffect(() => {
    fetch('/api/dogs?name=golden retriever') //GET, need to put correct url in
      .then(response => response.json())
      .then(data => {
        console.log(data); // log to see the structure
        setDog(data); // idk if backend will return correctly yet
      })
      .catch(error => console.error('There was an error!', error));
  }, []); //runs once after initial render?

  return (
    <div className="App">
      <header className="App-header">
        {dog ? (
          <div>
            <h1>{dog.name}</h1> {/* dog name*/}
            {/* the other stuff */}
          </div>
        ) : (
          <p>Loading...</p> //will integrate more detailed load message.
        )}
      </header>
    </div>
  );
}

export default App;
