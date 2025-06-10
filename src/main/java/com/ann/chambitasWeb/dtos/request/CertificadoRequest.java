package com.ann.chambitasWeb.dtos.request;

import java.time.Year;

public class CertificadoRequest {
    private Long profesionistaId;  
    private String titulo;         
    private String institucion;    
    private Year anio;             

    // Getters y Setters
    public Long getProfesionistaId() {
        return profesionistaId;
    }

    public void setProfesionistaId(Long profesionistaId) {
        this.profesionistaId = profesionistaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public Year getAnio() {
        return anio;
    }

    public void setAnio(Year anio) {
        this.anio = anio;
    }
}

