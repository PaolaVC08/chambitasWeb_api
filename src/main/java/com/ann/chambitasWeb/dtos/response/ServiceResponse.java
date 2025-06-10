package com.ann.chambitasWeb.dtos.response;

import java.util.List;

public class ServiceResponse {

    private Long idServicio;
    private String nombre;
    private String descripcion;
    private String categoria;
    private List<String> imagenesBase64;

    // Getters y Setters
    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<String> getImagenesBase64() {
        return imagenesBase64;
    }

    public void setImagenesBase64(List<String> imagenesBase64) {
        this.imagenesBase64 = imagenesBase64;
    }
}
