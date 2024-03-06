package com.malcolmbaatjies.user.entities.requests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UpdateUserRequestTest
 * <br><br>
 * This class is used to test the UpdateUserRequest class.
 */
class UpdateUserRequestTest
{
	/**
	 * The validator.
	 * <br><br>
	 * This is used to validate the UpdateUserRequest.
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
	 * Test that the UpdateUserRequest is valid when the first name is missing.
	 */
	@Test
	void missingFirstName()
	{
		UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
			.lastName("Doe")
			.email("johndoe@mail.com")
			.build();

		var violations = validator.validate(updateUserRequest);

		assertEquals(0, violations.size());
	}

	/**
	 * Test that the UpdateUserRequest is valid when the last name is missing.
	 */
	@Test
	void missingLastName()
	{
		UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
			.firstName("John")
			.email("johndoe@mail.com")
			.build();

		var violations = validator.validate(updateUserRequest);

		assertEquals(0, violations.size());
	}

	/**
	 * Test that the UpdateUserRequest is valid when the email is missing.
	 */
	@Test
	void missingEmail()
	{
		UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
			.firstName("John")
			.lastName("Doe")
			.build();

		var violations = validator.validate(updateUserRequest);

		assertEquals(0, violations.size());
	}

	/**
	 * Test that the UpdateUserRequest is invalid when the email is invalid.
	 */
	@Test
	void invalidEmail()
	{
		UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
			.firstName("John")
			.lastName("Doe")
			.email("johndoe")
			.build();

		var violations = validator.validate(updateUserRequest);

		assertEquals(1, violations.size());
		assertEquals("4004", violations.iterator().next().getMessage());
	}

	/**
	 * Test that the UpdateUserRequest is valid when all fields are present and valid.
	 */
	@Test
	void validUpdateUserRequest()
	{
		UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
			.firstName("John")
			.lastName("Doe")
			.email("johndoe@mail.com")
			.build();

		var violations = validator.validate(updateUserRequest);

		assertEquals(0, violations.size());
	}

	/**
	 * Test that the UpdateUserRequest is valid when all fields are missing.
	 */
	@Test
	void missingFirstNameLastNameAndEmail()
	{
		UpdateUserRequest updateUserRequest = UpdateUserRequest.builder().build();

		var violations = validator.validate(updateUserRequest);

		assertEquals(0, violations.size());
	}

	/**
	 * Test that the UpdateUserRequest is valid when the first name and last name are missing.
	 */
	@Test
	void missingFirstNameAndLastName()
	{
		UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
			.email("johndoe@mail.com")
			.build();

		var violations = validator.validate(updateUserRequest);

		assertEquals(0, violations.size());
	}

	/**
	 * Test that the UpdateUserRequest is invalid when the email is invalid.
	 */
	@Test
	void missingFirstNameLastNameAndInvalidEmail()
	{
		UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
			.email("johndoe")
			.build();

		var violations = validator.validate(updateUserRequest);

		assertEquals(1, violations.size());

		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("4004")));
	}
}