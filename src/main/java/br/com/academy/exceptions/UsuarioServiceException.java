package br.com.academy.exceptions;

public class UsuarioServiceException extends Exception
{

	public UsuarioServiceException(String message)
	{
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
}
