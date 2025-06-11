package com.ann.chambitasWeb.dtos.response;

public class ProfesionResponse {

    private Long idProfesion;
    private String nombre;



    public ProfesionResponse(Long idProfesion, String nombre) {
        this.idProfesion = idProfesion;
        this.nombre = nombre;
    }

    public ProfesionResponse() {
        //TODO Auto-generated constructor stub
    }

    // getters and setters
    public Long getIdProfesion() {
        return idProfesion;
    }

    public void setIdProfesion(Long idProfesion) {
        this.idProfesion = idProfesion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
