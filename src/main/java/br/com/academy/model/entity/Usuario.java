package br.com.academy.model.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario implements UserDetails
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Campo obrigatório não preenchido!")
	private String nome;
	
	@Size(min = 5, max = 20, message = "O usuário deve conter de 5 a 20 caracteres!")
	@NotBlank(message = "Campo obrigatório não preenchido!")
	private String username;
	
	@NotBlank(message = "Campo obrigatório não preenchido!")
	private String password;
	
	@Email(message = "Formato de e-mail inválido!")
	@NotBlank(message = "Campo obrigatório não preenchido!")
	private String email;
	
	private String roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return Arrays.stream(roles.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}
}
