package br.com.academy.util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.academy.exceptions.EmptyFileException;
import br.com.academy.exceptions.FileImportException;
import br.com.academy.exceptions.InvalidFileException;

public class Util
{
	public static String criptografarSenha(String senha) throws NoSuchAlgorithmException
	{
		MessageDigest msgDig = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, msgDig.digest(senha.getBytes()));
		return hash.toString(16);
	}
	
	public static boolean isArquivoImagem(String contentType)
	{
		if(contentType.equals("image/png") || contentType.equals("image/jpeg"))
		{
			return true;
		}
		return false;
	}
	
	public static String converteImagemParaString(MultipartFile arquivo)
	{
		String imagem = null;
		if(arquivo.isEmpty())
		{
			throw new EmptyFileException("Campo obrigatório não preenchido!");
		}
		if(!isArquivoImagem(arquivo.getContentType()))
		{
			throw new InvalidFileException("Arquivo inválido! Importe uma imagem.");
		}
		try
		{
			imagem = Base64.getEncoder().encodeToString(arquivo.getBytes());
		}
		catch (IOException e)
		{
			throw new FileImportException("Erro na importação do arquivo " + StringUtils.cleanPath(arquivo.getOriginalFilename()));
		}
		return imagem;
	}
}
