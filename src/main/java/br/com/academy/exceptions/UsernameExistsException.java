package br.com.academy.exceptions;

public class UsernameExistsException extends RuntimeException
{
	
	public UsernameExistsException(String message)
	{
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
