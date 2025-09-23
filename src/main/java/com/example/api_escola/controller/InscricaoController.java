package com.example.api_escola.controller;

import com.example.api_escola.dto.InscricaoDTO;
import com.example.api_escola.model.Inscricao;
import com.example.api_escola.service.InscricaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {

    private final InscricaoService service;

    public InscricaoController(InscricaoService service) {
        this.service = service;
    }

    // POST usando DTO
    @PostMapping
    public ResponseEntity<Inscricao> criar(@RequestBody InscricaoDTO dto) {
        try {
            Inscricao inscricao = service.salvar(dto);
            return ResponseEntity.ok(inscricao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // PUT por ID usando DTO
    @PutMapping("/{id}")
    public ResponseEntity<Inscricao> atualizar(@PathVariable Long id, @RequestBody InscricaoDTO dto) {
        try {
            Inscricao inscricao = service.atualizar(id, dto);
            return ResponseEntity.ok(inscricao);
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
    public ResponseEntity<Inscricao> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Inscricao> listar() {
        return service.listarTodos();
    }
}
