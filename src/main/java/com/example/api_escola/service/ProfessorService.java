package com.example.api_escola.service;

import com.example.api_escola.exception.ProfessorException;
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
        validarProfessor(professor);

        if (repository.existsByEmail(professor.getEmail())) {
            throw new ProfessorException("Já existe um professor com esse email.");
        }

        return repository.save(professor);
    }

    public Professor atualizar(Long id, Professor professor) {
        validarProfessor(professor);

        repository.findById(id).ifPresent(existing -> {
            if (!existing.getEmail().equals(professor.getEmail()) &&
                repository.existsByEmail(professor.getEmail())) {
                throw new ProfessorException("Já existe um professor com esse email.");
            }
        });

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

    // --------------------------
    // 🔹 Validação de regras
    // --------------------------
    private void validarProfessor(Professor professor) {
        if (professor.getNome() == null || professor.getNome().isBlank()) {
            throw new ProfessorException("O nome do professor não pode estar vazio.");
        }

        if (!professor.getNome().matches("^[A-Za-zÀ-ÿ\\s]+$")) {
            throw new ProfessorException("O nome do professor deve conter apenas letras e espaços.");
        }

        if (professor.getEmail() == null || 
            !professor.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new ProfessorException("O email informado não é válido.");
        }
    }
}
