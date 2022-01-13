package br.com.academy.exceptions;

public class InvalidFileException extends RuntimeException
{
	
	public InvalidFileException(String message)
	{
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
}
