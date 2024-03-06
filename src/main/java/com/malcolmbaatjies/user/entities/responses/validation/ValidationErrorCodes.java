package com.malcolmbaatjies.user.entities.responses.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ValidationErrorCodes
 * <br><br>
 * This enum is used to represent the validation error codes.
 * <br><br>
 * This enum is annotated with Lombok's {@link lombok.AllArgsConstructor} and {@link lombok.Getter} to generate the all-args constructor and getter methods during compile time.
 */
@AllArgsConstructor
@Getter
public enum ValidationErrorCodes
{
	USER_FIRST_NAME_REQUIRED("4001", "First name is required"),
	USER_LAST_NAME_REQUIRED("4002", "Last name is required"),
	USER_EMAIL_REQUIRED("4003", "Email is required"),
	USER_EMAIL_INVALID("4004", "Email is invalid"),
	UNEXPECTED_ERROR("Unexpected", "An unexpected error occurred")
	;

	/**
	 * The validation error code.
	 */
	private final String code;

	/**
	 * The validation error message.
	 */
	private final String message;

	/**
	 * Get the validation error code from the given code.
	 *
	 * @param code The validation error code.
	 * @return The validation error code.
	 */
	public static ValidationErrorCodes fromCode(String code)
	{
		for (ValidationErrorCodes value : ValidationErrorCodes.values())
		{
			if (value.getCode().equals(code))
			{
				return value;
			}
		}
		return UNEXPECTED_ERROR;
	}

	/**
	 * Get the validation error from the given code.
	 *
	 * @param code The validation error code.
	 * @return The validation error.
	 */
	public static ValidationError getErrorFromCode(String code)
	{
		ValidationErrorCodes errorCode = fromCode(code);
		return ValidationError.builder().code(errorCode.getCode()).message(errorCode.getMessage()).build();
	}
}
