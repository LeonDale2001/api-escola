package com.example.api_escola.service;

import com.example.api_escola.dto.InscricaoDTO;
import com.example.api_escola.exception.InscricaoException;
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
                .orElseThrow(() -> new InscricaoException("Aluno não encontrado"));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new InscricaoException("Turma não encontrada"));

        if (dto.getDataHora() == null) {
            throw new InscricaoException("DataHora é obrigatória");
        }

        // Verifica duplicidade
        boolean jaInscrito = inscricaoRepository
                .existsByAlunoIdAndTurmaId(aluno.getId(), turma.getId());
        if (jaInscrito) {
            throw new InscricaoException("Aluno já inscrito nesta turma");
        }

        // Valida ano da inscrição
        int anoInscricao = dto.getDataHora().getYear();
        if (anoInscricao < turma.getAno()) {
            throw new InscricaoException("A inscrição não pode ser anterior ao ano da turma");
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
                .orElseThrow(() -> new InscricaoException("Inscrição não encontrada"));

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new InscricaoException("Aluno não encontrado"));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new InscricaoException("Turma não encontrada"));

        if (dto.getDataHora() == null) {
            throw new InscricaoException("DataHora é obrigatória");
        }

        // Verifica duplicidade (ignora a própria inscrição)
        boolean jaInscrito = inscricaoRepository
                .existsByAlunoIdAndTurmaIdAndIdNot(aluno.getId(), turma.getId(), id);
        if (jaInscrito) {
            throw new InscricaoException("Aluno já inscrito nesta turma");
        }

        // Valida ano da inscrição
        int anoInscricao = dto.getDataHora().getYear();
        if (anoInscricao < turma.getAno()) {
            throw new InscricaoException("A inscrição não pode ser anterior ao ano da turma");
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
