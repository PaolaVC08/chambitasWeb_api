package com.ann.chambitasWeb.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    @ManyToOne
    @JoinColumn(name = "profesionista_profesion_id")
    private ProfesionistaProfesion profesionistaProfesion;

    @Column(nullable = false)
    private String nombre;  // para el título o nombre del servicio

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;  // relación con categoría

    @OneToMany(mappedBy = "servicio")
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