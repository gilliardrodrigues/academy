package br.com.academy.exceptions;

public class UsuarioServiceException extends RuntimeException
{

	public UsuarioServiceException(String message)
	{
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
}
