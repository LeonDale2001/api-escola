package com.example.api_escola.dto;

import java.time.LocalDateTime;

public class InscricaoDTO {
    private Long id;
    private Long alunoId;
    private Long turmaId;
    private LocalDateTime dataHora;

    public InscricaoDTO() {}

    // Construtor sem id (para criar)
    public InscricaoDTO(Long alunoId, Long turmaId, LocalDateTime dataHora) {
        this.alunoId = alunoId;
        this.turmaId = turmaId;
        this.dataHora = dataHora;
    }

    // Construtor completo com id (para resposta)
    public InscricaoDTO(Long id, Long alunoId, Long turmaId, LocalDateTime dataHora) {
        this.id = id;
        this.alunoId = alunoId;
        this.turmaId = turmaId;
        this.dataHora = dataHora;
    }

    // Getters e setters
    public Long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }

    public Long getTurmaId() { return turmaId; }
    public void setTurmaId(Long turmaId) { this.turmaId = turmaId; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
}
