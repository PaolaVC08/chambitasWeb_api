package com.ann.chambitasWeb.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "medio_contacto")
public class MedioContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMcontacto;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "profesionista_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Profesionista profesionista;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_contacto_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TipoContacto tipo;

    @Column(name = "valor", nullable = false)
    private String valor;

    // GETTERS AND SETTERS

    public Long getIdMcontacto() {
        return idMcontacto;
    }

    public void setIdMcontacto(Long idMcontacto) {
        this.idMcontacto = idMcontacto;
    }

    public Profesionista getProfesionista() {
        return profesionista;
    }

    public void setProfesionista(Profesionista profesionista) {
        this.profesionista = profesionista;
    }

    public TipoContacto getTipo() {
        return tipo;
    }

    public void setTipo(TipoContacto tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}