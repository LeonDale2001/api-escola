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

    // POST usando DTO
    @PostMapping
    public ResponseEntity<Turma> criar(@RequestBody TurmaDTO dto) {
        try {
            Turma turma = service.salvar(dto);
            return ResponseEntity.ok(turma);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // PUT por ID usando DTO
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody TurmaDTO dto) {
        try {
            Turma turma = service.atualizar(id, dto);
            return ResponseEntity.ok(turma);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Turma> listar() {
        return service.listarTodos();
    }
}
