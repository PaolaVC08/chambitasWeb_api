package com.ann.chambitasWeb.dtos.response;

import java.util.List;

public class PerfilProfesionistaResponse {

    private Long id; // ID del profesionista
    private String nombre;
    private String biografia;
    private int likes;
    // private List<MediosContactoResponse> mediosDeContacto;
    // private List<ZonaResponse> zonas;
    private List<ProfesionResponse> profesiones;
    private List<EducationResponse> educaciones;
    private List<CertificadoResponse> certificados;
    private List<ServiceResponse> servicios;

    // GETTERS AND SETTERS
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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<EducationResponse> getEducaciones() {
        return educaciones;
    }

    public void setEducaciones(List<EducationResponse> educaciones) {
        this.educaciones = educaciones;
    }

    public List<CertificadoResponse> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<CertificadoResponse> certificados) {
        this.certificados = certificados;
    }

    public List<ServiceResponse> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServiceResponse> servicios) {
        this.servicios = servicios;
    }

    public List<ProfesionResponse> getProfesiones() {
        return profesiones;
    }

    public void setProfesiones(List<ProfesionResponse> profesiones) {
        this.profesiones = profesiones;
    }

}
