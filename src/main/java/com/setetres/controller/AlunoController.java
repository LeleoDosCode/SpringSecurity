package com.setetres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setetres.entities.Aluno;
import com.setetres.service.AlunoService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/Aluno") 
public class AlunoController {
	private final AlunoService AlunoService; 

	@Autowired 
	public AlunoController(AlunoService AlunoService) { 
		this.AlunoService = AlunoService; 
	}
	
	//Query Method
	@GetMapping("/nome/{nome}")
	public List<Aluno> findAlunosPorNome(@PathVariable String nome){
		return AlunoService.findByNome(nome);
	}
	//Query Method
	@GetMapping("/renda/{renda}")
	public ResponseEntity<List<Aluno>> buscarAlunosPorRenda(@PathVariable  Double renda){
		List<Aluno> alunos = AlunoService.buscarAlunosPorRenda(renda);
		return ResponseEntity.ok(alunos);
	}
	//Query Method
	@GetMapping("/ra/{ra}")
	public ResponseEntity<List<Aluno>> buscarAlunosPorRa(@PathVariable  String ra){
		List<Aluno> alunos = AlunoService.buscarAlunosPorRa(ra);
		return ResponseEntity.ok(alunos);
	}
	//Query Method
	@GetMapping("/cidade/{cidade}/renda/{renda}")
	public ResponseEntity<List<Aluno>> buscarCidadeERenda(@PathVariable  String cidade, @PathVariable  Double renda){
		List<Aluno> alunos = AlunoService.buscarCidadeERenda(cidade, renda);
		return ResponseEntity.ok(alunos);
	}
	//Query Method
	@GetMapping("/turma/{turmaId}")
	public List<Aluno> findAlunosPorTurma(@PathVariable Long turmaId){
		return AlunoService.findByTurmaId(turmaId);
	}

	@GetMapping("/{id}") 
	public ResponseEntity<Aluno> buscaAlunoControlId(@PathVariable Long id){ 
		Aluno Aluno = AlunoService.getAlunoById(id); 
		if (Aluno != null) { 
			return ResponseEntity.ok(Aluno);
		} 

		else { 
			return ResponseEntity.notFound().build();
		} 

	} 

	@GetMapping("/") 
	public ResponseEntity<List<Aluno>> buscaTodosAlunosControl(){
		List<Aluno> Aluno = AlunoService.getAllAluno();
		return ResponseEntity.ok(Aluno);
	}

	@PostMapping("/")
	public ResponseEntity<Aluno> salvaAlunosControl(@RequestBody @Valid Aluno Aluno){
		Aluno salvaAluno = AlunoService.salvarAluno(Aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaAluno);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Aluno> alteraAlunosControl(@PathVariable Long id, @RequestBody @Valid Aluno Aluno){
		Aluno alteraAluno = AlunoService.updateAluno(id, Aluno);
		if (alteraAluno != null) {
			return ResponseEntity.ok(Aluno);
		}

		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagaAlunoControl(@PathVariable Long id){
		boolean apagar = AlunoService.deleteAluno(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		else {
			return ResponseEntity.notFound().build();
		}
	}
}