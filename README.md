# user-service

<!-- TOC -->
* [user-service](#user-service)
  * [Why: Purpose of the application](#why-purpose-of-the-application)
  * [How: How to run it and utilities](#how-how-to-run-it-and-utilities)
    * [Running application locally](#running-application-locally)
  * [What: Features and technologies in place](#what-features-and-technologies-in-place)
    * [Summary of tools and technologies](#summary-of-tools-and-technologies)
    * [API](#api-)
    * [Hexagonal Architecture](#hexagonal-architecture)
    * [Database](#database)
    * [Kafka](#kafka)
    * [Docker](#docker)
    * [Tests](#tests)
  * [Where: Things of interest](#where-things-of-interest)
    * [Kafka UI](#kafka-ui)
    * [Postman](#postman)
    * [Jacoco Reports](#jacoco-reports)
  * [TODO: Things to do with more time](#todo-things-to-do-with-more-time)
    * [bugfix](#bugfix)
    * [features](#features)
  * [Who: Alvaro Longueira](#who-alvaro-longueira)
<!-- TOC -->

## Why: Purpose of the application
Application that provide a CRUD to manage Users. 

This has been done using SOLID principles and Hexagonal Architecture

## How: How to run it and utilities
This is a multimodule java application. There are two ways to run it

### Running application locally
1) There are two ways of start the application:
* In the IDE start the main class  [UserServiceApplication.java](main/src/main/java/com/alvarolongueira/user/service/UserServiceApplication.java)

* Or you can generate a jar to run inside a docker container, for that run next command (*)

```bash
./mvnw clean install
````

(*) Because of a pending bug (see [bugfix section](#bugfix)) maybe you need to skip tests, in that case run this command
```bash
./mvnw clean install -DskipTests
````

2) Build and run all the services in docker. Take into account comment/uncomment service `app` depending of previous step 1
```bash
docker compose up
````

3) There is a postman collection in folder `/postman` to try any endpoint

## What: Features and technologies in place
### Summary of tools and technologies
* API first
* Open API
* Stoplight
* Swagger
* Spring
* Postman
* Kafka
* Docker
* Postgres
* Liquibase
* Jacoco
* Mockito
* Testcontainers
* Lombok
* Mapstruct

### API 
Following API first design and using `stoplight`, the OpenApi definition has been done before to implement any code

It has been used `openapi-generator-maven-plugin` to generate all the models and interfaces inside the module `rest-api-spec`

### Hexagonal Architecture
Application has been designed following this pattern, so each module has only the needed dependencies. Interfaces (ports) and implementations (adapters) are placed in the corresponding module.

### Database
This has been implemented using Liquibase and PostgreSQL. Easy to implement and maintain

### Kafka
To notify other services of any change in any user, it has been implemented kafka, which has three topics depending of the action
* create topic: `kafka_user_create`
* update topic: `kafka_user_update`
* delete topic: `kafka_user_delete`

### Docker
There is a docker compose with all the services needed to run this application locally

### Tests
There is an integration test with the happy path for all the endpoints [UserIntegrationTest.java](main/src/test/java/com/alvarolongueira/user/service/UserIntegrationTest.java)

It has been used testcontainers so there is no need of real connections to test it.

Also, there are some basic unit tests in module `application`(*For more information see )

## Where: Things of interest

### Kafka UI
Docker compose provides an UI to monitor kafka http://localhost:9081/

### Postman
There is a folder `postman` with a collection postman that can be used to call all the `health` endpoints and `user crud` endpoints

### Jacoco Reports
Jacoco reports are generated inside the module `report`

## TODO: Things to do with more time

### bugfix
These are problems that should be fixed before deliver this project, but because of lack of time, they are still pending.

* There is a problem when testcontainers start. Test shouldn't start until they are running and in healthy status, but in some cases tests start to run before to connect to testcontainers ports. It causes false positives, even breaking package of the jar

* When kafka messages are validated in integration tests, sometimes they take too much time to be received, causing timeouts and false positives

### features
Things that I would like have done having more time

* Increase the amount and quality of unit tests to have a better code coverage and testing edge cases and wrong scenarios
* Increase the amount and quality of integration tests
* Add profiles and include variables in the application.yml
* User entity has Id as primary key, probably mail or nickname should be something to validate and prevent duplication
* Hide password information field in all the application
* Implement grpc as requested in the challenge (although it was optional)

## Who: Alvaro Longueira
* **Alvaro Longueira** - [GitHub](https://github.com/alvarolongueira)


