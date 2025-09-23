package com.example.api_escola.controller;

import com.example.api_escola.dto.TurmaDTO;
import com.example.api_escola.model.Turma;
import com.example.api_escola.service.TurmaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    private final TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    // POST usando DTO → retorna apenas ID
    @PostMapping
    public ResponseEntity<TurmaDTO> criar(@RequestBody TurmaDTO dto) {
        try {
            Turma turma = service.salvar(dto);
            TurmaDTO resposta = new TurmaDTO(turma.getAno(), turma.getPeriodo(), turma.getProfessor().getId());
            return ResponseEntity.ok(resposta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT por ID usando DTO → retorna apenas ID
    @PutMapping("/{id}")
    public ResponseEntity<TurmaDTO> atualizar(@PathVariable Long id, @RequestBody TurmaDTO dto) {
        try {
            Turma turma = service.atualizar(id, dto);
            TurmaDTO resposta = new TurmaDTO(turma.getAno(), turma.getPeriodo(), turma.getProfessor().getId());
            return ResponseEntity.ok(resposta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    // GET por ID → retorna apenas ID e dados principais do DTO
    @GetMapping("/{id}")
    public ResponseEntity<TurmaDTO> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(t -> new TurmaDTO(t.getAno(), t.getPeriodo(), t.getProfessor().getId()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET todos → lista de DTOs
    @GetMapping
    public List<TurmaDTO> listar() {
        return service.listarTodos().stream()
                .map(t -> new TurmaDTO(t.getAno(), t.getPeriodo(), t.getProfessor().getId()))
                .toList();
    }
}
