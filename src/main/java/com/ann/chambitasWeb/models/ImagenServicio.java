package com.ann.chambitasWeb.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "imagenes_servicio")
public class ImagenServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImg;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Servicio servicio;

    @Column(name = "imagen_b64", columnDefinition = "TEXT", nullable = false)
    private String imagenB64;

    // Getters y setters

    public Long getIdImg() {
        return idImg;
    }

    public void setIdImg(Long idImg) {
        this.idImg = idImg;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getImagenB64() {
        return imagenB64;
    }

    public void setImagenB64(String imagenB64) {
        this.imagenB64 = imagenB64;
    }
}
