package com.example.api_escola.controller;

import com.example.api_escola.dto.DisciplinaDTO;
import com.example.api_escola.model.Disciplina;
import com.example.api_escola.service.DisciplinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    private final DisciplinaService service;

    public DisciplinaController(DisciplinaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DisciplinaDTO> criar(@RequestBody Disciplina disciplina) {
        Disciplina salvo = service.salvar(disciplina);
        DisciplinaDTO dto = new DisciplinaDTO(salvo.getId(), salvo.getNome(), salvo.getCargaHoraria());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> atualizar(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        Disciplina atualizado = service.atualizar(id, disciplina);
        DisciplinaDTO dto = new DisciplinaDTO(atualizado.getId(), atualizado.getNome(), atualizado.getCargaHoraria());
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(d -> new DisciplinaDTO(d.getId(), d.getNome(), d.getCargaHoraria()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<DisciplinaDTO> listar() {
        return service.listarTodos().stream()
                .map(d -> new DisciplinaDTO(d.getId(), d.getNome(), d.getCargaHoraria()))
                .toList();
    }
}
