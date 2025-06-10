package com.ann.chambitasWeb.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SignupProfesionistaRequest extends BaseSignupRequest {

  @NotNull
  private Long zonaId;

  @NotNull
  private Long categoriaId;

  @NotBlank
  private String numeroContacto;

  private String biografia;

  private String horarioAtencion;

  private Integer numeroLikes;

  // Getters y setters

  public Long getZonaId() {
    return zonaId;
  }

  public void setZonaId(Long zonaId) {
    this.zonaId = zonaId;
  }

  public Long getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Long categoriaId) {
    this.categoriaId = categoriaId;
  }

  public String getNumeroContacto() {
    return numeroContacto;
  }

  public void setNumeroContacto(String numeroContacto) {
    this.numeroContacto = numeroContacto;
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

  public Integer getNumeroLikes() {
    return numeroLikes;
  }

  public void setNumeroLikes(Integer numeroLikes) {
    this.numeroLikes = numeroLikes;
  }
}