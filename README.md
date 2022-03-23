# Salamanders Microservice

Salamanders Microservice used for collating information from the OpenCritic API.

## Build Project

To build the project run:

```bash
mvn clean sortpom:sort fmt:format install
```

## Test the project

```bash
mvn clean sortpom:sort fmt:format test
```

##  Run the project

To run the project locally run:

```bash
 mvn spring-boot:run -Dspring-boot.run.profile=local
```