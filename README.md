# Springboot Application with MySQL Docker Container

This is a Java Springboot application that utilizes Docker to run as a container along with a MySQL image. The application provides RESTful endpoints to manage user records.

## Prerequisites
- Docker
- Maven

## How to Run

1. Clone this repository:
   ```
   git clone <repository_url>
   cd <repository_name>
   ```

2. Build and run the application with Docker:
   ```
   docker-compose up --build
   ```

This command will build the server, run the unit tests, build the Docker image, and start the Docker container with both the MySQL image and the built API image.

## Accessing the Server

The server will be served on port 5000.

## Endpoints

- **GET /users**: Returns a list of all users.
- **GET /users/{id}**: Returns the user with the specified id.
- **POST /users**: Creates a new user record based on the request body.
- **PUT /users/{id}**: Updates the user with the specified id based on the request body.
- **DELETE /users/{id}**: Deletes the user with the specified id.

## Technologies Used

- Java Springboot
- Docker
- MySQL

## Notes

- Ensure that Docker is properly installed and running on your system before executing the `docker-compose` command.
- Unit tests can be run independently using the `mvn clean package` command.
- Make sure no other services are running on port 5000 to avoid conflicts with the Springboot server.

Feel free to reach out with any questions or issues!