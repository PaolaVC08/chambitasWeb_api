package com.ann.chambitasWeb.models;
import jakarta.persistence.*;;
@Entity
@Table(name = "medio_contacto")
public class MedioContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMcontacto;

    @ManyToOne
    @JoinColumn(name = "profesionista_id")
    private Profesionista profesionista;

    @ManyToOne
    @JoinColumn(name = "tipo_contacto_id")
    private TipoContacto tipo;

    //private String valor;
}

