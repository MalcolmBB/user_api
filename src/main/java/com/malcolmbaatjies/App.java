package com.malcolmbaatjies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * App
 * <br><br>
 * This class is used to start the Spring Boot application.
 * <br><br>
 * This class is annotated with Spring's {@link org.springframework.boot.autoconfigure.SpringBootApplication} to indicate that it is the main class of the Spring Boot application.
 */
@SpringBootApplication
public class App
{
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}