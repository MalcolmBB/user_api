package com.malcolmbaatjies.user.entities.responses.validation;

import com.malcolmbaatjies.user.entities.responses.ABaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * ValidationErrorResponse
 * <br><br>
 * This class is used to represent the validation error response.
 * <br><br>
 * This class is annotated with Lombok's {@link lombok.Builder}, {@link lombok.Getter} and {@link lombok.extern.log4j.Log4j2} to generate the builder, getter methods and logger during compile time.
 * <br><br>
 * This class extends the {@link com.malcolmbaatjies.user.entities.responses.ABaseResponse} class.
 */
@SuperBuilder
@Getter
@Log4j2
public class ValidationErrorResponse extends ABaseResponse
{
	/**
	 * The list of validation errors.
	 * <br><br>
	 * This field is annotated with Lombok's {@link lombok.Builder.Default} to generate the default value during compile time.
	 */
	@Builder.Default()
	private List<ValidationError> errors = new ArrayList<>();

	/**
	 * Add a validation error to the list of validation errors.
	 *
	 * @param errorCode The validation error code.
	 */
	public void addError(String errorCode)
	{
		errors.add(ValidationErrorCodes.getErrorFromCode(errorCode));
	}

	/**
	 * Sort the list of validation errors.
	 */
	public void sort()
	{
		if (errors != null)
		{
			errors.sort(Comparator.comparing(ValidationError::getCode));
		}
	}
}
