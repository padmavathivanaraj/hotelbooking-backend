# Build Stage - build the Spring Boot jar
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build jar file (skip tests)
RUN mvn -B clean package -DskipTests

# Run Stage - run the jar
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy built jar from first stage
COPY --from=build /app/target/*.jar app.jar

# App listens on this port (Render gives PORT env var, we mapped in application.yml)
EXPOSE 8080

# Start the application
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
