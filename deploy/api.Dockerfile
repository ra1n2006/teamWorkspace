FROM maven:3.9.16-eclipse-temurin-21

WORKDIR /app

COPY backend/pom.xml ./

RUN mvn dependency:go-offline

COPY backend/ ./

RUN mvn package -DskipTests
