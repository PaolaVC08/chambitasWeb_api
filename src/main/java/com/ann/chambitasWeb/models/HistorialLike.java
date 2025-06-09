package com.ann.chambitasWeb.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_likes")
public class HistorialLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "uEmisor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario emisor;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "uReceptor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario receptor;

    private LocalDateTime fechaLike;
    // private Boolean status;

    // Getters y setters

    public Long getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Long idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }

    public LocalDateTime getFechaLike() {
        return fechaLike;
    }

    public void setFechaLike(LocalDateTime fechaLike) {
        this.fechaLike = fechaLike;
    }

    // public Boolean getStatus() {
    // return status;
    // }

    // public void setStatus(Boolean status) {
    // this.status = status;
    // }
}
