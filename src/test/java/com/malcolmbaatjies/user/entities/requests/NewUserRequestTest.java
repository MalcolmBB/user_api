package com.malcolmbaatjies.user.entities.requests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * NewUserRequestTest
 * <br><br>
 * This class is used to test the NewUserRequest class.
 */
class NewUserRequestTest
{
	/**
	 * The validator.
	 * <br><br>
	 * This is used to validate the NewUserRequest.
	 */
	private Validator validator;

	/**
	 * Setup the validator.
	 */
	@BeforeEach
	public void setup()
	{
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	/**
	 * Test that the NewUserRequest is invalid when the first name is missing.
	 */
	@Test
	void missingFirstName()
	{
		NewUserRequest newUserRequest = NewUserRequest.builder()
			.lastName("Doe")
			.email("johndoe@mail.com")
			.build();

		var violations = validator.validate(newUserRequest);

		assertEquals(1, violations.size());
		assertEquals("4001", violations.iterator().next().getMessage());
	}

	/**
	 * Test that the NewUserRequest is invalid when the last name is missing.
	 */
	@Test
	void missingLastName()
	{
		NewUserRequest newUserRequest = NewUserRequest.builder()
			.firstName("John")
			.email("johndoe@mail.com")
			.build();

		var violations = validator.validate(newUserRequest);

		assertEquals(1, violations.size());
		assertEquals("4002", violations.iterator().next().getMessage());
	}

	/**
	 * Test that the NewUserRequest is invalid when the email is missing.
	 */
	@Test
	void missingEmail()
	{
		NewUserRequest newUserRequest = NewUserRequest.builder()
			.firstName("John")
			.lastName("Doe")
			.build();

		var violations = validator.validate(newUserRequest);

		assertEquals(1, violations.size());
		assertEquals("4003", violations.iterator().next().getMessage());
	}

	/**
	 * Test that the NewUserRequest is invalid when the email is invalid.
	 */
	@Test
	void invalidEmail()
	{
		NewUserRequest newUserRequest = NewUserRequest.builder()
			.firstName("John")
			.lastName("Doe")
			.email("johndoe")
			.build();

		var violations = validator.validate(newUserRequest);

		assertEquals(1, violations.size());
		assertEquals("4004", violations.iterator().next().getMessage());
	}

	/**
	 * Test that the NewUserRequest is valid when all the fields are present and valid.
	 */
	@Test
	void validNewUserRequest()
	{
		NewUserRequest newUserRequest = NewUserRequest.builder()
			.firstName("John")
			.lastName("Doe")
			.email("johndoe@mail.com")
			.build();

		var violations = validator.validate(newUserRequest);

		assertEquals(0, violations.size());
	}

	/**
	 * Test that the NewUserRequest is invalid when the first name, last name and email are missing.
	 */
	@Test
	void missingFirstNameLastNameAndEmail()
	{
		NewUserRequest newUserRequest = NewUserRequest.builder().build();

		var violations = validator.validate(newUserRequest);

		assertEquals(3, violations.size());

		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("4001")));
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("4002")));
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("4003")));
	}

	/**
	 * Test that the NewUserRequest is invalid when the first name and last name are missing.
	 */
	@Test
	void missingFirstNameAndLastName()
	{
		NewUserRequest newUserRequest = NewUserRequest.builder()
			.email("johndoe@mail.com")
			.build();

		var violations = validator.validate(newUserRequest);

		assertEquals(2, violations.size());

		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("4001")));
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("4002")));
	}

	/**
	 * Test that the NewUserRequest is invalid when the first and last names are missing and the email is invalid.
	 */
	@Test
	void missingFirstNameLastNameAndInvalidEmail()
	{
		NewUserRequest newUserRequest = NewUserRequest.builder()
			.email("johndoe")
			.build();

		var violations = validator.validate(newUserRequest);

		assertEquals(3, violations.size());

		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("4001")));
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("4002")));
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("4004")));
	}
}