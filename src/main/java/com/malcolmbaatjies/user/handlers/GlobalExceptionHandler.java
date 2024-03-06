package com.malcolmbaatjies.user.handlers;


import com.malcolmbaatjies.user.entities.exceptions.UserExistsException;
import com.malcolmbaatjies.user.entities.exceptions.UserNotFoundException;
import com.malcolmbaatjies.user.entities.responses.ApiErrorResponse;
import com.malcolmbaatjies.user.entities.responses.validation.ValidationErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler
 * <br><br>
 * This class is used to handle exceptions globally.
 * <br><br>
 * This class is annotated with Lombok's {@link lombok.extern.log4j.Log4j2} to enable logging.
 * <br><br>
 * This class is annotated with Spring's {@link org.springframework.web.bind.annotation.RestControllerAdvice} to indicate that it is used to handle exceptions globally.
 */
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler
{
	/**
	 * {@link org.springframework.validation.BindException} Handler.
	 * <br><br>
	 * This method handles the BindExceptions that are thrown by the application. These exceptions are thrown by the validation framework on incoming requests and the response is constructed using the {@link com.malcolmbaatjies.user.entities.responses.validation.ValidationErrorResponse#addError} method which handles mapping from a String to a {@link com.malcolmbaatjies.user.entities.responses.validation.ValidationErrorCodes ValidationErrorCodes} enum.
	 * <br><br>
	 * @param e The exception that was thrown.
	 * @return {@link com.malcolmbaatjies.user.entities.responses.validation.ValidationErrorResponse}
	 */
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ValidationErrorResponse onBindException(
		BindException e)
	{
		ValidationErrorResponse error = ValidationErrorResponse.builder().status(HttpStatus.BAD_REQUEST.toString()).message("Validation Failed.").build();

		for (FieldError fieldError : e.getBindingResult().getFieldErrors())
		{
			log.error("Field Error: {}", fieldError.getDefaultMessage());
			error.addError(fieldError.getDefaultMessage());
		}
		error.sort();
		return error;
	}

	/**
	 * {@link com.malcolmbaatjies.user.entities.exceptions.UserNotFoundException} handler
	 * <br><br>
	 * This method handles the User Not Found Exceptions that are thrown by the application and returns the appropriate payload to inform the user.
	 * <br><br>
	 * @param e The exception that was thrown
	 * @return {@link com.malcolmbaatjies.user.entities.responses.ApiErrorResponse}
	 */
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	ApiErrorResponse onUserNotFoundException(UserNotFoundException e)
	{
		return ApiErrorResponse.builder()
			.status(HttpStatus.NOT_FOUND.toString())
			.message(e.getMessage())
			.build();
	}

	/**
	 * {@link com.malcolmbaatjies.user.entities.exceptions.UserExistsException} handler
	 * <br><br>
	 * This method handles the User Exists Exceptions which are thrown when trying to create a user with an email address that already exists. An appropriate message is returned.
	 * <br><br>
	 * @param e The exception that was thrown
	 * @return {@link com.malcolmbaatjies.user.entities.responses.ApiErrorResponse}
	 */
	@ExceptionHandler(UserExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ApiErrorResponse onUserExistsException(UserExistsException e)
	{
		return ApiErrorResponse.builder()
			.status(HttpStatus.BAD_REQUEST.toString())
			.message(e.getMessage())
			.build();
	}

	/**
	 * {@link java.lang.Exception} handler
	 * <br><br>
	 * This is a catch-all for any exceptions that are thrown by the application and have not been handled explicitly.
	 * <br><br>
	 * @param e The exception that was thrown
	 * @return {@link com.malcolmbaatjies.user.entities.responses.ApiErrorResponse}
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	ApiErrorResponse onException(Exception e)
	{
		log.error("Internal Exception:", e);
		return ApiErrorResponse.builder()
			.status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
			.message("An internal exception has occurred. Please contact Malcolm Baatjies for support.")
			.build();
	}
}
