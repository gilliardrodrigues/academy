package br.com.academy.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academy.model.entity.Usuario;

public interface IDaoUsuario extends JpaRepository<Usuario, Long>
{
	public Usuario findByEmail(String email);
	
	public Usuario findByUsername(String username);
}
