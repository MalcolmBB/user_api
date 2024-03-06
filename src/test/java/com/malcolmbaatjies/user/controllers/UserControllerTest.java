package com.malcolmbaatjies.user.controllers;

import com.malcolmbaatjies.user.entities.exceptions.UserExistsException;
import com.malcolmbaatjies.user.entities.exceptions.UserNotFoundException;
import com.malcolmbaatjies.user.entities.models.User;
import com.malcolmbaatjies.user.entities.requests.NewUserRequest;
import com.malcolmbaatjies.user.entities.requests.UpdateUserRequest;
import com.malcolmbaatjies.user.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * UserControllerTest
 * <br><br>
 * This class is used to test the {@link com.malcolmbaatjies.user.controllers.UserController UserController} class.
 * <br><br>
 * This class is annotated with JUnit 5's {@link org.junit.jupiter.api.extension.ExtendWith} to enable the {@link org.mockito.junit.jupiter.MockitoExtension MockitoExtension} for the JUnit 5 test.
 */
@ExtendWith(MockitoExtension.class)
class UserControllerTest
{
	/**
	 * The mocked {@link com.malcolmbaatjies.user.repositories.UserRepository UserRepository}.
	 * <br><br>
	 * This is used to mock the UserRepository to allow for testing of the UserController.
	 * <br><br>
	 * This is annotated with Mockito's {@link org.mockito.Mock} to indicate that it is a mock object.
	 */
	@Mock
	private UserRepository mockedUserRepository;

	/**
	 * The {@link com.malcolmbaatjies.user.controllers.UserController UserController}.
	 * <br><br>
	 * This is used to test the UserController.
	 * <br><br>
	 * This is annotated with Mockito's {@link org.mockito.InjectMocks} to indicate that it is the object under test.
	 */
	@InjectMocks
	private UserController userController;

	/**
	 * Get all users.
	 * <br><br>
	 * This tests the {@link com.malcolmbaatjies.user.controllers.UserController#getAllUsers() getAllUsers} method.
	 */
	@Test
	void getAllUsers()
	{
		when(mockedUserRepository.findAll()).thenReturn(List.of(
			User.builder()
				.id(1L)
				.firstName("John")
				.lastName("Doe")
				.email("johndoe@mail.com")
				.build(),
			User.builder()
				.id(2L)
				.firstName("Jane")
				.lastName("Doe")
				.email("janedoe@mail.com")
				.build()
		));

		List<User> users = userController.getAllUsers();

		assertEquals(2, users.size());
		assertEquals("John", users.get(0).getFirstName());
		assertEquals("Doe", users.get(0).getLastName());
		assertEquals("johndoe@mail.com", users.get(0).getEmail());
		assertEquals("Jane", users.get(1).getFirstName());
		assertEquals("Doe", users.get(1).getLastName());
		assertEquals("janedoe@mail.com", users.get(1).getEmail());
	}

	/**
	 * Get user by id.
	 * <br><br>
	 * This tests the {@link com.malcolmbaatjies.user.controllers.UserController#getUser(String) getUser} method.
	 */
	@Test
	void getUser200()
	{
		when(mockedUserRepository.findById(1L)).thenReturn(java.util.Optional.of(
			User.builder()
				.id(1L)
				.firstName("John")
				.lastName("Doe")
				.email("johndoe@mail.com")
				.build()
		));

		User userResponse = userController.getUser("1");

		assertEquals("John", userResponse.getFirstName());
		assertEquals("Doe", userResponse.getLastName());
		assertEquals("johndoe@mail.com", userResponse.getEmail());
	}

	/**
	 * Get user by id.
	 * <br><br>
	 * This tests the {@link com.malcolmbaatjies.user.controllers.UserController#getUser(String) getUser} method to confirm that a {@link com.malcolmbaatjies.user.entities.exceptions.UserNotFoundException UserNotFoundException} is thrown when the user is not found.
	 */
	@Test
	void getUser404()
	{
		assertThrows(UserNotFoundException.class, () -> userController.getUser("3"));
	}

	/**
	 * Create user.
	 * <br><br>
	 * This tests the {@link com.malcolmbaatjies.user.controllers.UserController#createUser(NewUserRequest) createUser} method.
	 */
	@Test
	void createUser201()
	{
		when(mockedUserRepository.existsUserByEmail("jamesbell@mail.com")).thenReturn(false);
		when(mockedUserRepository.save(User.builder()
			.id(any())
			.firstName("James")
			.lastName("Bell")
			.email("jamesbell@mail.com")
			.build()
		)).thenReturn(User.builder()
			.id(3L)
			.firstName("James")
			.lastName("Bell")
			.email("jamesbell@mail.com")
			.build()
		);

		ResponseEntity<User> userResponse = userController.createUser(
			NewUserRequest.builder()
				.firstName("James")
				.lastName("Bell")
				.email("jamesbell@mail.com")
				.build()
		);

		assertEquals(201, userResponse.getStatusCodeValue());

		HttpHeaders headers = userResponse.getHeaders();

		assertEquals("/users/3", headers.get("Location").get(0));

		User user = userResponse.getBody();

		assertEquals(3, user.getId());
		assertEquals("James", user.getFirstName());
		assertEquals("Bell", user.getLastName());
		assertEquals("jamesbell@mail.com", user.getEmail());
	}

