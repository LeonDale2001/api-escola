package com.example.api_escola.service;

import com.example.api_escola.dto.TurmaDTO;
import com.example.api_escola.exception.TurmaException;
import com.example.api_escola.model.Professor;
import com.example.api_escola.model.Turma;
import com.example.api_escola.repository.ProfessorRepository;
import com.example.api_escola.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;

    public TurmaService(TurmaRepository turmaRepository, ProfessorRepository professorRepository) {
        this.turmaRepository = turmaRepository;
        this.professorRepository = professorRepository;
    }

    // Criar Turma usando DTO
    public Turma salvar(TurmaDTO dto) {
        validarTurma(dto);

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new TurmaException("Professor não encontrado"));

        Turma turma = new Turma();
        turma.setAno(dto.getAno());
        turma.setPeriodo(dto.getPeriodo());
        turma.setProfessor(professor);

        return turmaRepository.save(turma);
    }

    // Atualizar Turma usando DTO
    public Turma atualizar(Long id, TurmaDTO dto) {
        validarTurma(dto);

        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new TurmaException("Turma não encontrada"));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new TurmaException("Professor não encontrado"));

        turma.setAno(dto.getAno());
        turma.setPeriodo(dto.getPeriodo());
        turma.setProfessor(professor);

        return turmaRepository.save(turma);
    }

    public void remover(Long id) {
        turmaRepository.deleteById(id);
    }

    public Optional<Turma> buscarPorId(Long id) {
        return turmaRepository.findById(id);
    }

    public List<Turma> listarTodos() {
        return turmaRepository.findAll();
    }

    // --------------------------
    // 🔹 Validação da Turma
    // --------------------------
    private void validarTurma(TurmaDTO dto) {
        int anoAtual = Year.now().getValue();

        if (dto.getPeriodo() != 1 && dto.getPeriodo() != 2) {
            throw new TurmaException("O período (semestre) deve ser 1 ou 2.");
        }

        if (dto.getAno() < 1900 || dto.getAno() > anoAtual) {
            throw new TurmaException("O ano deve estar entre 1900 e " + anoAtual + ".");
        }
    }
}
