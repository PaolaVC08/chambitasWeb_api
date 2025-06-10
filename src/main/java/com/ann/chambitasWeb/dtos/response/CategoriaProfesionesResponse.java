package com.ann.chambitasWeb.dtos.response;

import java.util.List;

public class CategoriaProfesionesResponse {
    private Long id;

    private String nombreCategoria;
    private List<ProfesionResponse> profesiones;

    public CategoriaProfesionesResponse(Long id, String nombreCategoria, List<ProfesionResponse> profesiones) {
        this.nombreCategoria = nombreCategoria;
        this.profesiones = profesiones;
        this.id = id;
    }

    // GETTERS AND SETTERS
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<ProfesionResponse> getProfesiones() {
        return profesiones;
    }

    public void setProfesiones(List<ProfesionResponse> profesiones) {
        this.profesiones = profesiones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
