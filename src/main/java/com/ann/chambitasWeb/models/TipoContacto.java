package com.ann.chambitasWeb.models;
import jakarta.persistence.*;
@Entity
@Table(name = "tipos_contacto")
public class TipoContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTcontacto;

    @Column(nullable = false, unique = true)
    private String nombre;

    // Getters y setters
    public Long getIdTcontacto() {
        return idTcontacto;
    }

    public void setIdTcontacto(Long idTcontacto) {
        this.idTcontacto = idTcontacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

