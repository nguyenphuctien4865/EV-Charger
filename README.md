# EV Charger Search Backend

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen)
![Java](https://img.shields.io/badge/Java-11-orange)
![Elasticsearch](https://img.shields.io/badge/Elasticsearch-supported-blue)

## Introduction

This project is the backend for an EV Charger Search web application. It provides a robust API for searching and managing electric vehicle charging stations using Spring Boot and Elasticsearch.

## Features

- Search for EV chargers by location, type, and availability
- Calculate distances between user location and charging stations
- Mark favorite charging stations
- Advanced filtering options
- Comprehensive charger details

## Technologies

- **Spring Boot**: Provides the foundation for building stand-alone, production-grade Spring-based Applications.
- **Elasticsearch**: Powers efficient indexing and searching of EV charger data.
- **JPA**: Simplifies database operations and ORM.
- **H2 Database**: Used for development and testing.
- **Lombok**: Reduces boilerplate code in Java.
- **Retrofit**: Type-safe HTTP client for API integrations.
- **ModelMapper**: Simplifies object mapping between different models.
- **Swagger/OpenAPI**: API documentation and testing interface.

## Getting Started

### Prerequisites

- Java 11
- Maven
- Elasticsearch (make sure it's running and accessible)

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/ev-charger-search-backend.git
   ```

2. Navigate to the project directory:
   ```
   cd ev-charger-search-backend
   ```

3. Build the project:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## API Documentation

Once the application is running, you can access the Swagger UI for API documentation and testing at:
