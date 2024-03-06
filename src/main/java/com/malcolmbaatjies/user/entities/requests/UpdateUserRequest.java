package com.malcolmbaatjies.user.entities.requests;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * UpdateUserRequest
 * <br><br>
 * This class is used to represent the request to update a user.
 * <br><br>
 * This class is annotated with Lombok's {@link lombok.Builder} and {@link lombok.Getter} to generate the builder and getter methods during compile time.
 * <br><br>
 * This class is annotated with {@link javax.validation.constraints.Pattern} to validate the email field.
 */
@Builder
@Getter
public class UpdateUserRequest implements IBaseRequest
{
	private String firstName;

	private String lastName;

	/**
	 * {@link javax.validation.constraints.Pattern} Validation Reference: {@link com.malcolmbaatjies.user.entities.responses.validation.ValidationErrorCodes#USER_EMAIL_INVALID User Email Invalid}
	 */
	@Pattern(message = "4004", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	private String email;
}
