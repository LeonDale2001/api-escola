// ProfessorRepository.java
package com.example.api_escola.repository;

import com.example.api_escola.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    boolean existsByEmail(String email);

    Optional<Professor> findByEmail(String email);
}
