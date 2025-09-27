package com.example.api_escola.repository;

import com.example.api_escola.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    Optional<Disciplina> findByNome(String nome);
    boolean existsByNome(String nome);
}
