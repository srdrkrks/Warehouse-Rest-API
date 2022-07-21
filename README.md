# Getting Started Warehouse Assignment

### Install Instructions

## With Docker

Go to project directory and run only following command

> docker-compose up

This command build the project with default profile configuration, create database server container for api from Mysql 8
image and network, at the end execute schema.sql and data.sql files to create tables and insert box datas.

### To Run Locally  Without Docker

Create database (name is "warehouse") and execute sql files /resources/schema.sql  and "/resources/data.sql"

Set active profile to dev to run locally

> mvn clean install -DskipTests

## Documentation With Swagger v3

If runs with dev profile

http://localhost:8081/swagger-ui/index.html

If runs without profile

http://localhost:8080/swagger-ui/index.html

## Metrics

If runs with dev profile

http://localhost:8081/actuator/health

If runs without profile

http://localhost:8080/actuator/health

Result
> {
"status": "UP",
"components": {
"db": {
"status": "UP",
"details": {
"database": "MySQL",
"validationQuery": "isValid()"
> }
> },
"diskSpace": {
"status": "UP",
"details": {
"total": 379000430592,
"free": 58169552896,
"threshold": 10485760,
"exists": true
> }
> },
"ping": {
"status": "UP"
> }
> }
> }

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#actuator)


