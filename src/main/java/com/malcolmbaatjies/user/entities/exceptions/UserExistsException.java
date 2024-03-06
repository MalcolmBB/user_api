package com.malcolmbaatjies.user.entities.exceptions;

/**
 * UserExistsException
 * <br><br>
 * This class is used to represent the runtime exception when a user already exists.
 */
public class UserExistsException extends RuntimeException
{
	public UserExistsException(String email)
	{
		super("User with email [" + email + "] already exists");
	}
}