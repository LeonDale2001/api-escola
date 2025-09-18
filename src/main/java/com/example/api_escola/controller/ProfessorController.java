package com.example.api_escola.controller;

import com.example.api_escola.model.Professor;
import com.example.api_escola.service.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping
    public Professor criar(@RequestBody Professor professor) {
        return service.salvar(professor);
    }

    @PutMapping("/{id}")
    public Professor atualizar(@PathVariable Long id, @RequestBody Professor professor) {
        return service.atualizar(id, professor);
    }

    @PutMapping
    public ResponseEntity<Professor> atualizarPorBody(@RequestBody Professor professor) {
        if (professor.getId() == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.atualizar(professor.getId(), professor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removerPorBody(@RequestBody Professor professor) {
        if (professor.getId() == null) return ResponseEntity.badRequest().build();
        service.remover(professor.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Professor> listar() {
        return service.listarTodos();
    }
}
