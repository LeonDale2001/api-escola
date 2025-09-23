package com.example.api_escola.dto;

public class TurmaDTO {
    private int ano;
    private int periodo;
    private Long professorId;

    // Construtor vazio (necessário para Jackson)
    public TurmaDTO() {}

    // Construtor com todos os atributos
    public TurmaDTO(int ano, int periodo, Long professorId) {
        this.ano = ano;
        this.periodo = periodo;
        this.professorId = professorId;
    }

    // Getters e setters
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public int getPeriodo() { return periodo; }
    public void setPeriodo(int periodo) { this.periodo = periodo; }
    public Long getProfessorId() { return professorId; }
    public void setProfessorId(Long professorId) { this.professorId = professorId; }
}
