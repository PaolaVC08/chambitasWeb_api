package com.ann.chambitasWeb.dtos.request;

import java.util.List;

public class ServiceRequest {

    private Long profesionistaProfesionId;  
    private String nombre;                 
    private String descripcion;           
    //private Long categoriaId;              
    private List<String> imagenesBase64;  

    // Getters y Setters
    public Long getProfesionistaProfesionId() {
        return profesionistaProfesionId;
    }

    public void setProfesionistaProfesionId(Long profesionistaProfesionId) {
        this.profesionistaProfesionId = profesionistaProfesionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //public Long getCategoriaId() {
    //    return categoriaId;
    //}

    //public void setCategoriaId(Long categoriaId) {
    //    this.categoriaId = categoriaId;
    //}

    public List<String> getImagenesBase64() {
        return imagenesBase64;
    }

    public void setImagenesBase64(List<String> imagenesBase64) {
        this.imagenesBase64 = imagenesBase64;
    }
}

