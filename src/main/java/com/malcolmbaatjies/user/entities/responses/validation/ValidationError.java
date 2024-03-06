package com.malcolmbaatjies.user.entities.responses.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ValidationError
 * <br><br>
 * This class is used to represent a validation error.
 * <br><br>
 * This class is annotated with Lombok's {@link lombok.Builder}, {@link lombok.AllArgsConstructor}, {@link lombok.NoArgsConstructor} and {@link lombok.Getter} to generate the builder, all-args constructor, no-args constructor and getter methods during compile time.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ValidationError
{
	/**
	 * The validation error code.
	 */
	private String code;

	/**
	 * The validation error message.
	 */
	private String message;
}
