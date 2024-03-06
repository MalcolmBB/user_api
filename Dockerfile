FROM maven:3.6.3-jdk-11 AS build
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package
# Use an official OpenJDK image as the base image
FROM openjdk:11
# Set the working directory in the container
WORKDIR /app
# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/user_api-*.jar ./my-application.jar
# Set the command to run the application
CMD ["java", "-jar", "my-application.jar"]