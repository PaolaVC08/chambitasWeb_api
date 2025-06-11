package com.ann.chambitasWeb.dtos.request;

public class ZonaRequest {

    private Long id;
    private String nombre;

    // Constructor
    public ZonaRequest(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}