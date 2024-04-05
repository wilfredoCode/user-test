
FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ ./src/
RUN mvn package


FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/user-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3002
CMD ["java", "-jar", "app.jar"]