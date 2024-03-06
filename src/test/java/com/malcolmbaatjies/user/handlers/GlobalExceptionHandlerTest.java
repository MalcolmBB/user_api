package com.malcolmbaatjies.user.handlers;

import com.malcolmbaatjies.user.entities.exceptions.UserExistsException;
import com.malcolmbaatjies.user.entities.exceptions.UserNotFoundException;
import com.malcolmbaatjies.user.entities.responses.ApiErrorResponse;
import com.malcolmbaatjies.user.entities.responses.validation.ValidationErrorCodes;
import com.malcolmbaatjies.user.entities.responses.validation.ValidationErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.validation.*;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GlobalExceptionHandlerTest
 * <br><br>
 * This class is used to test the GlobalExceptionHandler class.
 */
class GlobalExceptionHandlerTest
{
	/**
	 * The {@link com.malcolmbaatjies.user.handlers.GlobalExceptionHandler}.
	 */
	private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

	/**
	 * Test that the {@link com.malcolmbaatjies.user.handlers.GlobalExceptionHandler#onBindException} method returns the correct response.
	 */
	@Test
	void onBindException()
	{
		BindingResult bindingResult = new BindingResult()
		{
			@Override
			public Object getTarget()
			{
				return null;
			}

			@Override
			public Map<String, Object> getModel()
			{
				return null;
			}

			@Override
			public Object getRawFieldValue(String field)
			{
				return null;
			}

			@Override
			public PropertyEditor findEditor(String field, Class<?> valueType)
			{
				return null;
			}

			@Override
			public PropertyEditorRegistry getPropertyEditorRegistry()
			{
				return null;
			}

			@Override
			public String[] resolveMessageCodes(String errorCode)
			{
				return new String[0];
			}

			@Override
			public String[] resolveMessageCodes(String errorCode, String field)
			{
				return new String[0];
			}

			@Override
			public void addError(ObjectError error)
			{

			}

			@Override
			public String getObjectName()
			{
				return null;
			}

			@Override
			public void setNestedPath(String nestedPath)
			{

			}

			@Override
			public String getNestedPath()
			{
				return null;
			}

			@Override
			public void pushNestedPath(String subPath)
			{

			}

			@Override
			public void popNestedPath() throws IllegalStateException
			{

			}

			@Override
			public void reject(String errorCode)
			{

			}

			@Override
			public void reject(String errorCode, String defaultMessage)
			{

			}

			@Override
			public void reject(String errorCode, Object[] errorArgs, String defaultMessage)
			{

			}

			@Override
			public void rejectValue(String field, String errorCode)
			{

			}

			@Override
			public void rejectValue(String field, String errorCode, String defaultMessage)
			{

			}

			@Override
			public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage)
			{

			}

			@Override
			public void addAllErrors(Errors errors)
			{

			}

			@Override
			public boolean hasErrors()
			{
				return false;
			}

			@Override
			public int getErrorCount()
			{
				return 0;
			}

			@Override
			public List<ObjectError> getAllErrors()
			{
				return null;
			}

			@Override
			public boolean hasGlobalErrors()
			{
				return false;
			}

			@Override
			public int getGlobalErrorCount()
			{
				return 0;
			}

			@Override
			public List<ObjectError> getGlobalErrors()
			{
				return null;
			}

			@Override
			public ObjectError getGlobalError()
			{
				return null;
			}

			@Override
			public boolean hasFieldErrors()
			{
				return false;
			}

			@Override
			public int getFieldErrorCount()
			{
				return 0;
			}

			@Override
			public List<FieldError> getFieldErrors()
			{
				return List.of(new FieldError("NOOP", "NOOP", "4001"),new FieldError("NOOP", "NOOP", "4002"));
			}

			@Override
			public FieldError getFieldError()
			{
				return null;
			}

			@Override
			public boolean hasFieldErrors(String field)
			{
				return false;
			}

			@Override
			public int getFieldErrorCount(String field)
			{
				return 0;
			}

			@Override
			public List<FieldError> getFieldErrors(String field)
			{
				return null;
			}

			@Override
			public FieldError getFieldError(String field)
			{
				return null;
			}

			@Override
			public Object getFieldValue(String field)
			{
				return null;
			}

			@Override
			public Class<?> getFieldType(String field)
			{
				return null;
			}
		};
		BindException bindException = new BindException(bindingResult);

		ValidationErrorResponse response = globalExceptionHandler.onBindException(bindException);

		assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getStatus());
		assertEquals("4001", response.getErrors().get(0).getCode());
		assertEquals(ValidationErrorCodes.fromCode("4001").getMessage(), response.getErrors().get(0).getMessage());
		assertEquals("4002", response.getErrors().get(1).getCode());
		assertEquals(ValidationErrorCodes.fromCode("4002").getMessage(), response.getErrors().get(1).getMessage());
	}

	/**
	 * Test that the {@link com.malcolmbaatjies.user.handlers.GlobalExceptionHandler#onUserNotFoundException} method returns the correct response.
	 */
	@Test
	void onUserNotFoundException()
	{
		ApiErrorResponse response = globalExceptionHandler.onUserNotFoundException(new UserNotFoundException("1"));

		assertEquals(HttpStatus.NOT_FOUND.toString(), response.getStatus());
		assertEquals("User with id [1] not found", response.getMessage());
	}

	/**
	 * Test that the {@link com.malcolmbaatjies.user.handlers.GlobalExceptionHandler#onUserExistsException} method returns the correct response.
	 */
	@Test
	void onUserExistsException()
	{
		ApiErrorResponse response = globalExceptionHandler.onUserExistsException(new UserExistsException("example@mail.com"));

		assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getStatus());
		assertEquals("User with email [example@mail.com] already exists", response.getMessage());
	}

	/**
	 * Test that the {@link com.malcolmbaatjies.user.handlers.GlobalExceptionHandler#onException} method returns the correct response.
	 */
	@Test
	void onException()
	{
		ApiErrorResponse response = globalExceptionHandler.onException(new Exception());

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.toString(), response.getStatus());
		assertEquals("An internal exception has occurred. Please contact Malcolm Baatjies for support.", response.getMessage());
	}
}