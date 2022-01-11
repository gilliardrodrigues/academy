package br.com.academy.exceptions;

public class CriptoExistsException extends RuntimeException
{

	public CriptoExistsException(String message)
	{
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
}
