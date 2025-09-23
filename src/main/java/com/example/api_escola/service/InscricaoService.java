package com.example.api_escola.service;

import com.example.api_escola.dto.InscricaoDTO;
import com.example.api_escola.model.Aluno;
import com.example.api_escola.model.Inscricao;
import com.example.api_escola.model.Turma;
import com.example.api_escola.repository.AlunoRepository;
import com.example.api_escola.repository.InscricaoRepository;
import com.example.api_escola.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    public InscricaoService(InscricaoRepository inscricaoRepository,
                            AlunoRepository alunoRepository,
                            TurmaRepository turmaRepository) {
        this.inscricaoRepository = inscricaoRepository;
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
    }

    // Criar inscrição usando DTO
    public Inscricao salvar(InscricaoDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        if (dto.getDataHora() == null) {
            throw new IllegalArgumentException("DataHora é obrigatória");
        }

        Inscricao inscricao = new Inscricao();
        inscricao.setAluno(aluno);
        inscricao.setTurma(turma);
        inscricao.setDataHora(dto.getDataHora());

        return inscricaoRepository.save(inscricao);
    }

    // Atualizar inscrição usando DTO
    public Inscricao atualizar(Long id, InscricaoDTO dto) {
        Inscricao inscricao = inscricaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inscrição não encontrada"));

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        if (dto.getDataHora() == null) {
            throw new IllegalArgumentException("DataHora é obrigatória");
        }

        inscricao.setAluno(aluno);
        inscricao.setTurma(turma);
        inscricao.setDataHora(dto.getDataHora());

        return inscricaoRepository.save(inscricao);
    }

    public void remover(Long id) {
        inscricaoRepository.deleteById(id);
    }

    public Optional<Inscricao> buscarPorId(Long id) {
        return inscricaoRepository.findById(id);
    }

    public List<Inscricao> listarTodos() {
        return inscricaoRepository.findAll();
    }
}
