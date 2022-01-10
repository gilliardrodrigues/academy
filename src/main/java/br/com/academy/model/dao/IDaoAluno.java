package br.com.academy.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.enums.Status;
import br.com.academy.model.entity.Aluno;

public interface IDaoAluno extends JpaRepository<Aluno, Integer>
{
	@Query("SELECT a FROM Aluno a WHERE a.status = :status")
	public List<Aluno> buscaAlunosFiltradoPeloStatus(Status status);
	
	public List<Aluno> findByNomeContainingIgnoreCase(String nome);
}
