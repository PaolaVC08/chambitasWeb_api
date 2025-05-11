package com.ann.chambitasWeb.models;
import jakarta.persistence.*;
@Entity
@Table(name = "zonas")
public class Zona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idZona;

 //   private String nombre;
}

