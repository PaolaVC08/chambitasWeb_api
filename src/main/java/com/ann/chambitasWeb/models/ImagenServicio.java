package com.ann.chambitasWeb.models;
import jakarta.persistence.*;

@Entity
@Table(name = "imagenes_servicio")
public class ImagenServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImg;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @Column(columnDefinition = "TEXT")
    private String imagenB64;
}

