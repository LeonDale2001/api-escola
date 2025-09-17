package com.example.api_escola.service;

import com.example.api_escola.model.Aluno;
import com.example.api_escola.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno salvar(Aluno aluno) {
        return repository.save(aluno);
    }

    public Aluno atualizar(Long id, Aluno aluno) {
        aluno.setId(id);
        return repository.save(aluno);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }
}
