package br.com.academy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.model.dao.IDaoAluno;
import br.com.academy.model.entity.Aluno;
import br.com.academy.model.enums.Status;

@Service
public class AlunoService
{
	@Autowired
	IDaoAluno repositorio;
	
	public void salvar(Aluno aluno)
	{
		repositorio.save(aluno);
	}
	
	public void excluir(Integer id)
	{
		repositorio.deleteById(id);
	}
	
	public Aluno buscarAlunoPeloId(Integer id)
	{
		return repositorio.getById(id);
	}
	
	public List<Aluno> buscarAlunosPeloNome(String nome)
	{
		return repositorio.findByNomeContainingIgnoreCase(nome);
	}
	
	public List<Aluno> buscarAlunosFiltradoPeloStatus(Status status)
	{
		return repositorio.buscaAlunosFiltradoPeloStatus(status);
	}
	
	public List<Aluno> buscarTodos()
	{
		return repositorio.findAll();
	}
}
