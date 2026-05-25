# MBTA Transit Tracker

A Spring Boot backend service that polls the MBTA V3 API every 30 seconds and stores live arrival predictions in PostgreSQL.

![Java](https://img.shields.io/badge/Java-21-orange) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-green) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-blue) ![CI](https://github.com/LuccaDeFulgentis/mbta-tracker/actions/workflows/ci.yml/badge.svg)

## Features

- Scheduled polling of the MBTA V3 API every 30 seconds
- Historical prediction storage with `recorded_at` timestamps for delay trend analysis
- Dynamic route and stop creation on first encounter
- REST API for querying live predictions by stop
- Normalized PostgreSQL schema with indexed queries
- Fully containerized with Docker and Docker Compose
- Automated testing via GitHub Actions CI/CD

## API

### Get predictions for a stop

```
GET /api/predictions?stop={stopId}
```

**Example:**
```bash
curl "http://localhost:8080/api/predictions?stop=place-pktrm"
```

**Response:**
```json
[
  {
    "id": "prediction-75324042-70197-70-Green-C",
    "arrivalTime": "2026-05-25T04:22:09Z",
    "departureTime": "2026-05-25T04:23:09Z",
    "status": null,
    "directionId": 0,
    "route": {
      "id": "Green-C",
      "longName": "Green Line C",
      "color": "00843D"
    },
    "stop": {
      "id": "70197",
      "name": "70197"
    }
  }
]
```

## Database Schema

Three tables: `routes`, `stops`, and `predictions`. 

Each prediction has a `recorded_at` timestamp so you can track how arrival estimates change over time


## Installation

Clone the repository:
```bash
git clone https://github.com/LuccaDeFulgentis/mbta-tracker.git
cd mbta-tracker
```

Free MBTA API key from [api-v3.mbta.com](https://api-v3.mbta.com)

### With Docker Compose

Create a `.env` file in the root:
```
MBTA_API_KEY=your_key_here
```

Start everything:
```bash
docker-compose up
```

## CI/CD

Automated tests run on every push to `main` and every pull request via GitHub Actions against a live PostgreSQL instance:

- Spring context load test
- Controller layer tests with MockMvc
- Service layer tests with Mockito

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5 |
| Database | PostgreSQL 18 |
| Migrations | Flyway |
| ORM | Hibernate |
| Containerization | Docker |
| CI/CD | GitHub Actions |
| Testing | JUnit , Mockito |

## Data Source

MBTA V3 API: [api-v3.mbta.com](https://api-v3.mbta.com)

