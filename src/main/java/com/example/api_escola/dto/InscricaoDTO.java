package com.example.api_escola.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class InscricaoDTO {

    @NotNull(message = "AlunoId é obrigatório")
    private Long alunoId;

    @NotNull(message = "TurmaId é obrigatório")
    private Long turmaId;

    @NotNull(message = "DataHora é obrigatória")
    private LocalDateTime dataHora;

    // Getters e Setters
    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }
    public Long getTurmaId() { return turmaId; }
    public void setTurmaId(Long turmaId) { this.turmaId = turmaId; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
}
