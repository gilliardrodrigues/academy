package br.com.academy.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.academy.enums.Curso;
import br.com.academy.enums.Status;
import br.com.academy.enums.Turno;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class Aluno {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Integer id;
	
	@Size(min = 5, max = 50, message = "O nome deve conter no mínimo 5 caracteres!")
	@NotBlank(message = "Campo obrigatório não preenchido!")
	private String nome;
	
	@NotBlank(message = "Clique no botão abaixo para gerar a matrícula!")
	private String matricula;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Campo obrigatório não selecionado!")
	private Curso curso;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Campo obrigatório não selecionado!")
	private Turno turno;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Campo obrigatório não selecionado!")
	private Status status;
}