	/**
	 * Create user.
	 * <br><br>
	 * This tests the {@link com.malcolmbaatjies.user.controllers.UserController#createUser(NewUserRequest) createUser} method to confirm that a {@link com.malcolmbaatjies.user.entities.exceptions.UserExistsException UserExistsException} is thrown when the user already exists.
	 */
	@Test
	void createUser400()
	{
		when(mockedUserRepository.existsUserByEmail("jamesbell@mail.com")).thenReturn(true);

		assertThrows(UserExistsException.class, () -> userController.createUser(
			NewUserRequest.builder()
				.firstName("James")
				.lastName("Bell")
				.email("jamesbell@mail.com")
				.build()
		));
	}

	/**
	 * Update user.
	 * <br><br>
	 * This tests the {@link com.malcolmbaatjies.user.controllers.UserController#updateUser(String, UpdateUserRequest) updateUser} method.
	 */
	@Test
	void updateUserFirstName200()
	{
		when(mockedUserRepository.findById(1L)).thenReturn(java.util.Optional.of(
			User.builder()
				.id(1L)
				.firstName("John")
				.lastName("Doe")
				.email("johndoe@mail.com")
				.build()
		));
		when(mockedUserRepository.save(any(User.class))).thenReturn(User.builder()
			.id(1L)
			.firstName("James")
			.lastName("Doe")
			.email("johndoe@mail.com")
			.build()
		);

		ResponseEntity<User> userResponse = userController.updateUser(
			"1",
			UpdateUserRequest.builder().firstName("james").build()
		);

		assertEquals(200, userResponse.getStatusCodeValue());

		User user = userResponse.getBody();

		assertEquals(1, user.getId());
		assertEquals("James", user.getFirstName());
		assertEquals("Doe", user.getLastName());
		assertEquals("johndoe@mail.com", user.getEmail());
	}

	/**
	 * Update user.
	 * <br><br>
	 * This tests the {@link com.malcolmbaatjies.user.controllers.UserController#updateUser(String, UpdateUserRequest) updateUser} method.
	 */
	@Test
	void updateUserLastName200()
	{
		when(mockedUserRepository.findById(1L)).thenReturn(java.util.Optional.of(
			User.builder()
				.id(1L)
				.firstName("John")
				.lastName("Doe")
				.email("johndoe@mail.com")
				.build()
		));
		when(mockedUserRepository.save(any(User.class))).thenReturn(User.builder()
			.id(1L)
			.firstName("John")
			.lastName("Deer")
			.email("johndoe@mail.com")
			.build()
		);

		ResponseEntity<User> userResponse = userController.updateUser(
			"1",
			UpdateUserRequest.builder().firstName("james").build()
		);

		assertEquals(200, userResponse.getStatusCodeValue());

		User user = userResponse.getBody();

		assertEquals(1, user.getId());
		assertEquals("John", user.getFirstName());
		assertEquals("Deer", user.getLastName());
		assertEquals("johndoe@mail.com", user.getEmail());
	}

	/**
	 * Update user.
	 * <br><br>
	 * This tests the {@link com.malcolmbaatjies.user.controllers.UserController#updateUser(String, UpdateUserRequest) updateUser} method.
	 */
	@Test
	void updateUserEmail200()
	{
		when(mockedUserRepository.findById(1L)).thenReturn(java.util.Optional.of(
			User.builder()
				.id(1L)
				.firstName("John")
				.lastName("Doe")
				.email("johndoe@mail.com")
				.build()
		));
		when(mockedUserRepository.save(any(User.class))).thenReturn(User.builder()
			.id(1L)
			.firstName("James")
			.lastName("Doe")
			.email("doe.john@mail.com")
			.build()
		);

		ResponseEntity<User> userResponse = userController.updateUser(
			"1",
			UpdateUserRequest.builder().firstName("james").build()
		);

		assertEquals(200, userResponse.getStatusCodeValue());

		User user = userResponse.getBody();

		assertEquals(1, user.getId());
		assertEquals("James", user.getFirstName());
		assertEquals("Doe", user.getLastName());
		assertEquals("doe.john@mail.com", user.getEmail());
	}

	/**
	 * Update user.
	 * <br><br>
	 * This tests the {@link com.malcolmbaatjies.user.controllers.UserController#updateUser(String, UpdateUserRequest) updateUser} method to confirm that a {@link com.malcolmbaatjies.user.entities.exceptions.UserNotFoundException UserNotFoundException} is thrown when the user is not found.
	 */
	@Test
	void updateUser404()
	{
		assertThrows(UserNotFoundException.class, () -> userController.updateUser(
			"3",
			UpdateUserRequest.builder().firstName("james").build()
		));
	}

	/**
	 * Update user.
	 * <br><br>
	 * This tests the {@link com.malcolmbaatjies.user.controllers.UserController#updateUser(String, UpdateUserRequest) updateUser} method to confirm that a {@link com.malcolmbaatjies.user.entities.exceptions.UserExistsException UserExistsException} is thrown when the user already exists.
	 */
	@Test
	void updateUserEmail400()
	{
		when(mockedUserRepository.findById(1L)).thenReturn(java.util.Optional.of(
			User.builder()
				.id(1L)
				.firstName("James")
				.lastName("Doe")
				.email("jamesdoe@mail.com")
				.build()
		));
		when(mockedUserRepository.existsUserByEmail("johndoe@mail.com")).thenReturn(true);

		assertThrows(UserExistsException.class, () -> userController.updateUser(
			"1",
			UpdateUserRequest.builder().email("johndoe@mail.com").build()
		));
	}

	/**
	 * Delete user.
	 * <br><br>
	 * This tests the {@link com.malcolmbaatjies.user.controllers.UserController#deleteUser(String) deleteUser} method.
	 */
	@Test
	void deleteUser200()
	{
		assertDoesNotThrow(() -> userController.deleteUser("1"));
	}
}