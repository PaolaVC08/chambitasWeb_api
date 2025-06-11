package com.ann.chambitasWeb.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "zonas")
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // Relación inversa, una zona puede tener varios profesionistas
    @OneToMany(mappedBy = "zona")
    private List<Profesionista> profesionistas;

    // getters y setters
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

    public List<Profesionista> getProfesionistas() {
        return profesionistas;
    }

    public void setProfesionistas(List<Profesionista> profesionistas) {
        this.profesionistas = profesionistas;
    }
}