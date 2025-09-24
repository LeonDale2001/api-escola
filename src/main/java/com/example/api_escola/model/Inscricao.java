package com.example.api_escola.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    // Cada inscrição pertence a 1 aluno
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    // Cada inscrição pertence a 1 turma
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    
    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }
}
