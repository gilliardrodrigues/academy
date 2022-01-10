package br.com.academy.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.exceptions.CriptoExistsException;
import br.com.academy.exceptions.EmailExistsException;
import br.com.academy.exceptions.UsuarioServiceException;
import br.com.academy.model.dao.IDaoUsuario;
import br.com.academy.model.entity.Usuario;
import br.com.academy.util.Util;

@Service
public class UsuarioService
{
	@Autowired
	IDaoUsuario repositorio;
	
	public void salvar(Usuario usuario) throws Exception
	{
		try
		{
			if(repositorio.findByEmail(usuario.getEmail()) != null)
			{
				throw new EmailExistsException("Já existe um usuário cadastro para o e-mail: " + usuario.getEmail());
			}
			usuario.setSenha(Util.criptografarSenha(usuario.getSenha()));
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new CriptoExistsException("Erro na criptografia da senha!");
		}
		repositorio.save(usuario);
	}
	
	public Usuario autenticar(String nomeUsuario, String senha) throws UsuarioServiceException
	{
		Usuario usuarioEncontrado = repositorio.findByNomeUsuarioAndSenha(nomeUsuario, senha);
		return usuarioEncontrado;
	}
	
}
