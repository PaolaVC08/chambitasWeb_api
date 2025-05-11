package com.ann.chambitasWeb.models;
//import java.time.LocalDateTime;

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

 //   private LocalDateTime fechaLike;
 //   private Boolean status;
}

