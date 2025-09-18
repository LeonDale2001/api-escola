package com.example.api_escola.service;

import com.example.api_escola.model.Inscricao;
import com.example.api_escola.repository.InscricaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscricaoService {

    private final InscricaoRepository repository;

    public InscricaoService(InscricaoRepository repository) {
        this.repository = repository;
    }

    public Inscricao salvar(Inscricao inscricao) {
        return repository.save(inscricao);
    }

    public Inscricao atualizar(Long id, Inscricao inscricao) {
        inscricao.setId(id);
        return repository.save(inscricao);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public Optional<Inscricao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Inscricao> listarTodos() {
        return repository.findAll();
    }
}
