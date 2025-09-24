package com.example.api_escola.controller;

import com.example.api_escola.dto.ProfessorDTO;
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
    public ResponseEntity<ProfessorDTO> criar(@RequestBody Professor professor) {
        Professor salvo = service.salvar(professor);
        return ResponseEntity.ok(new ProfessorDTO(salvo.getId(), salvo.getNome(), salvo.getEmail()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> atualizar(@PathVariable Long id, @RequestBody Professor professor) {
        Professor atualizado = service.atualizar(id, professor);
        return ResponseEntity.ok(new ProfessorDTO(atualizado.getId(), atualizado.getNome(), atualizado.getEmail()));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(p -> new ProfessorDTO(p.getId(), p.getNome(), p.getEmail()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ProfessorDTO> listar() {
        return service.listarTodos().stream()
                .map(p -> new ProfessorDTO(p.getId(), p.getNome(), p.getEmail()))
                .toList();
    }

}
