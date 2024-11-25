# Breakable Toy II | Flight Search App

## Overview

The goal of this application is to enable users to search for flights using the Amadeus REST API. The project consists of two main parts: the **Backend** (using Spring Boot) and the **Frontend** (using React and TypeScript). The application allows users to search for flight offers, retrieve airport codes, and get airline information.

---

## Tech Stack

### Frontend

- **React**: JavaScript library for building user interfaces.
- **TypeScript**: Typed superset of JavaScript for better development experience.
- **Vite**: A build tool that provides fast development and hot-reloading.

### Backend (REST API)

- **Java**: The programming language used for backend development.
- **Spring Boot**: Java framework to create stand-alone, production-grade applications.
- **Gradle**: A build automation tool used to build and manage the backend project.

---

## Main Features

- **Flight Search**: Users can search for available flight offers by providing various search parameters (such as origin, destination, date, etc.).
- **Airport Search**: Retrieve airport codes and information using Amadeus API.
- **Airline Lookup**: Get airline details (such as airline name and IATA code) using Amadeus API.
- **Dockerization**: The entire project is Dockerized, with both the backend and frontend components having their respective Dockerfiles and Docker Compose configuration.

---

## Setup

Before you run this project, make sure you have the following prerequisites installed:

- **Docker**: To build and run the project using Docker.
- **Node.js**: To run the frontend (React) project.
- **Java 21**: To build and run the backend (Spring Boot) project.
- **Gradle**: To build the backend project (already installed in the backend Docker image).

---

### 1. Clone this repository

```bash
git clone https://github.com/francisco-renteria/FlightSearchApp.git
cd FlightSearchApp
```

---

### 2. Set Backend Variables

In the `server/` directory, you need to configure the Amadeus API credentials in the `application.properties` file.

Add the following lines to `server/src/main/resources/application.properties`:

```
api.key=<your_api_key>
api.secret=<your_api_secret>
```

These properties will be used by the backend to authenticate your requests to the Amadeus API.

---

### 2. Run the Entire Application Using Docker Compose

To build and run both the backend and frontend using Docker Compose, use the following steps:

1. Navigate to the root directory of the project.

2. Run Docker Compose to build and start both services (backend and frontend):

   ```
   docker-compose up --build
   ```

This will build the images for both the backend and frontend, then start both services.

By default:

- The backend will be available at `http://localhost:9090`.
- The frontend will be available at `http://localhost:8080`.

---

## Amadeus API Integration

To integrate with the Amadeus API, you need to create an API key and secret. Follow these steps:

1. Sign in to the [Amadeus for Developers](https://developers.amadeus.com/) portal.
2. Create a new application to get your **API Key** and **API Secret**.

### Amadeus API endpoints used

- **Airport and City Search**: [API Reference](https://developers.amadeus.com/self-service/category/flights/api-doc/airport-and-city-search/api-reference)
- **Airline Code Lookup**: [API Reference](https://developers.amadeus.com/self-service/category/flights/api-doc/airline-code-lookup/api-reference)
- **Flight Offers Search**: [API Reference](https://developers.amadeus.com/self-service/category/air/api-doc/flight-offers-search)
