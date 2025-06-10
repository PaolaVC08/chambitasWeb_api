package com.ann.chambitasWeb.models;

import java.util.List;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "profesionista_profesion")
public class ProfesionistaProfesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPp;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "profesionista_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Profesionista profesionista;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "profesion_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Profesion profesion;

    @OneToMany(mappedBy = "profesionistaProfesion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Servicio> servicios;

    // Getters y Setters

    public Long getIdPp() {
        return idPp;
    }

    public void setIdPp(Long idPp) {
        this.idPp = idPp;
    }

    public Profesionista getProfesionista() {
        return profesionista;
    }

    public void setProfesionista(Profesionista profesionista) {
        this.profesionista = profesionista;
    }

    public Profesion getProfesion() {
        return profesion;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}
