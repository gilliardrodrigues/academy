package br.com.academy.model.enums;

public enum Turno
{
	MATUTINO("Matutino"),
	VESPERTINO("Vespertino"),
	NOTURNO("Noturno"),
	INTEGRAL("Integral");
	
	private final String descricao;
	
	Turno(String descricao)
	{
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}
}
