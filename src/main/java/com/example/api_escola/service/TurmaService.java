package com.example.api_escola.service;

import com.example.api_escola.dto.TurmaDTO;
import com.example.api_escola.model.Professor;
import com.example.api_escola.model.Turma;
import com.example.api_escola.repository.ProfessorRepository;
import com.example.api_escola.repository.TurmaRepository;
import org.springframework.stereotype.Service;

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
        // Busca professor pelo ID
        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        Turma turma = new Turma();
        turma.setAno(dto.getAno());
        turma.setPeriodo(dto.getPeriodo());
        turma.setProfessor(professor);

        return turmaRepository.save(turma);
    }

    // Atualizar Turma usando DTO
    public Turma atualizar(Long id, TurmaDTO dto) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

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
}
