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

    @PostMapping
    public ResponseEntity<TurmaDTO> criar(@RequestBody TurmaDTO dto) {
        Turma turma = service.salvar(dto);
        TurmaDTO resposta = new TurmaDTO(
                turma.getAno(),
                turma.getPeriodo(),
                turma.getProfessor().getId()
        );
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaDTO> atualizar(@PathVariable Long id, @RequestBody TurmaDTO dto) {
        Turma turma = service.atualizar(id, dto);
        TurmaDTO resposta = new TurmaDTO(
                turma.getAno(),
                turma.getPeriodo(),
                turma.getProfessor().getId()
        );
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDTO> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(t -> new TurmaDTO(
                        t.getAno(),
                        t.getPeriodo(),
                        t.getProfessor().getId()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<TurmaDTO> listar() {
        return service.listarTodos().stream()
                .map(t -> new TurmaDTO(
                        t.getAno(),
                        t.getPeriodo(),
                        t.getProfessor().getId()
                ))
                .toList();
    }
}
