package br.com.academy.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academy.model.entity.Aluno;

public interface IDaoAluno extends JpaRepository<Aluno, Integer>
{

}
