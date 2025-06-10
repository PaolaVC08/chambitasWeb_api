package com.ann.chambitasWeb.dtos.response;

public class SignupProfesionistaResponse {

    private Long id;
    private String nombre;
    private String correo;
    private String biografia;
    private Long zonaId;
    private Long profesionesIds;
    private Integer numeroLikes;

    // Constructor
    public SignupProfesionistaResponse(Long id, String nombre, String correo, String biografia, 
                                        Long zonaId, Long profesionesIds, Integer numeroLikes) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.biografia = biografia;
        this.zonaId = zonaId;
        this.profesionesIds = profesionesIds;
        this.numeroLikes = numeroLikes;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Long getZonaId() {
        return zonaId;
    }

    public void setZonaId(Long zonaId) {
        this.zonaId = zonaId;
    }

    public Integer getNumeroLikes() {
        return numeroLikes;
    }

    public void setNumeroLikes(Integer numeroLikes) {
        this.numeroLikes = numeroLikes;
    }

    public Long getProfesionesIds() {
        return profesionesIds;
    }

    public void setProfesionesIds(Long profesionesIds) {
        this.profesionesIds = profesionesIds;
    }
}