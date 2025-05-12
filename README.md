# User Service

<!-- TOC -->
* [User Service](#user-service)
  * [Purpose of the application](#purpose-of-the-application)
  * [How to run it and utilities](#how-to-run-it-and-utilities)
    * [Running application locally](#running-application-locally)
    * [Postman](#postman)
    * [Kafka UI](#kafka-ui)
  * [Features and technologies in place](#features-and-technologies-in-place)
    * [Summary of tools and technologies](#summary-of-tools-and-technologies)
    * [API](#api-)
    * [Hexagonal Architecture](#hexagonal-architecture)
    * [Database](#database)
    * [Kafka](#kafka)
    * [Docker](#docker)
    * [Tests](#tests)
    * [Jacoco Reports](#jacoco-reports)
  * [TODO: Things to do with more time](#todo-things-to-do-with-more-time)
    * [bugfix](#bugfix)
    * [features](#features)
  * [Author: Alvaro Longueira](#author-alvaro-longueira)
<!-- TOC -->

## Purpose of the application
Application that provide a CRUD to manage Users. 

This has been done using SOLID principles and Hexagonal Architecture.

## How to run it and utilities

### Running application locally
1) There are two ways of start the application:
* In the IDE start the main class  [UserServiceApplication.java](main/src/main/java/com/alvarolongueira/user/service/UserServiceApplication.java)

* Or you can generate a jar to run inside a docker container, for that run next command(*)

```bash
./mvnw clean install
````

(*) Because of a pending bug (see [bugfix section](#bugfix)) maybe you need to skip tests, in that case run this command.
```bash
./mvnw clean install -DskipTests
````

2) Build and run all the services in docker. Take into account comment/uncomment service `app` depending of previous step 1.
```bash
docker compose up
````

### Postman
There is a folder `postman` with a collection that can be used to call all the `health` endpoints and `user crud` endpoints.

### Kafka UI
Docker compose provides an UI to monitor kafka http://localhost:9081/.


## Features and technologies in place
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
Following API first design and using `stoplight`, the OpenApi definition has been done before to implement any code.

It has been used `openapi-generator-maven-plugin` to generate all the models and interfaces inside the module `rest-api-spec`.

### Hexagonal Architecture
Application has been designed following this pattern, so each module has only the needed dependencies. Interfaces (ports) and implementations (adapters) are placed in the corresponding module.

### Database
For ease and speed the chosen database is PostgreSQL. Also, with Liquibase we provide a way to manage changes in database structure.

### Kafka
To notify other services of any change in any user, it has been implemented kafka, which has three topics depending of the action.
* create topic: `kafka_user_create`
* update topic: `kafka_user_update`
* delete topic: `kafka_user_delete`

### Docker
There is a docker compose with all the services needed to run this application locally.

### Tests
There is an integration test with the happy path for all the endpoints [UserIntegrationTest.java](main/src/test/java/com/alvarolongueira/user/service/UserIntegrationTest.java). It has been used testcontainers so there is no need of real connections to test it.

Also, there are some basic unit tests in module `application` (For more information see [features section](#features)).

### Jacoco Reports
Jacoco reports are generated inside the module `report`.

## TODO: Things to do with more time

### bugfix
These are problems that should be fixed before deliver this project, but because of lack of time, they are still pending:

* There is a problem when testcontainers start. Test shouldn't start until testcontainers are running and in healthy status. But sometimes tests start to run before to connect to testcontainers ports. It causes false positives, even breaking jar packaging.

* When kafka messages are validated in integration tests, sometimes they take too much time to be received, causing timeouts and false positives.

### features
Things that would be nice to have done having more time:

* Increase the amount and quality of unit tests to have a better code coverage. Also test edge cases and wrong scenarios
* Increase the amount and quality of integration tests. Also test edge cases and wrong scenarios.
* Add profiles and include variables in the application.yml.
* User entity has Id as primary key, probably mail or nickname should be something to validate and prevent duplication.
* Hide password information field in all the application.
* Implement grpc as requested in the challenge (although it was optional).

## Author: Alvaro Longueira
* **Alvaro Longueira** - [GitHub](https://github.com/alvarolongueira)


