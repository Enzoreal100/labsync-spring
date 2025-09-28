# Stage 1: Build the application
# Use a Maven image with JDK 21 to match the project's Java version
FROM maven:3.9-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml to leverage Docker layer caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application, creating the executable JAR
# Skipping tests to speed up the build process in a CI/CD environment
RUN ./mvnw clean install -DskipTests

# Stage 2: Create the final, lightweight runtime image
# Use a JRE image for a smaller footprint
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the executable JAR from the build stage
# The artifact name is defined in pom.xml (artifactId-version.jar)
COPY --from=build /app/target/labsync-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Set the entrypoint to run the application.
# Environment variables like DB_URL, DB_USERNAME, DB_PASSWORD, and API_KEY
# must be provided at runtime when starting the container.
ENTRYPOINT ["java", "-jar", "app.jar"]
