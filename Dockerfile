#
# Build & Run
FROM maven:3.6.3-jdk-11-slim AS build

WORKDIR usr/src/app

COPY . ./
ARG DATA_PATH=/src/main/resources

RUN mvn clean install -DskipTests

FROM openjdk:11-jre-slim

ARG JAR_NAME="warehouse-0.0.1-SNAPSHOT"

WORKDIR /usr/src/app

EXPOSE ${HTTP_PORT}

COPY --from=build /usr/src/app/target/${JAR_NAME}.jar ./app.jar

CMD ["java","-jar", "./app.jar"]

