package com.example.api_escola.repository;

import com.example.api_escola.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    // Verifica se já existe inscrição de um aluno em uma turma
    boolean existsByAlunoIdAndTurmaId(Long alunoId, Long turmaId);

    // Verifica se já existe inscrição de um aluno em uma turma, ignorando uma inscrição específica (para updates)
    boolean existsByAlunoIdAndTurmaIdAndIdNot(Long alunoId, Long turmaId, Long id);
}
