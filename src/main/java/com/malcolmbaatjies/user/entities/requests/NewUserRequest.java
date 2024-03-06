package com.malcolmbaatjies.user.entities.requests;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * NewUserRequest
 * <br><br>
 * This class is used to represent the request to create a new user.
 * <br><br>
 * This class is annotated with Lombok's {@link lombok.Builder} and {@link lombok.Getter} to generate the builder and getter methods during compile time.
 * <br><br>
 * This class is annotated with {@link javax.validation.constraints.NotBlank} and {@link javax.validation.constraints.Pattern} to validate the first name, last name and email fields.
 */
@Builder
@Getter
public class NewUserRequest implements IBaseRequest
{
	/**
	 * {@link javax.validation.constraints.NotBlank} Validation Reference: {@link com.malcolmbaatjies.user.entities.responses.validation.ValidationErrorCodes#USER_FIRST_NAME_REQUIRED User First Name Required}
	 */
	@NotBlank(message = "4001")
	private String firstName;

	/**
	 * {@link javax.validation.constraints.NotBlank} Validation Reference: {@link com.malcolmbaatjies.user.entities.responses.validation.ValidationErrorCodes#USER_LAST_NAME_REQUIRED User Last Name Required}
	 */
	@NotBlank(message = "4002")
	private String lastName;

	/**
	 * {@link javax.validation.constraints.NotBlank} Validation Reference: {@link com.malcolmbaatjies.user.entities.responses.validation.ValidationErrorCodes#USER_EMAIL_REQUIRED User Email Required}
	 * <br>
	 * {@link javax.validation.constraints.Pattern} Validation Reference: {@link com.malcolmbaatjies.user.entities.responses.validation.ValidationErrorCodes#USER_EMAIL_INVALID User Email Invalid}
	 */
	@NotBlank(message = "4003")
	@Pattern(message = "4004", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	private String email;
}
