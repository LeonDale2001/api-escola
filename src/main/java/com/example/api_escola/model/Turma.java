package com.example.api_escola.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ano;
    private int periodo;

    // Uma turma possui 1 professor
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    // Uma turma pode ter 0 ou mais inscrições
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscricao> inscricoes;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public int getPeriodo() { return periodo; }
    public void setPeriodo(int periodo) { this.periodo = periodo; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
    public List<Inscricao> getInscricoes() { return inscricoes; }
    public void setInscricoes(List<Inscricao> inscricoes) { this.inscricoes = inscricoes; }
}
