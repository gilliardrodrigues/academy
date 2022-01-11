package br.com.academy.model.enums;

public enum Curso {
	
	SISTEMAS_DE_INFORMACAO("Sistemas de Informação"),
	CIENCIA_DA_COMPUTACAO("Ciência da Computação"),
	MATEMATICA_COMPUTACIONAL("Matemática Computacional"),
	ENGENHARIA_DA_COMPUTACAO("Engenharia da Computação"),
	ENGENHARIA_DE_SOFTWARE("Engenharia de Software");
	
	private final String descricao;
	
	Curso(String descricao)
	{
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}
}
