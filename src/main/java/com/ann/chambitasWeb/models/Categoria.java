package com.ann.chambitasWeb.models;
import jakarta.persistence.*;
@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

  //  private String nombre;
    //private String icono;
    //private String descripcion;
}

