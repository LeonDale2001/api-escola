package com.example.api_escola.service;

import com.example.api_escola.exception.DisciplinaException;
import com.example.api_escola.model.Disciplina;
import com.example.api_escola.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private final DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    // --------------------------
    // 🔹 Criar nova disciplina
    // --------------------------
    public Disciplina salvar(Disciplina disciplina) {
        validarDisciplina(disciplina);

        if (repository.existsByNome(disciplina.getNome())) {
            throw new DisciplinaException("Já existe uma disciplina com esse nome.");
        }

        return repository.save(disciplina);
    }

    // --------------------------
    // 🔹 Atualizar disciplina
    // --------------------------
    public Disciplina atualizar(Long id, Disciplina disciplina) {
        validarDisciplina(disciplina);

        Disciplina existente = repository.findById(id)
                .orElseThrow(() -> new DisciplinaException("Disciplina não encontrada com id: " + id));

        // Verifica se já existe outra disciplina com o mesmo nome
        Optional<Disciplina> disciplinaComMesmoNome = repository.findByNome(disciplina.getNome());
        if (disciplinaComMesmoNome.isPresent() && !disciplinaComMesmoNome.get().getId().equals(id)) {
            throw new DisciplinaException("Já existe uma disciplina com esse nome.");
        }

        // Atualiza os campos permitidos
        existente.setNome(disciplina.getNome());
        existente.setCargaHoraria(disciplina.getCargaHoraria());

        return repository.save(existente);
    }

    // --------------------------
    // 🔹 Remover disciplina
    // --------------------------
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new DisciplinaException("Disciplina não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }

    // --------------------------
    // 🔹 Buscar por ID
    // --------------------------
    public Optional<Disciplina> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // --------------------------
    // 🔹 Listar todas
    // --------------------------
    public List<Disciplina> listarTodos() {
        return repository.findAll();
    }

    // --------------------------
    // 🔹 Validação
    // --------------------------
    private void validarDisciplina(Disciplina disciplina) {
        if (disciplina.getNome() == null || disciplina.getNome().isBlank()) {
            throw new DisciplinaException("O nome da disciplina não pode estar vazio.");
        }

        if (!disciplina.getNome().matches("^[A-Za-zÀ-ÿ\\s]+$")) {
            throw new DisciplinaException("O nome da disciplina deve conter apenas letras e espaços.");
        }

        if (disciplina.getCargaHoraria() != 32 && disciplina.getCargaHoraria() != 64) {
            throw new DisciplinaException("A carga horária da disciplina deve ser 32 ou 64.");
        }
    }
}
