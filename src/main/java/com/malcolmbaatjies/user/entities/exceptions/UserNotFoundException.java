package com.malcolmbaatjies.user.entities.exceptions;

/**
 * UserNotFoundException
 * <br><br>
 * This class is used to represent the runtime exception when a user is not found.
 */
public class UserNotFoundException extends RuntimeException
{
	public UserNotFoundException(String id)
	{
		super("User with id [" + id + "] not found");
	}
}
