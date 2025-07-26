FROM maven:3.9.6-eclipse-temurin-21

# OR openjdk image with manual maven installation
# FROM openjdk:21-jdk
# RUN apt-get update \
#   && apt-get install -y maven \
#   && rm -rf /var/lib/apt/lists/*
# COPY . .
# RUN mvn clean package

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
RUN useradd app

RUN mv target/*.jar app.jar

USER app
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app/app.jar"]
EXPOSE 8080 