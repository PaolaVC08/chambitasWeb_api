package com.ann.chambitasWeb.models;
//import java.time.Year;

import jakarta.persistence.*;

@Entity
@Table(name = "certificados")
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCertificate;

    @ManyToOne
    @JoinColumn(name = "profesionista_id")
    private Profesionista profesionista;

//    private String titulo;
  //  private String institucion;
    //private Year anio;
}
