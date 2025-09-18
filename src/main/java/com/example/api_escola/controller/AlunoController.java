package com.example.api_escola.controller;

import com.example.api_escola.model.Aluno;
import com.example.api_escola.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        return service.salvar(aluno);
    }

    // Atualizar passando id na URL
    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        return service.atualizar(id, aluno);
    }

    // Atualizar passando id no corpo
    @PutMapping
    public ResponseEntity<Aluno> atualizarPorBody(@RequestBody Aluno aluno) {
        if (aluno.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Aluno atualizado = service.atualizar(aluno.getId(), aluno);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Aluno> listar() {
        return service.listarTodos();
    }
}
