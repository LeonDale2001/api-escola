package com.example.api_escola.service;

import com.example.api_escola.exception.AlunoException;
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
        validarAluno(aluno);

        if (repository.existsByMatricula(aluno.getMatricula())) {
            throw new AlunoException("Já existe um aluno com essa matrícula.");
        }

        if (repository.existsByEmail(aluno.getEmail())) {
            throw new AlunoException("Já existe um aluno com esse email.");
        }

        return repository.save(aluno);
    }

    public Aluno atualizar(Long id, Aluno aluno) {
        validarAluno(aluno);

        Aluno existing = repository.findById(id)
                .orElseThrow(() -> new AlunoException("Aluno não encontrado com id: " + id));

        // Verifica se existe outro aluno com o mesmo email
        Optional<Aluno> alunoComEmail = repository.findByEmail(aluno.getEmail());
        if (alunoComEmail.isPresent() && !alunoComEmail.get().getId().equals(id)) {
            throw new AlunoException("Já existe um aluno com esse email.");
        }

        // Verifica se existe outro aluno com a mesma matrícula
        Optional<Aluno> alunoComMatricula = repository.findByMatricula(aluno.getMatricula());
        if (alunoComMatricula.isPresent() && !alunoComMatricula.get().getId().equals(id)) {
            throw new AlunoException("Já existe um aluno com essa matrícula.");
        }

        // Atualiza os campos permitidos
        existing.setNome(aluno.getNome());
        existing.setEmail(aluno.getEmail());
        existing.setMatricula(aluno.getMatricula());

        return repository.save(existing);
    }


    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new AlunoException("Aluno não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    // --------------------------
    // 🔹 Validação dos dados
    // --------------------------
    private void validarAluno(Aluno aluno) {
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new AlunoException("O nome do aluno não pode estar vazio.");
        }

        if (!aluno.getNome().matches("^[A-Za-zÀ-ÿ\\s]+$")) {
            throw new AlunoException("O nome do aluno deve conter apenas letras e espaços.");
        }

        if (aluno.getEmail() == null || !aluno.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new AlunoException("O email informado não é válido.");
        }

        if (aluno.getMatricula() == null || aluno.getMatricula().length() != 9) {
            throw new AlunoException("A matrícula deve ter 9 caracteres.");
        }
    }
}
