package br.com.academy.exceptions;

public class FileImportException extends RuntimeException
{
	
	public FileImportException(String message)
	{
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
}
