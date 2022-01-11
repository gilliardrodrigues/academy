package br.com.academy.model.enums;

public enum Status
{
	ATIVO("Ativo"),
	INATIVO("Inativo"),
	TRANCADO("Trancado"),
	CANCELADO("Cancelado");
	
	private final String descricao;
	
	Status(String descricao)
	{
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}
}
