package com.ann.chambitasWeb.dtos.response;

import java.util.List;

public class CategoriaProfesionesResponse {
    private String nombreCategoria;
    private List<ProfesionResponse> profesiones;

    public CategoriaProfesionesResponse(String nombreCategoria, List<ProfesionResponse> profesiones) {
        this.nombreCategoria = nombreCategoria;
        this.profesiones = profesiones;
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
}
