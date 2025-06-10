package com.ann.chambitasWeb.dtos.request;

import java.time.LocalDate;

public class EducationRequest {

    private Long profesionistaId;
    private String school;
    private String titulo;
    private LocalDate startDate;
    private LocalDate endDate;

    // Getters y Setters
    public Long getProfesionistaId() {
        return profesionistaId;
    }

    public void setProfesionistaId(Long profesionistaId) {
        this.profesionistaId = profesionistaId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
