package com.ann.chambitasWeb.dtos.response;

public class ProfesionResponse {
    private Long id;
    private String nombre;

    public ProfesionResponse(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
