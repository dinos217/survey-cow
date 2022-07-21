# SurveyCow [WIP]
Spring Boot application that demonstrates a survey system. Its main purpose is to save the answers of a survey but there is also the implementation for creating a new survey. 
When a new answer is saved in db, a message is produced, sent and received with Kafka which is supposed to run in a Docker container. 
It is written using Java 17 and the services are exposed with a REST API.

## Getting Started
In order to build and run this application locally, you need to have installed: 
1) [jdk 17](https://www.oracle.com/java/technologies/downloads)
2) [PostgreSQL](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) 
3) [Docker Desktop and Docker Compose](https://www.docker.com/products/docker-desktop)

If Docker is installed then head to the directory where this project was cloned (e.g.: C:\User\My Projects\survey-cow) and where the `docker-compose.yml` exists. Open your CLI and type: 
`docker-compose -f docker-compose.yml up -d`. This will generate the 2 containers and images needed for Kafka. 
Then from Docker Desktop open a CLI in kafka container and type `kafka-topics.sh --bootstrap-server localhost:9092 --topic answer --create --partitions 3 --replication-factor 1` to create the topic that is used in the project.

### Database 
This application uses a [PostgreSQL](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) db. Once you have it installed, create a new database named `postgres` and then create a new schema named `surveycow`.
You can use the credentials found in `spring.datasource.username` & `spring.datasource.password` *in* `src/main/resources/application.properties` file or use your own and then change them accordingly.

### Get the application up & running 
Once the application is successfully loaded in your IDE you can:
- `spring-boot:run` from maven plugins or
- run the main method in `com.project.gameclub.GameclubApplication`

Now the application listens to your `localhost:8080`.

## Understanding and Testing the application
The documentation and the endpoints collection were exported from Postman. 
- Here you may find the [Documentation](https://documenter.getpostman.com/view/7555836/UzR1JN4n)
- and the [Postman Collection](https://github.com/dinos217/survey-cow/files/9155138/SurveyCow.Endpoints.postman_collection.zip)
