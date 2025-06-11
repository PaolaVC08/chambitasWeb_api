package com.ann.chambitasWeb.dtos.response;

public class ProfesionistaZonaResponse {

    private Long id;
    private String nombre;
    private String correo;
    private String biografia;

    public ProfesionistaZonaResponse(Long id, String nombre, String correo, String biografia) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.biografia = biografia;
    }

    // Getters y setters
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}