package com.example.api_escola.controller;

import com.example.api_escola.dto.AlunoDTO;
import com.example.api_escola.model.Aluno;
import com.example.api_escola.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> criar(@RequestBody Aluno aluno) {
        Aluno salvo = service.salvar(aluno);
        AlunoDTO dto = new AlunoDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno atualizado = service.atualizar(id, aluno);
        AlunoDTO dto = new AlunoDTO(atualizado.getId(), atualizado.getNome(), atualizado.getEmail());
        return ResponseEntity.ok(dto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(a -> new AlunoDTO(a.getId(), a.getNome(), a.getEmail()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<AlunoDTO> listar() {
        return service.listarTodos().stream()
                .map(a -> new AlunoDTO(a.getId(), a.getNome(), a.getEmail()))
                .toList();
    }
}
