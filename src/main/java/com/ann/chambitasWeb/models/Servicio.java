package com.ann.chambitasWeb.models;
//import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    @ManyToOne
    @JoinColumn(name = "profesionista_profesion_id")
    private ProfesionistaProfesion profesionistaProfesion;

    //private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

  //  private BigDecimal precio;

    @OneToMany(mappedBy = "servicio")
    private List<ImagenServicio> imagenes;
}

