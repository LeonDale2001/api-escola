package com.example.api_escola.service;

import com.example.api_escola.model.Turma;
import com.example.api_escola.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    private final TurmaRepository repository;

    public TurmaService(TurmaRepository repository) {
        this.repository = repository;
    }

    public Turma salvar(Turma turma) {
        return repository.save(turma);
    }

    public Turma atualizar(Long id, Turma turma) {
        turma.setId(id);
        return repository.save(turma);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public Optional<Turma> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Turma> listarTodos() {
        return repository.findAll();
    }
}
