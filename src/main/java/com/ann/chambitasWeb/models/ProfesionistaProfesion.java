package com.ann.chambitasWeb.models;

import java.util.List;
import jakarta.persistence.*;


@Entity
@Table(name = "profesionista_profesion")
public class ProfesionistaProfesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPp;

    @ManyToOne
    @JoinColumn(name = "profesionista_id")
    private Profesionista profesionista;

    @ManyToOne
    @JoinColumn(name = "profesion_id")
    private Profesion profesion;

    @OneToMany(mappedBy = "profesionistaProfesion")
    private List<Servicio> servicios;
}

