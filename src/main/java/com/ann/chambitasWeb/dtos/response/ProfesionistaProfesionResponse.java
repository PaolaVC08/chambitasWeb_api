package com.ann.chambitasWeb.dtos.response;

public class ProfesionistaProfesionResponse {

    private Long idPp;
    private Long profesionistaId;
    private Long profesionId;
    private String profesionNombre; 

    // Getters y setters

    public Long getIdPp() {
        return idPp;
    }

    public void setIdPp(Long idPp) {
        this.idPp = idPp;
    }

    public Long getProfesionistaId() {
        return profesionistaId;
    }

    public void setProfesionistaId(Long profesionistaId) {
        this.profesionistaId = profesionistaId;
    }

    public Long getProfesionId() {
        return profesionId;
    }

    public void setProfesionId(Long profesionId) {
        this.profesionId = profesionId;
    }

    public String getProfesionNombre() {
        return profesionNombre;
    }

    public void setProfesionNombre(String profesionNombre) {
        this.profesionNombre = profesionNombre;
    }
}