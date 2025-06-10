package com.ann.chambitasWeb.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class SignupProfesionistaRequest extends SignupRequest {

    @Size(min = 20, max = 200)
    private String biografia;

    @NotNull
    private Long zonaId;

    @NotNull
    @Size(min = 1)
    private List<MedioContactoRequest> medioContactos;

    @NotNull
    private Long profesionId;

    @NotNull
    private List<Long> profesionesIds;  // Lista de IDs de profesiones que se asignarán al profesionista

    private String horarioAtencion; // Campo para el horario de atención (opcional)

    // Getters y Setters

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Long getZonaId() {
        return zonaId;
    }

    public void setZonaId(Long zonaId) {
        this.zonaId = zonaId;
    }

    public List<MedioContactoRequest> getMedioContactos() {
        return medioContactos;
    }

    public void setMedioContactos(List<MedioContactoRequest> medioContactos) {
        this.medioContactos = medioContactos;
    }

    public Long getProfesionId() {
        return profesionId;
    }

    public void setProfesionId(Long profesionId) {
        this.profesionId = profesionId;
    }

    public List<Long> getProfesionesIds() {
        return profesionesIds;
    }

    public void setProfesionesIds(List<Long> profesionesIds) {
        this.profesionesIds = profesionesIds;
    }

    public String getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(String horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }
}