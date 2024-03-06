package com.malcolmbaatjies.user.controllers;

import com.malcolmbaatjies.user.entities.exceptions.UserExistsException;
import com.malcolmbaatjies.user.entities.exceptions.UserNotFoundException;
import com.malcolmbaatjies.user.entities.models.User;
import com.malcolmbaatjies.user.entities.requests.NewUserRequest;
import com.malcolmbaatjies.user.entities.requests.UpdateUserRequest;
import com.malcolmbaatjies.user.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * UserController
 * <br><br>
 * This class is used to handle the user related requests.
 * <br><br>
 * This class is annotated with Lombok's {@link lombok.extern.log4j.Log4j2} to enable logging.
 * <br><br>
 * This class is annotated with Spring's {@link org.springframework.web.bind.annotation.RestController} to indicate that it is used to handle requests and to return the response as JSON.
 * <br><br>
 * This class is annotated with Spring's {@link org.springframework.web.bind.annotation.RequestMapping} to specify the base URL for the controller.
 */
@RestController
@RequestMapping("/users")
@Log4j2
public class UserController
{
	/**
	 * The user repository.
	 */
	private final UserRepository userRepository;

	/**
	 * Constructor
	 * <br><br>
	 * This constructor is used to inject the {@link com.malcolmbaatjies.user.repositories.UserRepository} into the UserController.
	 * <br><br>
	 * @param userRepository The user repository
	 */
	@Autowired
	public UserController(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	/**
	 * Get all users.
	 * <br><br>
	 * This method is used to get all the users from the database.
	 * <br><br>
	 * @return List of users
	 */
	@GetMapping("")
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}

	/**
	 * Get a user by id.
	 * <br><br>
	 * This method is used to get a user from the database by id.
	 * <br><br>
	 * @param id The user's id.
	 * @return The user
	 * @throws UserNotFoundException Thrown when the user is not found.
	 */
	@GetMapping("/{id}")
	public User getUser(@PathVariable String id)
	{
		return userRepository.findById(Long.parseLong(id)).orElseThrow(
				() -> new UserNotFoundException(id)
		);
	}

	/**
	 * Create a user.
	 * <br><br>
	 * This method is used to create a user in the database.
	 * <br><br>
	 * @param newUserRequest The new user details.
	 * @return The created user
	 * @throws UserExistsException Thrown when the user already exists.
	 */
	@PostMapping("")
	public ResponseEntity<User> createUser(@RequestBody @Valid NewUserRequest newUserRequest)
	{
		// Business Logic added in the Controller based on the requirements limiting the use of a service layer to keep the controller as 'Skinny' as possible. "Use constructor injection to inject the UserRepository into the UserController."
		if(userRepository.existsUserByEmail(newUserRequest.getEmail()))
		{
			throw new UserExistsException(newUserRequest.getEmail());
		}

		User newUser = userRepository.save(
			User.builder()
				.firstName(newUserRequest.getFirstName())
				.lastName(newUserRequest.getLastName())
				.email(newUserRequest.getEmail())
			.build()
		);

		return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/users/" + newUser.getId()).body(newUser);
	}

	/**
	 * Update a user.
	 * <br><br>
	 * This method is used to update a user in the database.
	 * <br><br>
	 * @param id The user's id.
	 * @param updateUserRequest The updated user details.
	 * @return The updated user
	 * @throws UserNotFoundException Thrown when the user is not found.
	 * @throws UserExistsException Thrown when the user already exists.
	 */
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody @Valid UpdateUserRequest updateUserRequest)
	{
		User existingUser = userRepository.findById(Long.parseLong(id)).orElseThrow(
			() -> new UserNotFoundException(id)
		);

		// Check if the email is being updated and if it exists in the database
		if (updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().equals(existingUser.getEmail()) && userRepository.existsUserByEmail(updateUserRequest.getEmail()))
		{
			throw new UserExistsException(updateUserRequest.getEmail());
		}

		User updatedUser = userRepository.save(
			User.builder()
				.id(Long.parseLong(id))
				.firstName(Objects.requireNonNullElseGet(updateUserRequest.getFirstName(), existingUser::getFirstName))
				.lastName(Objects.requireNonNullElseGet(updateUserRequest.getLastName(), existingUser::getLastName))
				.email(Objects.requireNonNullElseGet(updateUserRequest.getEmail(), existingUser::getEmail))
			.build()
		);

		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}

	/**
	 * Delete a user.
	 * <br><br>
	 * This method is used to delete a user from the database.
	 * <br><br>
	 * @param id The user's id.
	 */
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id)
	{
		userRepository.deleteById(Long.parseLong(id));
	}
}
