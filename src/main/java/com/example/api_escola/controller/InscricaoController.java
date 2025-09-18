package com.example.api_escola.controller;

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

    @PostMapping
    public Inscricao criar(@RequestBody Inscricao inscricao) {
        return service.salvar(inscricao);
    }

    @PutMapping("/{id}")
    public Inscricao atualizar(@PathVariable Long id, @RequestBody Inscricao inscricao) {
        return service.atualizar(id, inscricao);
    }

    @PutMapping
    public ResponseEntity<Inscricao> atualizarPorBody(@RequestBody Inscricao inscricao) {
        if (inscricao.getId() == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.atualizar(inscricao.getId(), inscricao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removerPorBody(@RequestBody Inscricao inscricao) {
        if (inscricao.getId() == null) return ResponseEntity.badRequest().build();
        service.remover(inscricao.getId());
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
