package com.ann.chambitasWeb.dtos.request;

public class ProfesionRequest {
   
    private Long profesionistaId;
    private String nombre;

    
    public Long getProfesionistaId() {
        return profesionistaId;
    }
    public void setProfesionistaId(Long profesionistaId) {
        this.profesionistaId = profesionistaId;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
}
