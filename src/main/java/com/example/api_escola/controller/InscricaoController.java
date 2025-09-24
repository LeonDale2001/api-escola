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

    @PostMapping
    public ResponseEntity<InscricaoDTO> criar(@RequestBody InscricaoDTO dto) {
        try {
            Inscricao inscricao = service.salvar(dto);
            InscricaoDTO resposta = new InscricaoDTO(
                inscricao.getId(),
                inscricao.getAluno().getId(), 
                inscricao.getTurma().getId(), 
                inscricao.getDataHora());
            return ResponseEntity.ok(resposta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscricaoDTO> atualizar(@PathVariable Long id, @RequestBody InscricaoDTO dto) {
        try {
            Inscricao inscricao = service.atualizar(id, dto);
            InscricaoDTO resposta = new InscricaoDTO(
                inscricao.getId(),
                inscricao.getAluno().getId(), 
                inscricao.getTurma().getId(), 
                inscricao.getDataHora());
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

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoDTO> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(i -> new InscricaoDTO(
                    i.getId(),
                    i.getAluno().getId(), 
                    i.getTurma().getId(), 
                    i.getDataHora()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<InscricaoDTO> listar() {
        return service.listarTodos().stream()
                .map(i -> new InscricaoDTO(
                    i.getId(),
                    i.getAluno().getId(), 
                    i.getTurma().getId(), 
                    i.getDataHora()))
                .toList();
    }
}
