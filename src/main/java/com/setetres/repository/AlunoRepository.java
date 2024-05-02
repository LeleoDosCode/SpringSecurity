package com.setetres.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.setetres.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	List<Aluno> findByRenda(Double renda);
	List<Aluno> findByRa(String ra);
	List<Aluno> findByCidadeAndRenda(String cidade, Double renda);
	
	@Query("SELECT a FROM Aluno a WHERE a.nome = :nome")
	List<Aluno> findByNome(@Param("Nome") String Nome);
	
	@Query("SELECT a FROM Aluno a JOIN a.turma t WHERE t.id = :turmaId")
	List<Aluno> findByTurmaId(@Param("turmaId") Long turmaId);
}