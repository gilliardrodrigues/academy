package br.com.academy.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import br.com.academy.exceptions.EmailExistsException;
import br.com.academy.exceptions.UsernameExistsException;
import br.com.academy.model.dao.IDaoUsuario;
import br.com.academy.model.entity.Usuario;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService
{
	
	private final IDaoUsuario repositorio;
	
	public void salvar(Usuario usuario) throws Exception
	{
		if(repositorio.findByUsername(usuario.getUsername()) != null)
		{
			throw new UsernameExistsException("Esse nome de usuário já está sendo utilizado por outra pessoa, escolha outro!");
		}
		if(repositorio.findByEmail(usuario.getEmail()) != null)
		{
			throw new EmailExistsException("Já existe um usuário cadastro para o e-mail: " + usuario.getEmail());
		}
		usuario.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(usuario.getPassword()));
		usuario.setRoles("ROLE_ADMIN,ROLE_USER");
		repositorio.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return Optional.ofNullable(repositorio.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}
	
}
