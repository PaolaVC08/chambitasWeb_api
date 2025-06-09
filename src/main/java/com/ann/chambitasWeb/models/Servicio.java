package com.ann.chambitasWeb.models;

import jakarta.persistence.*;
import java.util.List;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "profesionista_profesion_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProfesionistaProfesion profesionistaProfesion;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagenServicio> imagenes;

    // Getters y setters

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public ProfesionistaProfesion getProfesionistaProfesion() {
        return profesionistaProfesion;
    }

    public void setProfesionistaProfesion(ProfesionistaProfesion profesionistaProfesion) {
        this.profesionistaProfesion = profesionistaProfesion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<ImagenServicio> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenServicio> imagenes) {
        this.imagenes = imagenes;
    }
}
