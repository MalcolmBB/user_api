package com.malcolmbaatjies.user.entities.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * ABaseResponse
 * <br><br>
 * This abstract class is used to represent the base response.
 * <br><br>
 * This class is annotated with Lombok's {@link lombok.AllArgsConstructor}, {@link lombok.Getter} and {@link lombok.experimental.SuperBuilder} to generate the all-args constructor, getter methods and builder methods during compile time.
 */
@AllArgsConstructor
@SuperBuilder
@Getter
public abstract class ABaseResponse
{
	/**
	 * The status of the response.
	 */
	protected String status;

	/**
	 * The message of the response.
	 */
	protected String message;
}
