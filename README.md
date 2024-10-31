# Microservices Architecture Application

This project is a microservices-based application built using Java Spring, integrating with public APIs, utilizing a service discovery server, and monitoring activities with observability tools such as ELK Stack, Zipkin, Prometheus and Grafana. The project is containerized using Docker and deployed on AWS.

## Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Technologies](#technologies)
- [Setup](#setup)
- [Usage](#usage)
- [Monitoring and Observability](#monitoring-and-observability)

## Overview
This application is designed to demonstrate the use of microservices architecture by integrating various public APIs. It includes three main microservices:
- **NBA Microservice**: Fetches data about NBA teams.
- **Population Microservice**: Retrieves population data from data.gov.il.
- **News Microservice**: Provides top headlines from gnews.io.

All services are managed and routed through an API Gateway, with service discovery enabled to manage service instances dynamically.

## Architecture
<img src="https://github.com/avivaws/StarshipNBA/assets/88618518/8a068ff1-c547-4e0f-a5b8-f76d374f5a8b" alt="Architecture" width="500">


The architecture consists of:
- **Clients**: Postman, Mobile App, Web Client
- **API Gateway**: Manages routing of requests to appropriate microservices
- **Microservices**: 
  - NBA Microservice
  - Population Microservice
  - News Microservice
- **Databases**: MongoDB for data persistence
- **Service Discovery**: Handles dynamic discovery of services. This project is using "Netflix Eurika Server"

## Technologies
- **Java Spring**: For building microservices
- **Prometheus**: For metrics collection and monitoring
- **Grafana**: For visualization of metrics
- **Zipkin**: For distributed tracing
- **Docker**: For containerization
- **AWS**: For deployment

## Setup
1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/your-repo.git
    cd your-repo
    ```

2. **Build and run the Docker containers**:
    ```bash
    docker-compose up --build
    ```

3. **Access the services**:
    - UI: `http://localhost:8035`
    - API Gateway: `http://localhost:8080`
    - NBA Microservice: `http://localhost:8081`
    - Population Microservice: `http://localhost:8082`
    - News Microservice: `http://localhost:8083`
    - Prometheus: `http://localhost:9090`
    - Grafana: `http://localhost:3000`
    - Zipkin: `http://localhost:9411`

## Usage

### Client Webserver
<img src="https://github.com/avivaws/StarshipNBA/assets/88618518/97663bfd-3265-4034-b993-b52e3afa8bdb" alt="UI" width="500">


### API Endpoints
- **News Headlines**: 
    ```bash
    GET /news/top-headlines
    ```
- **Population Data**: 
    ```bash
    GET /population/city
    ```
- **NBA Teams**: 
    ```bash
    GET /nba/teams
    ```

Use Postman, a web client, or a mobile app to interact with these endpoints through the API Gateway.

## Monitoring and Observability
The application is monitored using Prometheus and Grafana, and traced using Zipkin.

### Prometheus
Prometheus scrapes metrics from the microservices and the API Gateway. It provides a powerful query language to analyze metrics and alerting capabilities.

### Grafana
Grafana is used to visualize the metrics collected by Prometheus. Pre-configured dashboards display the health and performance of the services.

<img src="https://github.com/avivaws/StarshipNBA/assets/88618518/cd1b9ced-8a19-4f6c-be84-04895233e597" alt="Grafana" width="500">

### Zipkin
Zipkin provides distributed tracing capabilities. It helps in understanding the flow of requests across various microservices and identifying bottlenecks.

<img src="https://github.com/avivaws/StarshipNBA/assets/88618518/33908c4d-3823-40ad-91a1-9db0cc225235" alt="Zipkin" width="500">

## Conclusion
This microservices architecture project demonstrates the integration of various public APIs, dynamic service discovery, and monitoring using Prometheus, Grafana, and Zipkin. It is containerized with Docker and ready for deployment on AWS.
