package com.example.api_escola.dto;

import jakarta.validation.constraints.NotNull;

public class TurmaDTO {

    @NotNull(message = "Ano é obrigatório")
    private Integer ano;

    @NotNull(message = "Período é obrigatório")
    private Integer periodo;

    @NotNull(message = "ProfessorId é obrigatório")
    private Long professorId;

    // Getters e Setters
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public Integer getPeriodo() { return periodo; }
    public void setPeriodo(Integer periodo) { this.periodo = periodo; }
    public Long getProfessorId() { return professorId; }
    public void setProfessorId(Long professorId) { this.professorId = professorId; }
}
