package com.example.api_escola.dto;

public class TurmaDTO {
    private Long id;
    private int ano;
    private int periodo;
    private Long professorId;

    public TurmaDTO() {}

    // Construtor sem id (para criar)
    public TurmaDTO(int ano, int periodo, Long professorId) {
        this.ano = ano;
        this.periodo = periodo;
        this.professorId = professorId;
    }

    // Construtor completo com id (para retorno)
    public TurmaDTO(Long id, int ano, int periodo, Long professorId) {
        this.id = id;
        this.ano = ano;
        this.periodo = periodo;
        this.professorId = professorId;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public int getPeriodo() { return periodo; }
    public void setPeriodo(int periodo) { this.periodo = periodo; }
    
    public Long getProfessorId() { return professorId; }
    public void setProfessorId(Long professorId) { this.professorId = professorId; }
}
