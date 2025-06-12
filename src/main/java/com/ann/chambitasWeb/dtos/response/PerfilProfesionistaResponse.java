package com.ann.chambitasWeb.dtos.response;

import java.util.List;

public class PerfilProfesionistaResponse {

    private Long id; // ID del profesionista
    private String nombre;
    private String biografia;
    private int likes;
    private List<BiografiaResponse> biografiaResponses;
    private List<MedioContactoResponse> medioContactoResponses;
    private List<ZonaResponse> zonas;
    private List<EducationResponse> educaciones;
    private List<CertificadoResponse> certificados;
    private List<ServiceResponse> servicios;
    private List<ProfesionistaProfesionResponse> profesionistaProfesiones;
// con su setter

    // GETTERS AND SETTERS    
    public Long getId() {
        return id;
    }

    public List<BiografiaResponse> getBiografiaResponses() {
        return biografiaResponses;
    }

    public void setBiografiaResponses(List<BiografiaResponse> biografiaResponses) {
        this.biografiaResponses = biografiaResponses;
    }

    public List<MedioContactoResponse> getMedioContactoResponses() {
        return medioContactoResponses;
    }

    public void setMedioContactoResponses(List<MedioContactoResponse> medioContactoResponses) {
        this.medioContactoResponses = medioContactoResponses;
    }

    public List<ZonaResponse> getZonas() {
        return zonas;
    }

    public void setZonas(List<ZonaResponse> zonas) {
        this.zonas = zonas;
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

    public List<ProfesionistaProfesionResponse> getProfesionistaProfesiones() {
        return profesionistaProfesiones;
    }

    public void setProfesionistaProfesiones(List<ProfesionistaProfesionResponse> profesionistaProfesiones) {
        this.profesionistaProfesiones = profesionistaProfesiones;
    }

    

}
