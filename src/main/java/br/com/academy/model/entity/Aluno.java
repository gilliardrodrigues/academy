package br.com.academy.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.academy.model.enums.Curso;
import br.com.academy.model.enums.Sexo;
import br.com.academy.model.enums.Status;
import br.com.academy.model.enums.Turno;
import lombok.Data;

@Entity
@Data
public class Aluno {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String foto;
	
	@Size(min = 5, max = 50, message = "O nome deve conter no mínimo 5 caracteres!")
	@NotBlank(message = "Campo obrigatório não preenchido!")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Campo obrigatório não selecionado!")
	private Sexo sexo;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@NotNull(message = "Campo obrigatório não preenchido!")
	private Date dataNascimento;
	
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
