package com.ann.chambitasWeb.models;

import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "profesionistas")
public class Profesionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prof")
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", unique = true, nullable = false)
    private Usuario usuario;

    @Column(columnDefinition = "TEXT")
    private String biografia;

    @Column(name = "horario_atencion")
    private String horarioAtencion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "zona_id", nullable = false)
    private Zona zona;

    @Column(name = "numero_likes")
    private Integer numeroLikes = 0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "profesionista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certificado> certificados;

    @OneToMany(mappedBy = "profesionista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educacion;

    @OneToMany(mappedBy = "profesionista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfesionistaProfesion> profesiones;

    @OneToMany(mappedBy = "profesionista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedioContacto> contactos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(String horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Integer getNumeroLikes() {
        return numeroLikes;
    }

    public void setNumeroLikes(Integer numeroLikes) {
        this.numeroLikes = numeroLikes;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Certificado> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<Certificado> certificados) {
        this.certificados = certificados;
    }

    public List<Education> getEducacion() {
        return educacion;
    }

    public void setEducacion(List<Education> educacion) {
        this.educacion = educacion;
    }

    public List<ProfesionistaProfesion> getProfesiones() {
        return profesiones;
    }

    public void setProfesiones(List<ProfesionistaProfesion> profesiones) {
        this.profesiones = profesiones;
    }

    public List<MedioContacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<MedioContacto> contactos) {
        this.contactos = contactos;
    }

    
}