import React, { useState } from 'react';
import './App.css';

function DogFacts() {
  const [breedName, setBreedName] = useState('');
  const [dog, setDog] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const fetchDogInfo = () => {
    setLoading(true);
    setError(''); // Clear previous errors
    fetch(`/api/dogs/dog?breedName=${encodeURIComponent(breedName)}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        setDog(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('There was an error!', error.message);
        setError('Failed to fetch dog information. ' + error.message);
        setLoading(false);
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetchDogInfo();
  };

  return (
    <div className="DogFacts">
      <h1>Dog Facts</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={breedName}
          onChange={(e) => setBreedName(e.target.value)}
          placeholder="Enter dog breed"
        />
        <button type="submit">Get Facts</button>
      </form>
      {loading && <p>Loading...</p>}
      {error && <p className="error">{error}</p>}
      {dog && (
        <div className="dog-info">
          <h2>{dog.name}</h2>
          <img src={dog.imageLink} alt={dog.name} style={{ width: '100%', maxWidth: '300px', height: 'auto' }} />
          <p>Grooming: {dog.grooming}</p>
          <p>Good with children: {dog.goodWithChildren === 1 ? 'Yes' : 'No'}</p>
          <p>Good with other dogs: {dog.goodWithOtherDogs === 1 ? 'Yes' : 'No'}</p>
          <p>Shedding level: {dog.shedding}</p>
          <p>Energy level: {dog.energy}</p>
          <p>Trainability: {dog.trainability}</p>
          <p>Life expectancy: {dog.minLifeExpectancy}-{dog.maxLifeExpectancy} years</p>
        </div>
      )}
    </div>
  );
}

export default DogFacts;
