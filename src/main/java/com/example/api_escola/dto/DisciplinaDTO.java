package com.example.api_escola.dto;

public class DisciplinaDTO {
    private Long id;
    private String nome;
    private int cargaHoraria;

    public DisciplinaDTO(){}

    public DisciplinaDTO(Long id, String nome, int cargaHoraria){
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCargaHoraria(){return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
}