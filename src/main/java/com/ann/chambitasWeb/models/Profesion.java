package com.ann.chambitasWeb.models;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "profesiones")
public class Profesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfesion;

 //   private String nombre;
   // private String icono;

    @OneToMany(mappedBy = "profesion")
    private List<ProfesionistaProfesion> profesionistas;
}

