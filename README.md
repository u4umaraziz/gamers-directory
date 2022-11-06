# Gamers Directory Application
This project is developed using spring boot framework with in-memory H2 database:
<br> Players and Game data is already setup in application for testing purpose. However, APIs are also available to create new Player and link with any game 
* PORT: 8081
* Application can be access on local machine using following URL: http://localhost:8081

# Getting Started

### Tools & Technologies
* Java 8
* Spring Boot 2.6.13
* H2 In-Memory Database
* Restful APIs
* JUnit 5 

### API Documentation
For further reference, please consider the following sections:

* [Swagger URL](http://localhost:8081/swagger-ui.htm)


### Database Details
In-Memory database is used which is H2:
<br>Following are schema setup files which are loaded during application startup

#### schema.sql
    For schema setup
Players, Games, GameMapping tables and REGISTERED_GAMES_VIEW will be created using this script file. 
Each table has it's own unique ID (PK) and UUID which will be actually used to share in subsequent API requests. 
#### data.sql
    Initial Data preparation 

#### Console URL
    http://{host}:8081/h2-console

#### DB Username & Password
    Username: sa <br>
    Password: 

#### Test Execution
    mvn clean install OR
    mvn clean test



