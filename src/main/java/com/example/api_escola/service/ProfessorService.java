package com.example.api_escola.service;

import com.example.api_escola.model.Professor;
import com.example.api_escola.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public Professor salvar(Professor professor) {
        return repository.save(professor);
    }

    public Professor atualizar(Long id, Professor professor) {
        professor.setId(id);
        return repository.save(professor);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public Optional<Professor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Professor> listarTodos() {
        return repository.findAll();
    }
}
