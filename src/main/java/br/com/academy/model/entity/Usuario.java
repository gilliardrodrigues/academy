package br.com.academy.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Usuario
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Campo obrigatório não preenchido!")
	private String nome;
	
	@Size(min = 5, max = 20, message = "O usuário deve conter de 5 a 20 caracteres!")
	@NotBlank(message = "Campo obrigatório não preenchido!")
	private String nomeUsuario;
	
	@NotBlank(message = "Campo obrigatório não preenchido!")
	private String senha;
	
	@Email(message = "Formato de e-mail inválido!")
	@NotBlank(message = "Campo obrigatório não preenchido!")
	private String email;
}
