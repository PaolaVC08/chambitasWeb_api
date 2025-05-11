package com.ann.chambitasWeb.models;
import jakarta.persistence.*;
@Entity
@Table(name = "tipos_contacto")
public class TipoContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTcontacto;

 //   private String nombre;
}

