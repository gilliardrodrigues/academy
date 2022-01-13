package br.com.academy.exceptions;

public class EmptyFileException extends RuntimeException
{
	
	public EmptyFileException(String message)
	{
		super(message);
	}

	private static final long serialVersionUID = 1L;
}
