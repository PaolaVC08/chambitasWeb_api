package com.ann.chambitasWeb.models;

import jakarta.persistence.*;

@Entity
@Table(name = "historial_likes")
public class HistorialLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;

    @ManyToOne
    @JoinColumn(name = "uEmisor_id")
    private Usuario emisor;

    @ManyToOne
    @JoinColumn(name = "uReceptor_id")
    private Usuario receptor;

    // Puedes descomentar y usar estas para saber cuándo fue y estado del like
    // private LocalDateTime fechaLike;
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
}