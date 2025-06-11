package com.ann.chambitasWeb.dtos.response;

import java.time.Year;

public class CertificadoResponse {
    private Long idCertificate;    
    private String titulo;         
    private String institucion;    
    private Year anio;             
    //private ProfesionistaResponse profesionista; 

    // Getters y Setters
    public Long getIdCertificate() {
        return idCertificate;
    }

    public void setIdCertificate(Long idCertificate) {
        this.idCertificate = idCertificate;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public Year getAnio() {
        return anio;
    }

    public void setAnio(Year anio) {
        this.anio = anio;
    }

}
