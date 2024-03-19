# BoredomBuster Backend

BoredomBuster is an interactive backend application built using Spring, designed to serve a variety of informational and entertaining APIs. 
It powers the frontend React/Next.js application, which can be found [here](https://github.com/adam-knight1/boredombusterfrontend).

The live application can be viewed at https://www.boredombuster.fyi

## Current Features

- AI assistant powered by Chat GPT 3.5 turbo
- Chess engine powered by Stockfish
- Weather: Searching for weather info by city/state or zip
- Trivia: A large collection of trivia questions and answers
- Information about various dog breeds
- And more...

## Frontend Application

The frontend for BoredomBuster is a React/Next.js application that consumes the APIs provided by this backend 
You can start the frontend locally by running `npm run dev` if you have the repository cloned or on Github: [BoredomBusterFrontend](https://github.com/adam-knight1/boredombusterfrontend).  You can also check it out the live application at https://boredombuster.fyi

## Getting Started Locally

If you'd like to run the project locally, follow the simple steps below...

### Prerequisites

- JDK 11 or later
- Gradle

### Installation

1. Clone the backend repository
2. Navigate to the cloned directory: cd boredombusterbackend
3. Use the Gradle Wrapper to build and run the project: ./gradlew bootRun

The server will start, and you can begin to make requests to http://localhost:8080/

### Future Direction

This is a new project that is being continually developed, with updates generally being made on a daily basis.
Please refer back to GitHub for the latest version.

Stockfish source code available [here](https://github.com/official-stockfish/Stockfish)


