package com.example.api_escola.controller;

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

    @PostMapping
    public Turma criar(@RequestBody Turma turma) {
        return service.salvar(turma);
    }

    @PutMapping("/{id}")
    public Turma atualizar(@PathVariable Long id, @RequestBody Turma turma) {
        return service.atualizar(id, turma);
    }

    @PutMapping
    public ResponseEntity<Turma> atualizarPorBody(@RequestBody Turma turma) {
        if (turma.getId() == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.atualizar(turma.getId(), turma));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removerPorBody(@RequestBody Turma turma) {
        if (turma.getId() == null) return ResponseEntity.badRequest().build();
        service.remover(turma.getId());
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
