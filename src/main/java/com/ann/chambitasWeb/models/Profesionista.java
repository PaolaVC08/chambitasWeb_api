package com.ann.chambitasWeb.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "profesionistas")
public class Profesionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProf;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    @Column(columnDefinition = "TEXT")
    private String biografia;

 //   private String horarioAtencion;

    @ManyToOne
    @JoinColumn(name = "zona_id")
    private Zona zona;

    //private int numeroLikes;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "profesionista")
    private List<Certificado> certificados;

    @OneToMany(mappedBy = "profesionista")
    private List<Education> educacion;

    @OneToMany(mappedBy = "profesionista")
    private List<ProfesionistaProfesion> profesiones;

    @OneToMany(mappedBy = "profesionista")
    private List<MedioContacto> contactos;

    // Getters, setters
}

