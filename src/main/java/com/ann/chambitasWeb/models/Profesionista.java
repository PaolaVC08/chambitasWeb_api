package com.ann.chambitasWeb.models;

import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "profesionistas")
public class Profesionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prof")
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", unique = true, nullable = false)
    private Usuario usuario;

    @Column(columnDefinition = "TEXT")
    private String biografia;

    @Column(name = "horario_atencion")
    private String horarioAtencion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "zona_id", nullable = false)
    private Zona zona;

    @Column(name = "numero_likes")
    private Integer numeroLikes = 0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "profesionista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certificado> certificados;

    @OneToMany(mappedBy = "profesionista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educacion;

    @OneToMany(mappedBy = "profesionista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfesionistaProfesion> profesiones;

    @OneToMany(mappedBy = "profesionista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedioContacto> contactos;

    // Getters, setters, equals, hashCode, etc.
}